package bms;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class UpdateServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = "";
		String cmd = null;

		try {

			//画面からの入力情報を受け取る為にエンコード
			response.setCharacterEncoding("UTF-8");

			// BookDAOクラスのオブジェクト
			BookDAO objDao = new BookDAO();
			// DTOオブジェクト宣言
			Book book = new Book();

			// isbn、title、price等の入力パラメータを取得する。
			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			String price = request.getParameter("price");

			// bookオブジェクトに入力情報を格納
			book.setIsbn(isbn);
			book.setTitle(title);
			book.setPrice(Integer.parseInt(price));

			//入力情報が入ったbookオブジェクトをobjDaoに代入
			objDao.update(book);

			if (isbn.equals("")) {
				error = "ISBNが未入力の為、書籍登録処理は行えませんでした。 ";
				cmd = "list";
				return;
			}
			if(title.equals("")) {
				error = "タイトルが未入力の為、書籍登録処理は行えませんでした。   ";
				cmd = "list";
				return;
			}
			if(price.equals("")) {
				error = "価格が未入力の為、書籍登録処理は行えませんでした。    ";
				cmd = "list";
				return;
			}


			// 「ListServlet」へフォワード
		} finally {
			if (error != null) {
				request.getRequestDispatcher("/list").forward(request, response);
			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd",cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}
}