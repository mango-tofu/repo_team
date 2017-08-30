package com.wl.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wl.core.model.SysUser;
import com.wl.service.ISysUserService;

@Controller
@RequestMapping("/user")  
public class LoginController {
	@Resource  
    private ISysUserService sysUserSercice;  
    
    @RequestMapping(value="/checkLogin", method=RequestMethod.POST)
    public String login(SysUser sysuser, HttpServletRequest request,HttpSession httpSession, Model model){
    	if("".equals(sysuser.getUsercode()) || "".equals(sysuser.getPassword() )){
    		  request.setAttribute("error", "用户名或密码不能为空！");
              return "login";
    	} else if (!request.getParameter("randomcode").equals(httpSession.getAttribute("validateCode"))){
    		 request.setAttribute("error", "验证码不正确！"); 
    		return "login";
    	}else {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(sysuser.getUsercode(), sysuser.getPassword());
            try{
                subject.login(token);//会跳到我们自定义的realm中
                request.getSession().setAttribute("user", sysuser);
               // return "first";
                return "redirect:/first.action";
            }catch(UnknownAccountException  e){
                e.printStackTrace();
                request.setAttribute("error", "用户名不存在！");
                return "login";
            }catch(IncorrectCredentialsException  e){
                e.printStackTrace();
                request.setAttribute("error", "用户名/密码错误！");
                return "login";
            }catch(AuthenticationException  e){
                e.printStackTrace();
                request.setAttribute("error", "凭证错误！");
                return "login";
            }catch(Exception  e){
                e.printStackTrace();
                request.setAttribute("error", "未知错误！");
                return "login";
            }
    	}
     }
    
    
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "index";
    }   
    @RequestMapping("/login")
    public String admin(HttpServletRequest request) {
        return "login";
    } 
}
