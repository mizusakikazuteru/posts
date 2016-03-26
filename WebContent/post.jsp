<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規投稿</title>
<style type="text/css">
* {
	padding: 5px;
	margin: 0px;
}

body {
	textalign: center;
}

table {
	width: 800px;
	background: while;
	border: 2px black solid;
	border-collapse: collapse;
}

th {
	border: 1px black solid;
	background: #CCCCFF;
}

td {
	border: 1px black solid;
}
</style>
<script type="text/javascript">

</script>
</head>
<body>
	<center>
		<h1>新規投稿画面</h1>

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
		<form action="home" method="post">
			<br />
			<table>
				<tr>
					<th><label for="subject">件名</label></th>
					<td><input name="subject" id="subject" maxlength="20"
						size="50" /></td>
				</tr>
				<tr>
					<th><label for="text">本文</label></th>
					<td><textarea name="text" id="text" cols="70" rows="20" /></textarea></td>
				</tr>
				<tr>
					<th><label for="category">カテゴリー</label></th>
					<td><input name="category" id="category" maxlength="20"
						size="50" /></td>
				</tr>
				<tr>
					<td colspan="2" id="button"><input type="submit" value="新規投稿" />
						<input type="reset" value="消去" /></td>
				</tr>
				<tr>
					<td colspan="2" id="button"><a href="home">ホームに戻る</a></td>
				</tr>
			</table>

		</form>
	</center>
</body>
</html>