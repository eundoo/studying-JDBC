package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcinsertApp {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// DBMS 연결할 때 사용되는 JDBC드라이버 클래스 이름
		String driverClassName = "oracle.jdbc.OracleDriver";
		// 연결할 데이터베이스 경로
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		// 데이터베이스 연결 계정이름
		String username = "hr";
		// 데이터베이스 연결 비밀번호
		String password = "zxcv1234";
	
		// 1. Jdbc 드리이버를 메모리에 로딩하기
		// Class.forName("패키지경로를 포함하는 클래스 이름") - 지정된 클래스를 메모리에 로딩한다.
		Class.forName(driverClassName);
		
		// 2. DMBC와 연결하고, Connection 구현객체 획득하기
		// DriverManager는 데이터베이스 연결에 필요한 드라이버 클래스를 선택해서
		// 지정된 URL에 해당하는 DMBS에 연결을 시도하고, 연결이 완료되면
		// Connection 객체(SQL을 실행시키고, 실행결과를 획득하는데 필요한 객체를) 반환한다.
		// 커넥션으로 반환하기때문에 커넥션으로 담아야됨
		Connection connection =  DriverManager.getConnection(url, username, password);
		
		// 3. PreparedStatement 구현객체 획득하기
		// Connection 객체는 데이터베이스에 파라미터처리된 SQL을 전달하는
		// PreparedStatement 구현객체를 생성한다.
		
		//물음표 앞에 또 다른 값이있어도 ?첫번째꺼가 1번임
		String sql = "insert into sample_books values(?,?,?,?,?,?,sysdate)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		
		// 4. ?에 값 바인딩하기
		pstmt.setInt(1,  101);
		pstmt.setString(2, "자바의 정석");
		pstmt.setString(3, "남궁성");
		pstmt.setInt(4, 35000);
		pstmt.setInt(5, 32000);
		pstmt.setInt(6, 50);
		
		
		// 5. SQL구문을 DBMS로 보내기
		int rowCount = pstmt.executeUpdate();
		System.out.println(rowCount + "개의 행이 추가되었습니다.");
		// 6. DBMS와 연결에 사용했던 모든 자원에 대한 점유 해제하기
		pstmt.close();
		connection.close();
	
	}

}
