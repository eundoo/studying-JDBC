package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBMS와 연결된 Connection객체를 제공하는 클래스이다.
 * @author USER
 *
 */

public class ConnectionUtils {

	private static final String DRIVER_CLASS_NAME = "oracle.jdbc.OracleDriver";
	private static final String URL = "job:oracle:thin:@localhost:1521:we";
	private static final String USER_NAME = "hr";
	private static final String PASSWORD = "zxcv1234";
	
	/*
	 * 정적 초기화 블록
	 * 클래스파일(설계도)이 메모리에 로딩될 때 딱 한 번 실행되는 곳이다.
	 * 메모리에 몇번이고 로딩 될 필요가 없잖아
	 * - 생성자 보다 먼저 실행된다.
	 * - 생성자는 객체생성할 때 마다 실행되지만, 정적초기화 블록의 코드는 
	 *   클래스의 일생에서 오직 한 번만 실행된다. (프로그램이 켜져있는한... 프로그램을 껏다 키면 다시 실행되겠지)
	 */
	static {
		try {
			Class.forName(DRIVER_CLASS_NAME);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * DBMS와 연결된 Connection객체를 반환한다.
	 * @return Connection
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
	}
}
