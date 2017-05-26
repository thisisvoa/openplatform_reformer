<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>
<!--顶部条-->
<!-- 顶部工具 S -->
<div class="siteNav">
	<div class="siteNavBox">
		<div class="siteInfo">
			<span class="welcome">您好 <shiro:principal property="username"/></span><shiro:authenticated><a href="${ctx}/logout">[退出]</a></shiro:authenticated><shiro:notAuthenticated>请<a id="login-top" href="${ctx}/login" class="loginBtn">登录</a>  <a href="${ctx}/register" class="registerBtn">免费注册</a></shiro:notAuthenticated>
		</div>
		<div class="siteMenu">
			<a href="${ctx}">首页</a>
			<shiro:authenticated><a href="${ctx}/open/index">开放平台</a></shiro:authenticated>
		</div>
	</div>
</div>
<!-- 顶部工具 E -->