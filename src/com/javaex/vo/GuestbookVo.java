package com.javaex.vo;

public class GuestbookVo {
	
	private int no;
	private String name;
	private String password;
	private String content;
	private String reg_date;
	
	public GuestbookVo () {
		
	}
	
	//필요한 생성자는 상황에 맞게 만들기

	public GuestbookVo(int no, String name, String password, String content, String reg_date) {
		super();
		this.no = no;
		this.name = name;
		this.password = password;
		this.content = content;
		this.reg_date = reg_date;
	}
	
	public GuestbookVo(String name, String password, String content, String reg_date) {
		super();
		this.name = name;
		this.password = password;
		this.content = content;
		this.reg_date = reg_date;
	}
	
	public GuestbookVo(String name, String password, String content) {
		super();
		this.name = name;
		this.password = password;
		this.content = content;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	@Override
	public String toString() {
		return "GuestbookVo [no=" + no + ", name=" + name + ", password=" + password + ", content=" + content
				+ ", reg_date=" + reg_date + "]";
	}
	
	
	

}
