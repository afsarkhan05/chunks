package com.synechron.prm.form;

import org.apache.struts.action.*;

public class RegisterForm extends ActionForm
{

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String isAdmin;


	public RegisterForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public void setUsername(String username)
	{
		this.username = username;

	}
	public String getUsername()
	{

		return username;
	}
	public void setPassword(String password)
	{

		this.password = password;

	}
	public String getPassword()
	{

		return password;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	
}