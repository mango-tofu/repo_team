package com.wl.manage.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.wl.core.common.MenuUtil;
import com.wl.core.common.TreeNoteUtil;
import com.wl.core.dao.BaseMapper;
import com.wl.core.model.entity.MenuTree;
import com.wl.core.model.entity.MenuVo;
import com.wl.service.ISysRoleService;
import com.wl.service.ISysUserService;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestMyBatis {
	private static Logger logger = Logger.getLogger(TestMyBatis.class);  
//  private ApplicationContext ac = null;  
    @Resource  
    private ISysUserService sysUserSercice;  
    @Resource
	private ISysRoleService sysRoleService;
    
    @Resource
   	private BaseMapper baseMapper;
//  @Before  
//  public void before() {  
//      ac = new ClassPathXmlApplicationContext("applicationContext.xml");  
//      userService = (IUserService) ac.getBean("userService");  
//  }  
  
    @Test  
    public void test1() {  
    	String permissionid = "1,2,3";
    	String roleid = "9";
		String permissionidArr[] = permissionid.split(",");
		int f1 = sysRoleService.delRolePermissionByRoleid(roleid);
			for(int i=0; i<permissionidArr.length; i++){
				sysRoleService.insertRolePermission(roleid, permissionidArr[i]);
			}
    	/* List<MenuVo> list = new ArrayList<MenuVo>();
         //用于把接收到的数据改造成EasyUI tree认识的数据格式
         List<MenuVo> menuTrees = new ArrayList<MenuVo>();
         try {
             //后台接收到的数据
             list = sysUserSercice.findUserMenuListById("2");
             //TreeNoteUtil为工具类，用于改造数据
             menuTrees = MenuUtil.getFatherNode(list);
             Gson gson = new Gson();
             String json = gson.toJson(menuTrees);
             
             String a = " {" + "\"menus\":";
            String b= "}";
            logger.info(json);
             System.out.println(a + json +b);
         } catch (Exception e) {
         	e.printStackTrace();
         }*/
    }
    
    public static void main(String[] args) {
    	JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
    	senderImpl.setHost("smtp.qq.com");
    	MimeMessage mailMessage = senderImpl.createMimeMessage();
    	try {
    		MimeMessageHelper helper = new MimeMessageHelper(mailMessage,true,"utf-8");
        	String[] array = new String[]
        	{"1930716633@qq.com","2213528160@qq.com"};
        	helper.setTo(array);
        	helper.setFrom("1965571954@qq.com");
        	helper.setSubject("这是我的主题！");
        	helper.setText("<p style='color:red;'>这是我的内容！</p>",true);
        	
        	senderImpl.setUsername("898861970@qq.com"); 
        	senderImpl.setPassword("why.,zhu0924"); // 根据自己的情况, 设置password
        	senderImpl.send(mailMessage);
        	System.out.println(" 邮件发送成功.. ");
		} catch (Exception e) {
				e.printStackTrace();
		}
    	
	}
    
}
