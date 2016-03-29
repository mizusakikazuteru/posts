<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー管理画面</title>

<script src="<c:url value="jquery-2.2.0.min.js" />"></script>
<script>
	$(document).ready(function() {
		$('#click').click(function() {
			if (!confirm('本当に停止しますか？')) {

				return false;
			} else {


				location.href = 'management.jsp';
			}
		});
	});
</script>
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
				<form action="management" method="get">
					<c:choose>
						<c:when test="${user.isActive == true }">
							<th width="25%"><input type="button" id="click"
								name="isactive" value="停止"></th>
						</c:when>
						<c:otherwise>
							<th width="25%"><input type="button" id="click"
								name="isactive" value="復活"></th>
						</c:otherwise>
					</c:choose>
				</form>
			</tr>
		</c:forEach>
	</table>
</body>
</html>