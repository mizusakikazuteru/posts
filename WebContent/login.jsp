<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ page import="java.sql.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>ログイン画面</title>
<style type="text/css">
*{padding:5px; margin:0px;}
   body{textalign:center;  background: url("login.png") no-repeat;}
 </style>
<script type="text/javascript">
	function frmCheck() {
		var checkLogin = document.frm1.login.value;
		if (!checkID.match([ azAZ0 * 9 ], [ 5, 19 ])) {
			alert("半角英数字6文字以上20文字以下で入力して下さい。");
			document.frm1.login.focus();
			return false;
		} else if (!checkPass.match(!"#$%&'()=~|`{+*}", [ 5, 244 ])) {
			alert("記号を含む全ての半角文字で6文字以上255文字以下で入力して下さい。");
			document.frm1.login.focus();
			return false;
		}
	}
</script>
</head>
<body>
<h1>ログイン画面</h1>
<form action="home.jsp" method="post" >
<table>
<tr><th>ユーザーID:</th><td><input type="text" name="userId" /><td></tr><br />
<tr><th>パスワード:</th><td><input type="password" name="pass"></td></tr><br />
<td><input type="submit" value="ログイン" /></td>
</table>
</form>
</body>
</html>