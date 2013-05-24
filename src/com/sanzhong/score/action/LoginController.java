package com.sanzhong.score.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sanzhong.score.util.MD5Util;

@Controller
@RequestMapping("/")
public class LoginController {

	@RequestMapping(value = "/login",method=RequestMethod.POST)
	public @ResponseBody Map<String,String> login(HttpServletRequest request, HttpServletResponse response) {
		String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		String account = request.getParameter("username");
		String password = request.getParameter("password");
		Map<String,String> map = new HashMap<String,String>();
		if(account==null||password==null||"".equals(account)||"".equals(password)){
			map.put("success", "false");
			map.put("msg", "用户名或密码不能为空!");
			return map;
		}
		Subject subject = SecurityUtils.getSubject();
		try{
			password = MD5Util.getMD5((password+"{"+account+"}").getBytes());
			subject.login(new UsernamePasswordToken(account,password));
		}catch(UnknownAccountException e){
			map.put("success", "false");
			map.put("msg", "用户名不存在!");
			return map;
		}catch(IncorrectCredentialsException e){
			map.put("success", "false");
			map.put("msg", "用户名或密码错误!");
			return map;
		}catch(AuthenticationException e){
			map.put("success", "false");
			map.put("msg", "未知错误!联系管理员!");
			return map;
		}
		map.put("success", "true");
		map.put("url",basepath+"/index.json");
		return map;
	}
	@RequestMapping(value="/login",method= RequestMethod.GET)
    public String showLoginForm(HttpServletRequest request, HttpServletResponse response) {
        return "login";
    }
	@RequestMapping(value="/index",method= RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response) {
        return "index";
    }
}
