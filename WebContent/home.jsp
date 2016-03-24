<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ホーム</title>
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

li {
	display: inline-block;
}
</style>
<script type="text/javascript">

</script>
</head>
<body>

	<p>
		お疲れ様です
		<c:out value="${loginUser .name}" />
		さん
	</p>
	<c:if test="${ empty loginUser .name }">
		<a href="login">ログイン</a>
	</c:if>
	<center>
		<h1>ホーム画面</h1>
		<c:if test="${ not empty loginUser .name }">
			<ul id="menu">
				<li><a href="post">新規投稿</a></li>
<c:if test="${ not empty errorMessages }">
		<div class="errorMessages">
			<ul>
				<c:forEach items="${errorMessages}" var="message">
					<li><c:out value="${message}" /></li>
				</c:forEach>
			</ul>
		</div>
		<c:remove var="errorMessages" scope="session" />
	</c:if>

	<c:out value="${branch .id}" />
	<li><a href="management">ユーザー管理</a></li>

				<li><a href="logout">ログアウト</a></li>
			</ul>
		</c:if>




	<c:out value="${postList }" />
	<c:forEach items="${postList}" var="post">
		件名
		<c:out value="${post.subject}" />

	</c:forEach>



		<form action="home" method="post">
		</form>
	</center>
</body>
</html>