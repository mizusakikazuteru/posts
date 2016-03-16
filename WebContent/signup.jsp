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
<form action="" method="post"><br />
<table>
	<tr><label for="loginId"><th>ログインID</th></label>
	<td><input name="loginId" id="loginId"/></td></tr><br />

	<tr><label for="password"><th>パスワード</th></label>
	<td><input name="password" type="password" id="password"/></td></tr><br />

	<tr><label for="newpass"><th>確認用パスワード</th></label>
	<td><input name="newpass" type="password" id="newpass"/></td></tr><br/>

	<tr><label for="name"><th>氏名</th></label>
	<td><input name="name"  id="name"/></td></tr><br />

	<tr><label for="branchId"><th>支店名</th></label>
	<td><select name="branchId">
	<option value="0"></option>
	<option value="1">本社</option>
	<option value="2">A支店</option>
	<option value="3">B支店</option>
	<option value="4">C支店</option></td>
	</select></tr>

	<tr><label for="departmentId"><th>部署・役職</th></label>
	<td><select name="departmentId">
	<option value="0"></option>
	<option value="1">総務人事担当者</option>
	<option value="2">情報管理担当者</option>
	<option value="3">支店長</option>
	<option value="4">一般社員</option></td>
	</select></tr> <br />

	<tr><td><input type="submit" value="登録" /><a href="./">戻る</a></td></tr> <br />

	</table>
</form>
</div>
</body>
</html>
