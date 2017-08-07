<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-cn">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/top.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>首页</title>
</head>
<body>
    <div align="center">
    <a href="${ctx}/open/application/add">添加应用</a> 
	<table>
	    <tr><td>appname</td><td>appkey</td><td>secret</td> </tr>
		<c:forEach items="${applications}" var="application">
		 <tr><td>${application.appname}</td><td>${application.appkey}</td><td>${application.secret}</td><td><a href="${ctx}/open/application/${application.id}/delete">删除</a></td></tr>
		</c:forEach>
	</table>
    </div>
</body>

</html>