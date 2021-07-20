package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.*;

public class BoardDao {

	// Field

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	// Constructor

	// Method GS

	// General Methods

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

	// LIST

	public List<BoardVo> getBoardList() {

		List<BoardVo> boardList = new ArrayList<BoardVo>();

		getConnection();

		try {

			String query = "";

			query += " select b.no, ";
			query += " 		  title, ";
			query += " 		  u.name, ";
			query += " 		  hit, ";
			query += " 		  to_char(sysdate, 'YY-MM-DD HH24:MI') reg_date";
			query += " from board b, users u ";
			query += " where u.no = b.user_no ";

			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				int no = rs.getInt("no");
				String title = rs.getString("title");
				String name = rs.getString("name");
				int hit = rs.getInt("hit");
				String reg_date = rs.getString("reg_date");

				BoardVo boardVo = new BoardVo(no, title, name, hit, reg_date);

				boardList.add(boardVo);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		close();

		return boardList;

	}

	// READ

	public BoardVo boardRead(int no) {

		BoardVo boardVo = null;

		getConnection();

		try {

			String query = "";

			query += " select u.name, ";
			query += " 		  hit, ";
			query += " 		  to_char(sysdate, 'YYYY-MM-DD') reg_date, ";
			query += " 		  title, ";
			query += "        content ";
			query += " from board b, users u ";
			query += " where u.no = b.user_no ";
			query += " and b.no = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				String bName = rs.getString("name");
				int bHit = rs.getInt("hit");
				String bDate = rs.getString("reg_date");
				String bTitle = rs.getString("title");
				String bContent = rs.getString("content");

				boardVo = new BoardVo(bName, bHit, bDate, bTitle, bContent);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		close();

		return boardVo;

	}

	// HIT

	public int boardHit(int no) {

		int count = -1;

		getConnection();

		try {

			String query = "";

			query += " update board ";
			query += " set hit = hit + 1 ";
			query += " where no = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, no);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		close();

		return count;
	}

	// MODIFY

	public int boardModify(BoardVo boardVo) {

		int count = -1;

		getConnection();

		try {

			String query = "";

			query += " update board ";
			query += " set    title = ?, ";
			query += "        content = ? ";
			query += " where no = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContent());
			pstmt.setInt(3, boardVo.getNo());

			count = pstmt.executeUpdate();

			System.out.println(count + "건 수정완료");

		} catch (SQLException e) {

			e.printStackTrace();
		}

		close();

		return count;

	}

	public int boardPost(BoardVo boardVo) {

		int count = -1;

		getConnection();

		try {

			String query = "";

			query += " insert into board";
			query += " values(seq_board_no.nextval, ?, ?, ?, sysdate, ?) ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContent());
			pstmt.setInt(3, boardVo.getHit());
			pstmt.setInt(4, boardVo.getUser_no());

			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close();
		
		return count;

	}

}
