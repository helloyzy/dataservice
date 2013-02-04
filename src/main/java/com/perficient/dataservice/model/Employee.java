package com.perficient.dataservice.model;

import java.io.Serializable;

public class Employee implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3774222296311252945L;

	private String empNum;
	
	private String name;
	
	private String preferredName;
	
	private String firstName;
	
	private String lastName;
	
	private String level;
	
	private String role;
	
	private String joinCompanyDate;
	
	private String startWorkingDate;
	
	private String leavingDate;
	
	private String department;
	
	private String sex;
	
	private String educationBackground;
	
	private String mobile;
	
	private String yahooIM;
	
	private String email;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPreferredName() {
		return preferredName;
	}

	public void setPreferredName(String preferredName) {
		this.preferredName = preferredName;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getJoinCompanyDate() {
		return joinCompanyDate;
	}

	public void setJoinCompanyDate(String joinCompanyDate) {
		this.joinCompanyDate = joinCompanyDate;
	}

	public String getStartWorkingDate() {
		return startWorkingDate;
	}

	public void setStartWorkingDate(String startWorkingDate) {
		this.startWorkingDate = startWorkingDate;
	}

	public String getLeavingDate() {
		return leavingDate;
	}

	public void setLeavingDate(String leavingDate) {
		this.leavingDate = leavingDate;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEducationBackground() {
		return educationBackground;
	}

	public void setEducationBackground(String educationBackground) {
		this.educationBackground = educationBackground;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getYahooIM() {
		return yahooIM;
	}

	public void setYahooIM(String yahooIM) {
		this.yahooIM = yahooIM;
	}

	public String getEmpNum() {
		return empNum;
	}

	public void setEmpNum(String empNum) {
		this.empNum = empNum;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
	
	public static String[] getEmployeeTitleSequence() {
		return new String[] { "员工编号", "姓名", "First Name",
				"Last Name", "Preferred Name", "Perficient Level", "Role",
				"入公司日期", "参加工作日", "离职日期", "部门", "性别", "学历", "手机", "Yahoo IM",
				"公司邮件" };
	}

}
