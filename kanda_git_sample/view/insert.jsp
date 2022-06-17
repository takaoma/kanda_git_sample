<%@page contentType="text/html;charset=UTF-8" %>
<%@page import="java.util.ArrayList,bms.Book"%>

<html>
	<head>
		<title>書籍管理システム</title>
	</head>
	<body>
		<%@include file="/common/header.jsp" %>
			<table width="800px">
				<tr>
					<td width="30px"><a href="./menu.jsp">【メニュー】</a></td>
					<td width="30px"><a href="./insert.jsp">【書籍登録】</a></td>
					<td align="center" width="200px"><font size="5">書籍登録</font></td>
				</tr>
			</table>

					<hr align="center" size="2" color="black" width="950">

		<br><br>

		<form action="<%=request.getContextPath() %>/insert">
			<table align="center">
				<tr>
					<td>
					<th style="background-color:#CCCCCC; width:100">ISBN
					</td>
					<td> <input type="text" name="isbn"></td>
				</tr>
				<tr>
					<td>
					<th style="background-color:#CCCCCC; width:100">TITLE
					</td>
					<td><input type="text" name="title"></td>
				</tr>
				<tr>
					<td>
					<th style="background-color:#CCCCCC; width:100">価格
					</td>
					<td><input type="text" name="price"></td>
				</tr>
			</table>
			<p align="center"><input type="submit" value="登録"></p>
		</form>
			<%@include file="/common/footer.jsp" %>
    </body>
</html>
