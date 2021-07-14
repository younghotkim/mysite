package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestbookVo;

public class GuestbookDao {
	
	//Field
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
	
	//Constructor
	
	//Method GS
	
	//General Methods
	
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
	
	//INSERT
	
	public int guestbookInsert(GuestbookVo guestbookVo) {
		
		int count = -1;
		
		this.getConnection();
		
		try {
			
			String query="";
			query += " insert into guestbook ";
			query += " values(seq_no.nextval, ?, ?, ?, sysdate ) ";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, guestbookVo.getName());
			pstmt.setString(2, guestbookVo.getPassword());
			pstmt.setString(3, guestbookVo.getContent());
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건 등록");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		this.close();
		
		return count;
		
	}
	
	//LIST
	
	public List<GuestbookVo> getGuestbookList() {
		
		List<GuestbookVo> guestbookList = new ArrayList<GuestbookVo>();
		
		this.getConnection();
				
		try {
			
			String query = "";
			query += " select no,";
		    query += "        name,";
		    query += "        password,";
		    query += "        content,";
		    query += "        reg_date";
		    query += " from guestbook ";
		    query += " order by no asc ";
					
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String reg_date = rs.getString("reg_date");
				
				GuestbookVo guestbookVo = new GuestbookVo(no, name, password, content, reg_date);
				
				guestbookList.add(guestbookVo);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.close();
		
		return guestbookList;
		
	}
	
	//DELETE
	
	public int guestbookDelete(int no, String password) {
		
		int count = -1;
		
		this.getConnection();
		
		try {
			
			String query = "";
			query += " delete from guestbook ";
			query += " where no = ? ";
			query += " and password = ? ";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, no);
			pstmt.setString(2, password);
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건이 삭제되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.close();
		
		return count;
		
	}
	

	
	
	
	
	
	
	
	

}
