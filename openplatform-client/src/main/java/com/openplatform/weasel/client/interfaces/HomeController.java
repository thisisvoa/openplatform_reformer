package com.openplatform.weasel.client.interfaces;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author dylan
 * @email pickear@gmail.com
 * @time 2015年6月10日
 */
@Controller
public class HomeController {
	
	
	@RequestMapping(value = {"/","index"},method = GET)
	public String authorize(HttpServletRequest request){
		
		return "index";
	}

}
