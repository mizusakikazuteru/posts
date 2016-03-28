<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー登録画面</title>
<style type="text/css">
* {
	padding: 5px;
	margin: 0px;
}

body {
	textalign: center;
}

form {
	width: 800px;
	background: while;
	border: 2px black solid;
	border-collapse: collapse;
}

main-contents {
	textalign: center;
}
</style>
</head>
<body>
	<h1>ユーザー新規登録</h1>
	<div class="main-contents">
		<c:if test="${ not empty errorMessages }">
			<div class="errorMessages">
				<ul>
					<c:forEach items="${errorMessages}" var="message">
						<li><c:out value="${message}" />
					</c:forEach>
				</ul>
			</div>
			<c:remove var="errorMessages" scope="session" />
		</c:if>
		<form action="signup" method="post">
			<br />
			<table>
				<tr>
					<th><label for="loginId">ログインID</label></th>
					<td><input name="loginId" id="loginId" /></td>
				</tr>
				<tr>
					<th><label for="password">パスワード</label></th>
					<td><input name="password" type="password" id="password" /></td>
				</tr>
				<tr>
					<th><label for="newpass">確認用パスワード</label></th>
					<td><input name="newpass" type="password" id="newpass" /></td>
				</tr>
				<tr>
					<th><label for="name">氏名</label></th>
					<td><input name="name" id="name" /></td>
				</tr>
				<tr>
					<th><label for="branchId">支店名</label></th>
					<td><select name="branchId">
							<c:forEach items="${branches}" var="branch">
								<option value="<c:out value='${branch.id}' />"><c:out
										value="${branch.name}" /></option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th><label for="departmentId">部署・役職</label></th>
					<td><select name="departmentId">
							<c:forEach items="${departments}" var="department">
								<option value="<c:out value='${department.id}' />"><c:out
										value="${department.name}" /></option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td><input type="submit" value="登録" /><a href="management">戻る</a></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
