package com.wl.shiro;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.wl.core.model.ActiveUser;
import com.wl.core.model.SysPermission;
import com.wl.core.model.SysUser;
import com.wl.service.ISysUserService;

public class ShiroRealm extends AuthorizingRealm {

	 @Resource
	    private ISysUserService sysUserSercice;
	/* 
     * 授权  授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用 
     */  
    @Override  					
    protected AuthorizationInfo doGetAuthorizationInfo(  
            PrincipalCollection principals) {  
    		ActiveUser activeUser =  (ActiveUser) principals.getPrimaryPrincipal();
		
		//根据身份信息获取权限信息
		//从数据库获取到权限数据
		List<SysPermission> permissionList = null;
		try {
			permissionList = sysUserSercice.findMenuListByUserId(activeUser.getUserid(),"permission");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//单独定一个集合对象 
		List<String> permissions = new ArrayList<String>();
		if(permissionList!=null){
			for(SysPermission sysPermission:permissionList){
				//将数据库中的权限标签 符放入集合
				permissions.add(sysPermission.getPercode());
			}
		}
		
		
	/*	List<String> permissions = new ArrayList<String>();
		permissions.add("user:create");//用户的创建
		permissions.add("item:query");//商品查询权限
		permissions.add("item:add");//商品添加权限
		permissions.add("item:edit");//商品修改权限
*/		//....
		
		//查到权限数据，返回授权信息(要包括 上边的permissions)
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		//将上边查询到授权信息填充到simpleAuthorizationInfo对象中
		simpleAuthorizationInfo.addStringPermissions(permissions);

		return simpleAuthorizationInfo;
	}
	
	//清除缓存
	public void clearCached() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}
    /* 
     * 认证登录 
     */  
    @Override  
    protected AuthenticationInfo doGetAuthenticationInfo(  
            AuthenticationToken authcToken) throws AuthenticationException {  
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;  
        // 简单默认一个用户,实际项目应User user =  
        // userService.getByAccount(token.getUsername());  
        SysUser user = sysUserSercice.getByUsername(token.getUsername());
        if (user == null) {  
            throw new AuthorizationException();  
        }  
    	//盐
		String salt = user.getSalt();
		// 如果查询到返回认证信息AuthenticationInfo
		//activeUser就是用户身份信息
		ActiveUser activeUser = new ActiveUser();
		activeUser.setUserid(String.valueOf(user.getId()));
		activeUser.setUsercode(user.getUsercode());
		activeUser.setUsername(user.getUsername());
		
		//根据用户id取出菜单
		List<SysPermission> menus  = null;
		try {
			//通过service取出菜单 
			menus = sysUserSercice.findMenuListByUserId(String.valueOf(user.getId()),"menu");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//将用户菜单 设置到activeUser
		activeUser.setMenus(menus);
        SimpleAuthenticationInfo info = null;  
        if (user.getUsercode().equals(token.getUsername())) {  
            info = new SimpleAuthenticationInfo(activeUser, user.getPassword(),ByteSource.Util.bytes(salt),this.getName());  
        }  
        return info;  
    }  
}