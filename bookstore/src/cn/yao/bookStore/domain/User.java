package cn.yao.bookStore.domain;

import java.util.Date;

public class User {
	private String username; //姓名
	private String password;//密码
	private String gender;//性别
	private String email;//邮箱
	private String telephone;//电话
	private String introduce;//自我介绍
	private String activeCode;//激活码
	private int state;//状态，激活或者未激活。0是未激活，1是激活
	private String role;//权限，普通用户，管理员用户
	private Date registTime;//注册时间
	
	private int id;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getActiveCode() {
		return activeCode;
	}
	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Date getRegistTime() {
		return registTime;
	}
	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", gender=" + gender + ", email=" + email
				+ ", telephone=" + telephone + ", introduce=" + introduce + ", activeCode=" + activeCode + ", state="
				+ state + ", role=" + role + ", registTime=" + registTime + "]";
	}
	
	
}
