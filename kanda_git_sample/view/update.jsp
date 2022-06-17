<%@page contentType="text/html;charset=UTF-8" %>
<%@page import="java.util.ArrayList,bms.Book"%>
<%
//リクエストスコープのデータを取得
Book book = (Book)request.getAttribute("book");
%>
<%@page import="util.MyFormat"%>
<%
//MyFormatクラスのオブジェクト
	MyFormat objMyForm = new MyFormat();
%>
<html>
	<head>
		<title>書籍管理システム</title>
	</head>

	<style>
	fixed{
		position: fixed;
		bottom:0;
		right:0;
		vorder:3px solid #6ac5ac;
	}
	</style>

	<body>
		<%@include file="/common/header.jsp" %>

		<table width="850">
			<tr>
				<td width="90">[<a href="<%=request.getContextPath() %>/view/menu.jsp">メニュー</a>]</td>
				<td width="90">[<a href="<%=request.getContextPath() %>/view/insert.jsp">書籍登録</a>]</td>
				<td width="90">[<a href="<%=request.getContextPath() %>/list">書籍一覧</a>]</td>
				<td width="508" align="center" ><font size="5">書籍変更</font></td>
				<td width="80">&nbsp;</td>
				<td width="80">&nbsp;</td>
			</tr>
	</table>

			<hr align="center" size="2" color="black" width="950">

			<br><br><br>

		<div align = "center">
		<form action="<%=request.getContextPath()%>/update">
			<table style="border:1xp solid; margin;0 auto">
			<tr>
				<td align="center"  width="190">
					＜＜変更前情報＞＞
				</td>
			<td align="center" width="170">
					＜＜変更後情報＞＞
			</td>
			</tr>


			<table align="center">
				<tr>
					<td>
					<th style="background-color:#CCCCCC; ">ISBN
					 <th><%=book.getIsbn() %></th>
					 <th><%=book.getIsbn() %></th></td>

				</tr>
				<tr>
					<td>
					<th style="background-color:#CCCCCC; width:100">TITLE
					<th><%=book.getTitle() %></th>
					</td>
					<td><input type="text" name="title"></td>
				</tr>
				<tr>
					<td>
					<th style="background-color:#CCCCCC; width:100">価格</th>
					<th><%=objMyForm.moneyFormat(book.getPrice()) %></th>
					</td>
					<td><input type="text" name="price"></td>
				</tr>
			</table>

			<br><br>

			<p align="center"><input type="submit" value="変更完了"></p>




		</table>
		<input type="hidden" name="isbn" value="<%=book.getIsbn()%>">
		</form>
		</div>
			<%@include file="/common/footer.jsp" %>
	</body>
</html>