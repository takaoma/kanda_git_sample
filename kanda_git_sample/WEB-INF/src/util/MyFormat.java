package util;

import javax.servlet.http.*;
import java.text.DecimalFormat;

public class MyFormat extends HttpServlet {
	/*
	 * 引数に受け取った金額データを「￥付き、３桁カンマ区切り、小数点第2位（.00）」の
	 * 形式に変換するインスタンスメソッド
	 */
	public String moneyFormat(int price){
		//DecimalFormatインスタンスの生成
		DecimalFormat df = new DecimalFormat("\u00A5##,###");
		return df.format(price);
	}
}
