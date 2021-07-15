package com.javaex.vo;

public class UserVo {
	
	private int no;
	private String id;
	private String password;
	private String name;
	private String sex;
	
	public UserVo () {
		
	}
	
	public UserVo(int no, String id, String password, String name, String sex) {
		super();
		this.no = no;
		this.id = id;
		this.password = password;
		this.name = name;
		this.sex = sex;
	}
	
	public UserVo(String id, String password, String name, String sex) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.sex = sex;
	}
	
	public UserVo(int no, String password, String name, String sex) {
		super();
		this.no = no;
		this.password = password;
		this.name = name;
		this.sex = sex;
	}
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "UserVo [no=" + no + ", id=" + id + ", password=" + password + ", name=" + name + ", sex=" + sex + "]";
	}

	
	
	
	
	
	
	

}
