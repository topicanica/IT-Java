package com.anica.vjezba6.service;

public interface SecurityService {
	boolean isAuthenticated();

	String authenticatedUsername();

	void autoLogin(String username, String password);
}
