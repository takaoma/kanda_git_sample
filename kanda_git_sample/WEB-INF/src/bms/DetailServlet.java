package bms;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String error = "";

		// isbnとcmdのGETパラメータを取得する
		String isbn = request.getParameter("isbn");
		String cmd = request.getParameter("cmd");

		try {

			// BookDAOクラスのオブジェクト
			BookDAO objDao = new BookDAO();

			// 表示する書籍情報を格納するオブジェクト
			Book book = objDao.selectByIsbn(isbn);

			// エンコード設定
			response.setCharacterEncoding("UTF-8");

			// 取得したBookをリクエストスコープに”Book”という名前で格納する
			request.setAttribute("book", book);

			// ISBNリンクをクリックした際に対象の書籍が存在しなかった場合エラー
			if (isbn.equals(null)) {
				error = "表示対象の書籍が存在しない為、詳細情報は表示できませんでした。 ";
				cmd = "list";
				return;
			}

			// DBに接続できなかった場合エラー表示
		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、書籍詳細は表示できませんでした。  ";

			//
		} finally {

			if (cmd.equals("update")) {
				request.getRequestDispatcher("/view/update.jsp").forward(request, response);

			}
			if (cmd.equals("detail")) {
				request.getRequestDispatcher("/view/detail.jsp").forward(request, response);

			}
			if (isbn != null) {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/list").forward(request, response);
			}
		}
	}
}
