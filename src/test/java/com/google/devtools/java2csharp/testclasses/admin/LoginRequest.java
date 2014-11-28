package com.google.devtools.java2csharp.testclasses.admin;

import com.google.devtools.java2csharp.testclasses.Header;

public class LoginRequest extends Message {
	private String username;

	private String password;

	protected LoginRequest() {
		super();
	}

	public LoginRequest(Header header, String username,
			String password) {
		super(header);
		this.username = username;
		this.password = password;
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
}