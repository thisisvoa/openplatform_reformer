<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-cn">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>首页</title>
	<script type="text/javascript">
	var getUserInfo = function(){
		$("#table_div").show();
		$.ajax({
			   type: "GET",
			   url: ctx+"/oauth/user-resource.json",
			   data: "",
			   success: function(data){
				   var userInfos = data.platformUserResources;
				   var html = "";
				   $.each(userInfos,function(n,value){
					   var tr = "<tr><td>"+value.username+"</td><td>"+value.email+"</td><td>"+value.mobilePhone+"</td></tr>";
					   html += tr;
				   });
				   $("#userInfo").append(html);
			   }
			});
	}
</script>
</head>
<body>
	<div align="center">
	           授权令牌:${accessToken} <br>有效时长:${expiresIn} <br>授权状态:${not empty accessToken ? '已授权' : '未授权'}<br>
		<a href="${ctx}/oauth/authorize">开始授权</a>
		<a href="javascript:getUserInfo();">获取用户信息</a>
		<c:if test="${not empty accessToken}">
		<a href="${ctx}/oauth/refresh-access-token">用户授权续期</a>
		</c:if>
		<div id="table_div" style="display: none">
			<table id="userInfo">
				<tr><td>用户名</td><td>邮箱</td><td>电话</td></tr>
			</table>
		</div>
	</div>
</body>
</html>