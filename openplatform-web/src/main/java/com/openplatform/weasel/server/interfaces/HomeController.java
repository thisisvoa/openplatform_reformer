package com.openplatform.weasel.server.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import com.openplatform.weasel.server.application.PlatformUserService;
import com.openplatform.weasel.server.domain.PlatformUser;
import com.weasel.openplatform.security.SecurityServer;
import com.weasel.openplatform.security.helper.EncryptHelper;


@Controller
public class HomeController {

	@Autowired
	private PlatformUserService service;
	
	@Autowired
	private SecurityServer security;
	
	@RequestMapping(value = {"/index","/"},method = GET)
	public String index(){
		
		return "index";
	}
	
	@RequestMapping(value = "/register",method = GET)
	public String register(){
		
		return "register";
	}
	
	@RequestMapping(value = "/register",method = POST)
	public String register(PlatformUser user,HttpServletRequest request,HttpServletResponse response){
		
		String notEncodePassword = user.getPassword();
		byte[] salt = EncryptHelper.createSalt();
		user.encodePassword(salt);
		user = service.save(user);
		user.setPassword(notEncodePassword);
		security.login(user, request, response);
		return "index";
	}
	
	@RequestMapping(value = "/login",method = GET)
	public String login(){
		
		return "login";
	}
	
	@RequestMapping(value = "/login",method = POST)
	public String login(PlatformUser user,HttpServletRequest request,HttpServletResponse response){
		
		security.login(user, request, response);
		return "index";
	}
	
	@RequestMapping(value = "/logout",method = GET)
	public String logout(){
		
		security.logout();
		return "index";
	}
}
