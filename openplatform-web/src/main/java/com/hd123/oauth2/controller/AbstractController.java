package com.hd123.oauth2.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hd123.oauth2.service.AppService;
import com.hd123.oauth2.service.OAuthService;
import com.hd123.oauth2.service.ProductService;
import com.hd123.oauth2.service.UserService;
import sun.rmi.runtime.Log;

/**
 * 转发器基类
 *
 * @author liyue
 */
public abstract class AbstractController {

  protected final Logger logger = Logger.getLogger(getClass());
  protected static final String PATH = "/api/";

  @Autowired
  protected OAuthService oAuthService;
  @Autowired
  protected AppService appService;
  @Autowired
  protected UserService userService;
  @Autowired
  protected ProductService productService;

}