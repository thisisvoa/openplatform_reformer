<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-cn">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/top.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>登录授权</title>
</head>
<body>
	<form action="${ctx}/open/login?appkey=${appkey}&redirectUri=${redirectUri}&responseType=${responseType}" method="post">
		<table border="0" align="center">
		    <h1 align="center">登录授权</h1>
			<tr><td><input name="username" placeholder="用户名/邮箱/手机号" type="text"/></td></tr>
			<tr><td><input name="password" placeholder="密码" type="password"/></td></tr>
			<tr><td><input type="submit" value="登录"/></td></tr>
		</table>
	</form>
</body>

</html>