<%@page contentType="text/html;charset=UTF-8" %>
<%@page import="java.util.ArrayList,bms.Book"%>
<%@page import="util.MyFormat"%>
<%
//MyFormatクラスのオブジェクト
	MyFormat objMyForm = new MyFormat();
%>

<%
String error = (String)request.getAttribute("error");
String cmd = (String)request.getAttribute("cmd");
%>
<html>
	<head>
		<title>list</title>
	</head>
	<body>

		<%@include file="/common/header.jsp" %>

		<table align="center" width="850">
		<p align="center"><font size="5">■■エラー</font></p>


		<p align="center"><%= error %></p>

		<table border=0 align="center" >

		<% if(cmd.equals("menu")){%>
			<td width="160">[<a href="<%=request.getContextPath() %>/view/menu.jsp">メニューへ戻る</a>]</td>
		<%}else if(cmd.equals("list")){ %>
			<td width="160">[<a href="<%=request.getContextPath() %>/list">一覧表示に戻る</a>]</td>
		</table>
		<%}%>

			</table>
			<%@include file="/common/footer.jsp" %>
	</body>
</html>