package bms;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class InsertServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = "";
		String cmd = null;

		try {

			// 画面からの入力情報を受け取る為にエンコード
			response.setCharacterEncoding("UTF-8");

			// DTOオブジェクト宣言
			Book book = new Book();

			// BookDAOクラスのオブジェクトを生成
			BookDAO objDao = new BookDAO();

			// isbn、title、price等の入力パラメータを取得する。
			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			String price = request.getParameter("price");

			// bookオブジェクトに入力情報を格納
			book.setIsbn(isbn);
			book.setTitle(title);
			book.setPrice(Integer.parseInt(price));

			if (isbn.equals("")) {
				error = "ISBNが未入力の為、書籍登録処理は行えませんでした。 ";
				cmd = "list";
				return;
			}
			Book checkBook=new Book();
			checkBook=objDao.selectByIsbn(isbn);
			if(checkBook.getIsbn() != null) {
				error = "入力ISBNは既に登録済みの為、書籍登録処理は行えませんでした。  ";
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

			objDao.insert(book);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示は行えませんでした。 ";
			cmd = "menu";

		}catch(NumberFormatException e) {
			error = "価格の値が不正の為、書籍登録処理は行えませんでした。    ";
			cmd = "list";

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
