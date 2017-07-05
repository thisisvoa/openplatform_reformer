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
	 <form action="${ctx}/open/auth" method="post">
	    <input name="appkey" value="${appkey}" type="hidden" />
	    <input name="redirectUri" value="${redirectUri}" type="hidden" />
	    <input name="responseType" value="${responseType}" type="hidden" />
	    <h2>${username}</h2>
	    <input type="submit" value="授权"/>
	</form>
    </div>
</body>

</html>