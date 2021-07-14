package com.javaex.vo;

public class UserVo {

	private String id;
	private String password;
	private String name;
	private String sex;
	
	public UserVo () {
		
	}
	
	public UserVo(String id, String password, String name, String sex) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.sex = sex;
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
		return "UserVo [id=" + id + ", password=" + password + ", name=" + name + ", sex=" + sex + "]";
	}
	
	
	
	
	
	

}
