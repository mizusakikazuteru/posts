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
	<table>
		<c:forEach var="user" items="${userList}">
			<tr>
				<th width="25%">ID:<c:out value="${user.id}" /></th>
				<th width="25%">ログインID:<c:out value="${user.loginId}" /></th>
				<th width="25%">名前:<c:out value="${user.name}" /></th>
		<c:if test="${ isActive }">
					<form action="management" method="get">
					<c:choose>
						<c:when test="${user.isActive == true }">
							<th width="25%"><button>停止</button></th>
						</c:when>
						<c:otherwise>
							<th width="25%"><button>復活</button></th>
						</c:otherwise>
					</c:choose>
					</form>
		</c:if>
			</tr>




		</c:forEach>
	</table>

</body>
</html>