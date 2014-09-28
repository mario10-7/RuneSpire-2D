package com.foo.server.network.access;

public class LoginEvent {

	private String username, password;

	public LoginEvent(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}