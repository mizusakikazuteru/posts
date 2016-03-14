<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投稿</title>
<style type="text/css">
   *{padding:5px; margin:0px;}
   body{textalign:center;}
   table{width:800px; background:while; border:2px black solid; border-collapse:collapse;}
   th{border:1px black solid; background:#CCCCFF;}
   td{border:1px black solid;}
</style>
<script type="text/javascript">
	function frmCheck(){
		if(document.frm1.title.value ==""){
			alert("件名を入力して下さい。");
			document.frm1.title.focus();
			return false;
		}else if(document.frm1.text.value == ""){
			alert("本文を入力して下さい。");
			document.frm1.text.focus();
			return false;
		}else if(document.frm1.category.value ==""){
			alert("カテゴリーを入力して下さい。");
			document.frm1.category.focus();
			return false;
		}
	}
</script>
</head>
<body>
<center>
<h1>投稿画面</h1>
<form id="frm1"name="frm1"method="post"action="#" onsubmit="return frmCheck()">
<table>
<tr><th>件名</th><td><input type="text" name="title" maxlength="50" size="90" /></td></tr>
<tr><th>本文</th><td><textarea name="messages" cols="70" rows="20" /></textarea></td></tr>
<tr><th>カテゴリー</th><td><input type="text" name="category" maxlength="10" size="20" /></td></tr>
<tr><th>登録日時</th><td><input type="text" name="created_at" maxlength="10" size="20" /></td></tr>
<tr><th>登録者</th><td><input type="text" name="created_at" maxlength="10" size="20" /></td></tr>
<tr><td colspan="2" id="button"><a href="#" onclick="window.history.back()">ホームに戻る</a></td></tr>
</table>

</form>
</center>
</body>
</html>