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
*{padding:5px; margin:0px;}
   body{textalign:center;}
   table{width:800px; background:while; border:2px black solid; border-collapse:collapse;}
   th{border:1px black solid; background:#CCCCFF;}
   td{border:1px black solid;}
</style>
<script type="text/javascript">

</script>
</head>
<body>
	<h1>ホーム画面</h1>

	<c:forEach  var="post" items="${postList}">
	<table>
	<tr><th width="30%">件名:<c:out value="${post.subject}" /></th>
	<th width="20%">投稿者:<c:out value="${post.name}" /></th>
	<th width="30%">投稿日時:<c:out value="${post.createdAt}" /></th>
	<th width="20%">カテゴリー:<c:out value="${post.category}" /></th>
	<tr><td colspan="4" align="left">本文:::<c:out value="${post.text}" /></td></tr>
	</table>
	</c:forEach>
	<c:if test="${ isShowMessageForm }">

		<form action="comment" method="post">
			コメント<br />
			<textarea name="message" cols="100" rows="5" ></textarea>
			<br />
			<input type="submit" value="コメント"">（500文字まで）
		</form>
	</c:if>


</body>
</html>