<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="s" uri="/struts-tags" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h3>编写表单</h3>

<form action="" method="post">
	性别：<input type="radio" name="sex" value="1"/>男<input type="radio" name="sex" value="2"/>女
</form>

<h3>使用UI标签方式</h3>
<s:form action="" method="post">
	<%-- 性别：<s:radio name="sex" list="{'男','女'}"/> --%>
	性别：<s:radio name="sex" list="#{'1':'男','2':'女'}"/>
</s:form>

<s:property value="'aaaa'"/>

</body>
</html>