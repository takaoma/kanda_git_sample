package bms;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DeleteServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String cmd = null;
		String error = "";

		try {

			// isbnのgetパラメータを取得
			String isbn = request.getParameter("isbn");

			// BookDAOクラスのオブジェクト
			BookDAO objDao = new BookDAO();

			objDao.delete(isbn);

			// エンコード設定
			response.setCharacterEncoding("UTF-8");

			Book checkBook = new Book();
			checkBook = objDao.selectByIsbn(isbn);
			if (checkBook.getIsbn() == null) {
				error = "削除対象の書籍が存在しない為、書籍削除処理は行えませんでした。  ";
				cmd = "menu";
				return;
			}

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、書籍削除処理は行えませんでした。 ";

			// 「ListServlet」へフォワード
		} finally {
			if (error != null) {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/list").forward(request, response);
			}
		}
	}

}
