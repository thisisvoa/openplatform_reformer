package com.openplatform.weasel.server.interfaces;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.openplatform.weasel.server.application.ApplicationService;
import com.openplatform.weasel.server.domain.Application;
import com.openplatform.weasel.server.domain.PlatformUser;
import com.weasel.openplatform.security.SecurityServer;
import com.weasel.openplatform.security.helper.RandomHelper;
import com.weasel.openplatform.security.helper.ShiroSecurityHelper;

/**
 * @author dylan
 * @email pickear@gmail.com
 * @time 2015年6月9日
 */
@Controller
@RequestMapping(value="/open")
public class OpenPlatformController {
	
	private final static String VIEW = "open";
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private SecurityServer security;

	@RequestMapping(value = {"/","/index"},method = GET)
	public String index(Model model){
		
		String username = ShiroSecurityHelper.getCurrentUsername();
		
		List<Application> applications = applicationService.query(username);
		
		model.addAttribute("applications", applications);
		return VIEW+"/index";
	}
	
	@RequestMapping(value = "/application/add",method = GET)
	public String addApplication(){
		
		return VIEW+"/application_add";
	}
	
	@RequestMapping(value = "/application/add",method = POST)
	public String addApplication(Application application){
		
		PlatformUser user = ShiroSecurityHelper.getCurrentUser();
		application.setUser(user);
		application.setAppkey(RandomHelper.randomNumber(12));
		application.setSecret(RandomHelper.random(28));
		applicationService.save(application);
		
		return "redirect:/open/index";
	}
	
	@RequestMapping(value = "/application/{id}/delete",method = GET)
	public String deleteApplication(@PathVariable("id") Long id){
		
		String username = ShiroSecurityHelper.getCurrentUsername();
		applicationService.delete(id,username);
		
		return "redirect:/open/index";
	}
	
	@RequestMapping(value = "/login",method = GET)
	public String login(){
		
		return "open/login";
	}
	
	@RequestMapping(value = "/login",method = POST)
	public String login(PlatformUser user,HttpServletRequest request,HttpServletResponse response,Model model){
		
		model.addAttribute("redirectUri", ServletRequestUtils.getStringParameter(request, "redirectUri",""));
		model.addAttribute("responseType", ServletRequestUtils.getStringParameter(request, "responseType",""));
		model.addAttribute("appkey", ServletRequestUtils.getStringParameter(request, "appkey",""));
		model.addAttribute("username", user.getUsername());
		security.login(user, request, response);
		return "open/auth";
	}
	
	@RequestMapping(value = "/auth",method = POST)
	public String auth(PlatformUser user,HttpServletRequest request,HttpServletResponse response){
		
		String redirectUri = ServletRequestUtils.getStringParameter(request, "redirectUri","");
		String responseType = ServletRequestUtils.getStringParameter(request, "responseType","");
		String appkey = ServletRequestUtils.getStringParameter(request, "appkey","");
		security.login(user, request, response);
		return "redirect:/oauth/authorize?response_type="+responseType+"&client_id="+appkey+"&redirect_uri="+redirectUri;
	}
	
}
