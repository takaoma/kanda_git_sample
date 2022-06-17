package bms;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;

public class ListServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	//doGetメソッド
	public void doGet(HttpServletRequest request ,HttpServletResponse response)
			throws ServletException ,IOException{

		String error ="";

		try {
		//BookDAOクラスのオブジェクトを生成
		BookDAO objDao = new BookDAO();
		//検索した書籍情報を格納するArrayListオブジェクトを生成
		ArrayList<Book> list = objDao.selectAll();

		//取得した書籍情を「book list」としてリクエストスコープに登録
		request.setAttribute("book_list", list);

		//DBに接続できなかった場合エラー表示
		}catch(IllegalStateException e){
			error="DB接続エラーの為、一覧表示は行えませんでした。 ";


		}finally {
		//「list.jsp」へフォワード
		request.getRequestDispatcher("/view/list.jsp").forward(request, response);
		}

	}
}
