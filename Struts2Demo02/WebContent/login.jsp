<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>login</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
   	<form action="${pageContext.request.contextPath }/login" name="frmLogin"  method="post">
   	   用户名： <input type="text" name="user.userName"> <br/>
   	 密码： <input type="text" name="user.pwd"> <br/>
   	   <input type="submit" value="登陆"> <br/>
   	</form>
  </body>
</html>
