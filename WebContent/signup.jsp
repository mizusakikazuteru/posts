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
   *{padding:5px; margin:0px;}
   body{textalign:center;}
   form{width:800px; background:while; border:2px black solid; border-collapse:collapse;}
  main-contents{textalign:center;}
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
	<c:remove var="errorMessages" scope="session"/>
</c:if>
<form action="signup" method="post"><br />
<table>
	<tr><label for="login_id"><th>ログインID</th></label>
	<td><input name="login_id" id="login_id"/></td></tr><br />

	<tr><label for="password"><th>パスワード</th></label>
	<td><input name="password" type="password" id="password"/></td></tr><br />

	<tr><label for="newpass"><th>確認用パスワード</th></label>
	<td><input name="newpass" type="newpass" id="newpass"/></td></tr><br />

	<tr><label for="name"><th>氏名</th></label>
	<td><input name="name"  id="name"/></td></tr><br />

	<tr><label for="branch_id"><th>支店</th></label>
	<td><input name="branch_id" id="branch_id"/></td></tr> <br />

	<tr><label for="department_id"><th>部署・役職</th></label>
	<td><input name="department_id"  id="department_id"></td></tr> <br />

	<tr><td><input type="submit" value="登録" /><a href="./">戻る</a></td></tr> <br />

	</table>
</form>
</div>
</body>
</html>
