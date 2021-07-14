package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestbookVo;

@WebServlet("/guest")
public class GuestbookController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		// post시 한글깨짐방지
		System.out.println("컨트롤러");

		String action = request.getParameter("action");
		System.out.println(action);

		if ("list".equals(action)) {

			System.out.println("===리스트===");

			GuestbookDao guestbookDao = new GuestbookDao();

			List<GuestbookVo> guestbookList = guestbookDao.getGuestbookList();

			request.setAttribute("gList", guestbookList);

			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/list.jsp");

		} else if ("insert".equals(action)) {

			System.out.println("===등록===");

			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");

			GuestbookVo guestbookVo = new GuestbookVo(name, password, content);

			GuestbookDao guestbookDao = new GuestbookDao();

			int count = guestbookDao.guestbookInsert(guestbookVo);

			if (count > 0) {
				System.out.println("등록완료");

				WebUtil.redirect(request, response, "/mysite/guest?action=list");

			} else {
				System.out.println("등록실패");
			}

		} else if ("delete".equals(action)) {

			System.out.println("===삭제===");

			String password = request.getParameter("password");

			int no = Integer.parseInt(request.getParameter("no"));

			GuestbookDao guestbookDao = new GuestbookDao();

			int count = guestbookDao.guestbookDelete(no, password);

			if (count > 0) {
				System.out.println("삭제완료");

				WebUtil.redirect(request, response, "/mysite/guest?action=list");

			} else {

				System.out.println("삭제실패");

				String refere = (String) request.getHeader("REFERER");

				WebUtil.redirect(request, response, refere);

			}

		} else if ("dform".equals(action)) {

			System.out.println("===삭제폼===");

			int no = Integer.parseInt(request.getParameter("no"));

			request.setAttribute("guestNo", no);

			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/deleteForm.jsp");

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
