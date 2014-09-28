package com.daybreak.network.management;

import com.paramvirphagura.network.Session;

public abstract class Manager {

	protected Session session;

	protected Manager(Session session) {
		this.session = session;
	}

}