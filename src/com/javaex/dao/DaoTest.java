package com.javaex.dao;

import com.javaex.vo.UserVo;

public class DaoTest {

	public static void main(String[] args) {
		
		//UserVo userVo = new UserVo("cronaldo", "1234", "호날두", "male");
		
		UserDao userDao = new UserDao();
		
		//userDao.userInsert(userVo);
		
		//System.out.println(userVo);
		
		UserVo userVo = userDao.getUser(1);
		
		System.out.println(userVo);
		

		
	}

}
