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
	<h3>ログイン画面</h3>
	<div class="main-contents">
		<td align="center">
<%
	String id = "";
	String pass ="";
	if(request.getParameter("id") != null){
		id = request.getParameter("id");

	if(request.getParameter("pass") != null){
		pass = request.getParameter("pass");

	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
			"root","root");

	Statement st = con.createStatement();
	String sql = "SELECT * FROM keijiban WHERE id = \"" +id + "\" and pass =\"" +pass +"\"";
	ResultSet rs = st.executeQuery(sql);

	while(rs.next()){
		id = rs.getString("id");
		pass  = rs.getString("pass");

	}
	rs.close();
	st.close();
	con.close();
	}
}
%>
<form id= "frm1" name="frm1" action="Postnewscreen.jsp" method="post" onSubmit="returnfrmCheck()">
				<br />
				<table>
					<tr>
						<td>ユーザーID <input name="id"  id="id" size="40"
							maxlength="32"  /></td>
					</tr>
					<tr>
						<td>パスワード&nbsp;&nbsp; <input name="password" type="password"
							id="password" size="41" maxlength="32"  /><br />
						</td>
					</tr>
				</table>
				<a href="Postnewscreen.jsp"><input type="submit" value="ログイン" /></a><br />
				<a href="home.jsp">ホームへ戻る</a>

			</form>
			</td>
	</div>
</body>
</html>