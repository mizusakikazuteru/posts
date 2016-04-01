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

body, h1 {
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

h3 {
	textalign: left;
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

			<a href="post">新規投稿</a>
			<c:if test="${ not empty errorMessages }">
				<div class="errorMessages">




					<c:forEach items="${errorMessages}" var="message">
						<li><c:out value="${message}" /></li>
					</c:forEach>

				</div>
				<c:remove var="errorMessages" scope="session" />
			</c:if>


			<a href="management">ユーザー管理</a>

			<a href="logout">ログアウト</a>

		</c:if>
		<form action="home" method="get">
        <label for="category">カテゴリー検索</label>
        <input type="text" name="category">
        <input type="submit" value="検索">
        <input type="reset" value="クリア">
		</form>
		<c:forEach var="postList" items="${posts}">
			<table>
				<tr>
					<th width="30%">件名:<c:out value="${postList.subject}" /></th>
					<th width="20%">投稿者:<c:out value="${postList.name}" /></th>
					<th width="30%">投稿日時:<c:out value="${postList.createdAt}" /></th>
					<th width="20%">カテゴリー:<c:out value="${postList.category}" /></th>
				<tr>
					<td colspan="4" align="left">本文:<c:out value="${postList.text}" /></td>
				</tr>
			</table>
			<h3>コメント</h3>
			<br />

				<form action="comment" method="post">
					<input type="hidden" name="postid" value="${postList.id}">
					<textarea name="text" cols="100" rows="5"></textarea>
					<br /> <input type="submit" value="コメント">（500文字まで）
				</form>

			<h2>コメント一覧</h2>
			<c:forEach items="${comments}" var="commentList">
				<table>
				<tr>

				<th width="50%">投稿者:<c:out value="${commentList.id}" /></th>
				<th width="50%">投稿日時:<c:out value="${commentList.createdAt}" /></th>
				</tr>
				<tr>
				<td colspan="4" align="left">本文:<c:out value="${commentList.text}" /></td>
				</tr>
				</table>
			</c:forEach>
			</c:forEach>
</body>
</html>