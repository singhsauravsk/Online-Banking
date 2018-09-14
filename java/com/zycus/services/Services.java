package com.zycus.services;

import javax.servlet.http.HttpServletRequest;

public interface Services <T, ID> {
	
	void addNew(T t);
	Iterable <T> fetchAll();
	T fetchById(ID id);
	boolean validateUser(T t, HttpServletRequest request);
}
