package com.foo.server.user;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception {

	public UserNotFoundException() {

	}

	public UserNotFoundException(String message) {
		super(message);
	}

}