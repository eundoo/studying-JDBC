package demo2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import demo2.vo.User;

import static utils.ConnectionUtils.getConnection;

public class UserDao {
	
	/**
	 * 사용자 정보를 전달 받아서 SAMPLE_USERS테이블에 추가한다.
	 * @param user
	 */
	
	public void insertUser(User user) {
		String sql = "insert into sample_users "
				   + "(user_id, user_password, user_name, user_email, user_phone) "
				   + "values "
				   + "(?, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getPhone());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {if(pstmt!=null) pstmt.close();} catch(SQLException e) {}
			try {if(con!=null) con.close();} catch(SQLException e) {}
		}
	}
	/**
	 * 전달받은 아이디에 해당하는 사용자정보를 SAMPLE_USERS 테이블에서 조회한다.
	 * @param userId 조회할 사용자 아이디
	 * @return 사용자정보를 포함하는 User객체, 사용자 정보가 존재하지 않으면 null이 반환된다.
	 */
	public User getUserById(String userId) {
		String sql = "select user_id, user_password, user_name, user_email, user_phone, "
				   + "		 user_status, user_created_date "
				   + "from sample_users "
				   + "where user_id = ? ";
		User user = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				user = new User();
				user.setId(rs.getString("user_id"));
				user.setPassword(rs.getString("user_password"));
				user.setName(rs.getString("user_name"));
				user.setEmail(rs.getString("user_email"));
				user.setPhone(rs.getString("user_phone"));
				user.setStatus(rs.getString("user_status"));
				user.setCreateDate(rs.getDate("user_create_date"));
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {if(rs!=null) rs.close();} catch(SQLException e) {}
			try {if(pstmt!=null) pstmt.close();} catch(SQLException e) {}
			try {if(con!=null) con.close();} catch(SQLException e) {}
		}

		return user;
	}
	/**
	 * 변경할 사용자 정보를 전달 받아서 SAMPLE_USERS 테이블의 사용자 정보를 변경한다.
	 * @param userId
	 */
	public void updateUser(User user) {
		String sql = "update sample_users "
				   + "set "
				   + "user_password = ?, "
				   + "user_email = ?, "
				   + "user_phone = ?, "
				   + "user_status = ?, "
				   + "where user_id = ? ";
	
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getPhone());
			pstmt.setString(4, user.getStatus());
			pstmt.setString(5, user.getId());
			
			pstmt.executeUpdate();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {if(pstmt!=null) pstmt.close();} catch(SQLException e) {}
			try {if(con!=null) con.close();} catch(SQLException e) {}
		}

	}
	
	/**
	 * 전달받은 아이디에 해당하는 사용자 정보를 SAMPLE_USERS테이블에서 삭제한다.
	 * @param userId 삭제할 사용자 아이디
	 */
	public void deleteUser(String userId) {
		String sql = "delete from sample_users "
				   + "where user_id = ? ";
	
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {if(pstmt!=null) pstmt.close();} catch(SQLException e) {}
			try {if(con!=null) con.close();} catch(SQLException e) {}
		}
	
	}

}
