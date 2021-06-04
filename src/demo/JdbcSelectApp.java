package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class JdbcSelectApp {

	public static void main(String[] args) throws Exception {
		
		String driverClassName = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin@localhost:1521:xe";
		String user = "hr";
		String password = "zxcv1234";
	
		String sql = "select book_no, book_title, book_writer, book_price, "
				   + "		 book_discount_price, book_stock, book_created_date "
				   + "from sample_books "
				   + "order by book_no desc ";
		Class.forName(driverClassName);
		Connection con = DriverManager.getConnection(url, user, password);
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			int no = rs.getInt("book_no");
			String title = rs.getString("book_title");
			String writer = rs.getString("book_writer");
			int price = rs.getInt("book_price");
			int discountPrice = rs.getInt("book_discount_Price");
			int stock = rs.getInt("book_stock");
			Date createdDate = rs.getDate("book_created_date");
			
			System.out.println("----------------------------------");
			System.out.println("번호: " + no);
			System.out.println("제목: " + title);
			System.out.println("저자: " + writer);
			System.out.println("가격: " + price);
			System.out.println("할인가격: " + discountPrice);
			System.out.println("재고수량: " + stock);
			System.out.println("등록날짜: " + createdDate);
		}
		rs.close();
		pstmt.close();
		con.close();
	}
}
