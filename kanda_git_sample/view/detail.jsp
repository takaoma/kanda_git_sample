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
				<td width="508" align="center" ><font size="5">書籍詳細画面</font></td>
				<td width="80">&nbsp;</td>
				<td width="80">&nbsp;</td>
			</tr>
		</table>


		<hr align="center" size="2" color="black" width="950"></hr>

		<br><br>

		<div align = "center">
			<table style="border:1xp solid; margin;0 auto">
			<tr>
				<td align="center">
					<form action="<%=request.getContextPath()%>/detail">
						<input type="hidden" name="isbn" value="<%=book.getIsbn() %>">
						<input type="hidden" name="cmd" value="update">
						<input type="submit" value="変更">
					</form>
				</td>
			<td align="center">
				<form action="<%=request.getContextPath()%>/delete">
					<input type="hidden" name="isbn" value="<%=book.getIsbn() %>">
				<input type="submit" value="削除">
					</form>
			</td>
			</tr>

		<tr>
			<th style="background-color:#CCCCCC; width:100">ISBN</th>
			<th style="background-color:#AEFFBD;  width:100"><%=book.getIsbn() %></th>
		</tr>
		<tr>
			<th style="background-color:#CCCCCC;  width:100">TITLE</th>
			<th style="background-color:#AEFFBD;  width:100"><%=book.getTitle() %></th>
		</tr>
		<tr>
			<th style="background-color:#CCCCCC;  width:100">価格</th>
			<th style="background-color:#AEFFBD;  width:100"><%=objMyForm.moneyFormat(book.getPrice()) %></th>
		</tr>

		</table>

</div>
			<%@include file="/common/footer.jsp" %>
	</body>
</html>