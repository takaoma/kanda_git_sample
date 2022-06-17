package bms;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;

public class SearchServlet extends HttpServlet {
	//doGetメソッド
		public void doGet(HttpServletRequest request ,HttpServletResponse response)
				throws ServletException ,IOException{

			String error ="";

			try {
			//BookDAOクラスのオブジェクトを生成
			BookDAO objDao = new BookDAO();

			//画面からの入力情報を受け取る為にエンコード
			response.setCharacterEncoding("UTF-8");

			// isbn、title、price等の入力パラメータを取得する。
			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			String price = request.getParameter("price");

			//結果セットの戻り値として、Bookオブジェクトのリストを取得する。
			ArrayList<Book> list = objDao.search(isbn, title, price);

			//リクエストスコープ
			request.setAttribute("book_list",list);

			}catch(IllegalStateException e){
				error="DB接続エラーの為、一覧表示は行えませんでした。 ";

			// 「ListServlet」へフォワード
		} finally {
			request.getRequestDispatcher("/view/list.jsp").forward(request, response);

		}
		}

}
