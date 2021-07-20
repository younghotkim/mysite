package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.BoardDao;
import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@WebServlet("/board")
public class BoardController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		System.out.println("컨트롤러");

		String action = request.getParameter("action");

		if ("list".equals(action)) {

			System.out.println("=======================LIST=======================");

			BoardDao boardDao = new BoardDao();

			List<BoardVo> boardList = boardDao.getBoardList();

			request.setAttribute("bList", boardList);

			WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");

		} else if ("read".equals(action)) {

			System.out.println("=======================READ=======================");

			int no = Integer.parseInt(request.getParameter("no"));

			// System.out.println(no);

			BoardDao boardDao = new BoardDao();

			boardDao.boardHit(no); // 조회수 증가

			BoardVo boardVo = boardDao.boardRead(no);

			// System.out.println(boardVo);

			request.setAttribute("boardVo", boardVo);

			WebUtil.forward(request, response, "/WEB-INF/views/board/read.jsp");

		} else if ("modify".equals(action)) {

			System.out.println("=======================MODIFY=======================");

			int no = Integer.parseInt(request.getParameter("no"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			BoardVo boardVo = new BoardVo(no, title, content);

			BoardDao boardDao = new BoardDao();

			boardDao.boardModify(boardVo);

			WebUtil.redirect(request, response, "/mysite/board?action=list");

		} else if ("modifyForm".equals(action)) {

			System.out.println("=======================MODIFYFORM=======================");

			int no = Integer.parseInt(request.getParameter("no"));

			BoardDao boardDao = new BoardDao();

			BoardVo boardVo = boardDao.boardRead(no);

			request.setAttribute("boardVo", boardVo);

			WebUtil.forward(request, response, "/WEB-INF/views/board/modifyForm.jsp");

		} else if ("post".equals(action)) {

			System.out.println("=======================POST=======================");

			HttpSession session = request.getSession();
			UserVo authUser = (UserVo) session.getAttribute("authUser");

			String authUserName = authUser.getName();
			int authUserNo = authUser.getNo();

			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int hit = 0;

			BoardVo boardVo = new BoardVo();
			boardVo.setName(authUserName);
			boardVo.setHit(hit);
			boardVo.setTitle(title);
			boardVo.setContent(content);
			boardVo.setNo(authUserNo);

			BoardDao boardDao = new BoardDao();

			int count = boardDao.boardPost(boardVo);
			
			System.out.println(count + "건이 등록되었습니다.");

			WebUtil.redirect(request, response, "/mysite/board?action=list");

		} else if ("writeForm".equals(action)) {

			System.out.println("=======================WRITEFORM=======================");

			HttpSession session = request.getSession();
			UserVo authUser = (UserVo) session.getAttribute("authUser");

			int authUserNo = authUser.getNo();

			UserDao userDao = new UserDao();

			UserVo userVo = userDao.getUser(authUserNo);

			request.setAttribute("userVo", userVo);

			WebUtil.forward(request, response, "/WEB-INF/views/board/writeForm.jsp");

		} else if ("delete".equals(action)) {

			System.out.println("=======================DELETE=======================");

			int no = Integer.parseInt(request.getParameter("no"));

			BoardDao boardDao = new BoardDao();

			boardDao.boardDelete(no);

			WebUtil.redirect(request, response, "/mysite/board?action=list");

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
