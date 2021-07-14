package com.javaex.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("[UserController]");
		
		String action = request.getParameter("action");

		if("joinForm".equals(action)) {
			
			System.out.println("==============JOINFORM==============");
			
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinForm.jsp");
			
		} else if("join".equals(action)) {
			
			System.out.println("==============JOIN==============");
			
			//회원가입
			
			//http://localhost:8088/mysite/user/join?id=ronaldo&password=%ED%98%B8%EB%82%A0%EB%91%90&sex=female&action=join
			
			//파라미터 꺼내기
			
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			
			//System.out.println(id + password + name + sex);
			
			//vo만들기
			
			UserVo userVo = new UserVo(id,password,name,sex);
			
			System.out.println(userVo.toString());
			
			//*dao.insert(vo) --> db저장
			
			UserDao userDao = new UserDao();
			
				int count = userDao.userInsert(userVo);
				
					if (count>0) {
					
					System.out.println("1건 저장");
					
					WebUtil.forward(request, response, "/WEB-INF/views/user/joinOk.jsp");
					
					} else {
					
					System.out.println("저장실패");
						
					}

		} else if ("loginForm".equals(action)); {
			
			System.out.println("==============LOGINFORM==============");
			
			//로그인폼 포워드
			
			WebUtil.forward(request, response, "/WEB-INF/views/user/loginForm.jsp");
			
		}
	
		
		
		
	
		
		
		
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
