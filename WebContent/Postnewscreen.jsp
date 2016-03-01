<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ page import="java.sql.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<title>新規投稿画面</title>
</head>
<html>
<body>
<div align="center">
<h1>掲示板投稿</h1>
<form method="post" action="">
<table border="1">
<tr>
<th>件名</th>
<td><input type="text" name="title" size="50"></td>
</tr>
<tr>
<th>本文</th>
<td><textarea name="msg" rows="10" cols="40"></textarea></TD>
</tr>
<tr>
<th>カテゴリー</th>
<td><input type="text" name="title" size="10"></td>
</tr>
</table>
<input type="submit" value="投稿">
<input type="reset" value="元に戻す">
</form>
</div>
</body>
</html>