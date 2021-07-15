package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaex.vo.UserVo;

public class UserDao {

	// Field

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	private void getConnection() {

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패" + e);
		} catch (SQLException e) {
			System.out.println("error" + e);
		}
	}

	private void close() {

		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}

		} catch (SQLException e) {
			System.out.println("error" + e);
		}
	}

	// 유저 정보 저장
	public int userInsert(UserVo userVo) {

		int count = -1;

		getConnection();

		try {

			String query = "";

			query += " insert into users ";
			query += " values(seq_user_no.nextval, ?, ? ,?, ?) ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, userVo.getId());
			pstmt.setString(2, userVo.getPassword());
			pstmt.setString(3, userVo.getName());
			pstmt.setString(4, userVo.getSex());

			count = pstmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		close();

		return count;

	}

	// 유저 1명정보 가져오기
	public UserVo getUser(String id, String password) {

		UserVo userVo = null;

		getConnection();

		try {

			String query = "";

			query += " select no, ";
			query += "        name ";
			query += " from users ";
			query += " where id = ? ";
			query += " and password = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, id);
			pstmt.setString(2, password);

			pstmt.executeQuery();
			
			rs = pstmt.executeQuery();

			// 결과처리

			while (rs.next()) {

				int no = rs.getInt("no");
				String name = rs.getString("name");

				// 생성자가 없는경우 setter를 이용

				userVo = new UserVo();

				userVo.setNo(no);
				userVo.setName(name);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		close();

		return userVo;

	}
	
	public UserVo getUser(int no) {

		UserVo userVo = null;

		getConnection();

		try {

			String query = "";

			query += " select no, ";
			query += "        id, ";
			query += "        password,";
			query += "        name,";
			query += "        sex";
			query += " from users ";
			query += " where no = ? ";
			
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, no);

			pstmt.executeQuery();
			
			rs = pstmt.executeQuery();

			// 결과처리

			while (rs.next()) {

				int uNo = rs.getInt("no");
				String uId = rs.getString("id");
				String uPassword = rs.getString("password");
				String uName = rs.getString("name");
				String uSex = rs.getString("sex");

				userVo = new UserVo(uNo, uId, uPassword, uName, uSex);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		close();

		return userVo;

	}
	
	public int userModify(UserVo userVo) {
		
		int count = -1;
		
		getConnection();
		
		try {
			
			String query = "";
			
			query += " update users ";
			query += " set password = ?, ";
			query += "     name = ?, ";
			query += "     sex = ? ";
			query += " where no = ? ";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, userVo.getPassword());
			pstmt.setString(2, userVo.getName());
			pstmt.setString(3, userVo.getSex());
			pstmt.setInt(4, userVo.getNo());
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건 수정완료");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		close();
		
		return count;
	}

}
