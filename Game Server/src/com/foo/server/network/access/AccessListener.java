package com.foo.server.network.access;

public interface AccessListener {

	void registerPerformed(RegisterEvent registerEvent);

	void loginPerformed(LoginEvent loginEvent);

}