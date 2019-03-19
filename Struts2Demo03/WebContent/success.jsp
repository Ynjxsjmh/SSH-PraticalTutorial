<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">   
  </head>
  
  <body>
  	<%@taglib uri="/struts-tags" prefix="s" %>
  	<s:form action="#" themie="simple">
  		<!-- 从值栈中提取数据 -->
  		用户名： <s:textfield name="model.userName"></s:textfield>   <br/>
  		密码： <s:textfield name="pwd"></s:textfield>     <br/>
  	</s:form>
  	
  	<s:debug></s:debug>
  </body>
</html>
