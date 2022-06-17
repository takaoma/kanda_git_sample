package bms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BookDAO {

	private static String RDB_DRIVE = "com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost:8889/mybookdb";
	private static String USER = "root";
	private static String PASSWD = "root";



	public static Connection getConnection() {
		try {
			Class.forName(RDB_DRIVE);
			Connection con = DriverManager.getConnection(URL, USER, PASSWD);
			return con;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public ArrayList<Book> selectAll() {

		ArrayList<Book> list = new ArrayList<Book>();
		String sql = "SELECT * FROM bookinfo ORDER BY ISBN";

		Connection con = null;
		Statement smt = null;
		try {
			con = BookDAO.getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				Book books = new Book();
				books.setIsbn(rs.getString("isbn"));
				books.setTitle(rs.getString("title"));
				books.setPrice(rs.getInt("price"));

				list.add(books);
			}

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return list;

	}

	// 引数のISBNを元にDBのbookinfoテーブルに新規登録処理を行うメソッド
	public void insert(Book _book) {

		// 登録用のSQL文を文字列として定義
		String sql = "INSERT INTO bookinfo VALUES('" + _book.getIsbn() + "','" + _book.getTitle() + "',"
				+ _book.getPrice() + ")";

		Connection con = null;
		Statement smt = null;
		// int型変数rowsCountを宣言して0で初期化
		int rowsCount = 0;

		try {
			// DBに接続
			con = getConnection();
			smt = con.createStatement();

			// SQL文発行
			rowsCount = smt.executeUpdate(sql);

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}

	// 書籍データを検索しBookオブジェクトに格納するインスタンスメソッド
	public Book selectByIsbn(String _isbn) {

		Connection con = null;
		Statement smt = null;

		// 検索した書籍情報を格納するオブジェクト
		Book book = new Book();

		// 検索用のSQL文を文字列として定義
		String sql = "SELECT isbn,title,price FROM bookinfo WHERE isbn = '" + _isbn + "'";

		try {
			// DBに接続
			con = getConnection();
			smt = con.createStatement();

			// 検索用のSQL文を発行し結果セットを取得
			ResultSet rs = smt.executeQuery(sql);

			// 取得した結果をDTOオブジェクトに格納
			if (rs.next()) {
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setPrice(rs.getInt("price"));
			}

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return book;
	}

	// bookinfoテーブルから該当書籍データの削除を行うインスタンスメソッド
	public void delete(String _isbn) {

		// 削除用のSQL文を文字列として定義
		String sql = "DELETE FROM bookinfo WHERE isbn = '" + _isbn + "'";

		Connection con = null;
		Statement smt = null;
		// int型変数rowsCountを宣言して0で初期化
		int rowsCount = 0;

		try {
			// DBに接続
			con = getConnection();
			smt = con.createStatement();

			// SQL文発行
			rowsCount = smt.executeUpdate(sql);

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}

	//指定された書籍データを更新するインスタンスメソッド
	public void update(Book _book) {

		// 検索した書籍情報を格納するオブジェクト
		Book book = new Book();

		//更新用のSQL文を文字列として定義
		String sql = "UPDATE bookinfo SET title='"+_book.getTitle()+"',price="+_book.getPrice()+" WHERE isbn='"+_book.getIsbn()+"'";

		Connection con = null;
		Statement smt = null;
		// int型変数rowsCountを宣言して0で初期化
		int rowsCount = 0;

		try {
			// DBに接続
			con = getConnection();
			smt = con.createStatement();

			// SQL文発行
			rowsCount = smt.executeUpdate(sql);

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}

	//指定された書籍データを検索するインスタンスメソッド
	public ArrayList<Book> search(String _isbn,String _title, String _price){

		Connection con = null;
		 Statement smt = null;


		//検索した書籍情報を格納するArrayListオブジェクト
		ArrayList<Book> bookList = new ArrayList<Book>();

			//検索用のSQL文を文字列として定義
			String sql = "SELECT isbn,title,price FROM bookinfo " +
					"WHERE isbn LIKE '%" + _isbn + "%' AND title LIKE '%" + _title + "%' AND price LIKE '%" + _price + "%'";

			try {
					// DBに接続
					con = getConnection();
					smt = con.createStatement();

					// 検索用のSQL文を発行し結果セットを取得
					ResultSet rs = smt.executeQuery(sql);

					// 取得した結果をDTOオブジェクトに格納
					while(rs.next()) {
						Book book = new Book();
						book.setIsbn(rs.getString("isbn"));
						book.setTitle(rs.getString("title"));
						book.setPrice(rs.getInt("price"));
						bookList.add(book);
					}

		 }catch(Exception e) {
			 throw new IllegalStateException(e);
		 }finally {
			 if( smt != null) {
				 try {smt.close();}catch(SQLException ignore) {}
			 }
			 if( con != null) {
				 try {con.close();}catch(SQLException ignore) {}
			 }
		 }
		return bookList;
	}
}
