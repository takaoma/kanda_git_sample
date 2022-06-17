<%@page contentType="text/html;charset=UTF-8" %>
<html>
	<head>
		<title>書籍管理メニュー画面</title>
	</head>
	<body>
		<%@include file="/common/header.jsp" %>
		<p align="center"><font size="5">menu</font></p>
		<hr align="center" size="2" color="black" width="950">

		<table border=0 align="center" summary="メニュー表示">
			<tr><td><a href="../list">【書籍一覧】</a></td></tr>
			<tr><td><a href="./insert.jsp">【書籍登録】</a></td></tr>
		</table>
				<%@include file="/common/footer.jsp" %>
    </body>
</html>