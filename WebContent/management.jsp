<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー管理画面</title>
</head>
<body>
	<h1>ユーザー管理画面</h1>
		<a href="signup.jsp">ユーザー新規登録</a>
		<a href="edit.jsp">ユーザー編集登録</a>

	<c:forEach  var="user" items="${userList}">
	<table>
	<tr>
	<th width="25%">ID:<c:out value="${user.Id}" /></th>
	<th width="25%">ログインID:<c:out value="${user.loginId}" /></th>
	<th width="25%">名前:<c:out value="${user.name}" /></th>
	<th  width="25%">停止・復活:<c:out value="${user.isActive}" /></th>
	</tr>
	</table>
	</c:forEach>

</body>
</html>