package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcDeleteApp {

	public static void main(String[] args) {
		//ojdbc6.jar 라이브러리에 포함되어있는 오라클용 jdbc지원 핵심 클래스
		String driverClassName = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password = "zxcv1234";
		
		String sql = "delete from sample_books where book_no = ?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driverClassName);
			
			// 데이터와 관련된 모든 것은 SQLException을 발생시킴
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 101);
			
			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + "개의 행이 삭제되었습니다.");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		//에러가 나도 finally는 무조건 실행되자너
		} finally {
			try {
				if (pstmt != null) pstmt.close();
		} catch(SQLException e) {}
			try {
				if (con != null) con.close();
			} catch (SQLException e) {}
		}
	}
}
