package com.anica.vjezba6.service;

import com.anica.vjezba6.model.Configuration;
import com.anica.vjezba6.model.User;

public interface UserService {
	void save(User user);

	User findByUsername(String username);

	void addConfiguration(Configuration configuration, String username);

	Configuration getConfiguration(String username);
}
