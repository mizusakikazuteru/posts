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
	<a href="signup">ユーザー新規登録</a>
	<a href="edit">ユーザー編集</a>

	<table>
		<c:forEach var="user" items="${userList}">

			<tr>

				<td width="25%">ID:<c:out value="${user.id}" /></td>
				<td width="25%">ログインID:<c:out value="${user.loginId}" /></td>
				<td width="25%">名前:<c:out value="${user.name}" /></td>

				<td><form action="management" method="post">
					<c:if test="${user.isActive == (true || false) && ( !true || !false ) }" >
						<input type="hidden" name="isactive" value="${user.id },${user.isActive == true }">
						<input type="hidden" name="isactive" value="${user.id },${user.isActive == false }">
						<input type="submit" value="${user.isActive}">
					</c:if>
				</form>
				</td>

			</tr>
		</c:forEach>
	</table>
</body>
</html>