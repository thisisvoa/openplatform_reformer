<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-cn">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/top.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>注册</title>
</head>
<body>
	<form action="${ctx}/open/application/add" method="post">
		<table border="0" align="center">
		    <h1 align="center">添加应用</h1>
			<tr><td>应用名:</td><td><input name="appname" type="text" /></td></tr>
			<tr><td>重定向地址:</td><td><input name="redirectUrl" type="text" /></td></tr>
			<tr><td><input value="添加" type="submit" /> </td></tr>
		</table>
	</form>
</body>

</html>