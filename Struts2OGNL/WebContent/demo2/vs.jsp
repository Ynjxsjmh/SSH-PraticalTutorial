<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h3>从值栈中获取值</h3>

<!--
	// vs.push("刘备");
	// 获取到栈顶的值
	//查看页面的Debug，分为上下两部分。上面的是root中存放的对象，下面中context中存放的对象。
	//在root中又分为左右两部门，左边存放root中每一个对象（最上面的对象下标为0），右边存放对象的属性。
	
	<s:property value="[0].top"/>
	<s:property value="[1].top"/>
	<s:property value="[2].top"/>
	<s:property value="[3].top"/>
	 -->

<!--
	// 栈顶是map集合，通过key获取值
	//vs.set("msg", "小凤");
	<s:property value="[0].top.msg"/>
-->
	<!--  
	//vs.push(user);
	// 栈顶放user对象
	
	<s:property value="[0].top.username"/>
	<s:property value="[0].top.password"/>
	<!--[0].top 关键字是可以省略的 
	<s:property value="username"/>
	-->
<!--
	vs.set("user", user);
	<s:property value="[0].top.user.username"/>
	<s:property value="[0].top.user.password"/>
	-->
	<!--// 省略关键字 
	<s:property value="user.username"/>
     -->
<!--  
	栈顶是list集合
	vs.push(ulist);
	<s:property value="[0].top[0].username"/>
	<s:property value="[0].top[1].username"/>
-->

<!--
	vs.set("ulist", ulist);
	<s:property value="ulist[0].username"/>
-->
<!-- 迭代的标签 
	属性
		* value	要迭代的集合，需要从值栈中获取
		* var	迭代过程中，遍历的对象
		* var编写上，把迭代产生的对象默认压入到context栈中，从context栈取值，加#号
		* var不编写，默认把迭代产生的对象压入到root栈中
		
	for(User user:ulist){}	
	// 编写var的属性
	<s:iterator value="ulist" var="u">
		<s:property value="#u.username"/>
		<s:property value="#u.password"/>
	</s:iterator>
	
	// 没有编写var关键字
	<s:iterator value="ulist">
		<s:property value="username"/>
		<s:property value="password"/>
	</s:iterator>
-->

<!-- 从context栈中获取值，加#号 

HttpServletRequest request = ServletActionContext.getRequest();
request.setAttribute("msg", "美美");
request.getSession().setAttribute("msg", "小风");
-->
<s:property value="#request.msg"/>
<s:property value="#session.msg"/>
<s:property value="#parameters.id"/>
<s:property value="#attr.msg"/>


<!--
	在JSP页面上可以使用EL和JSTL标签库来取值
	使用装饰者模式，连接池 全站编码
	getAttribute()增强了

<c:forEach items="${ ulist }" var="user">
	${ user.username } -- ${ user.password }
</c:forEach>
-->
<!-- 在JSP页面上，查看值栈的内部结构 -->
<s:debug></s:debug>

</body>
</html>