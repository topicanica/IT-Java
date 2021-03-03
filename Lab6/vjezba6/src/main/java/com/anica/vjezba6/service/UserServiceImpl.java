package com.anica.vjezba6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.anica.vjezba6.model.Configuration;
import com.anica.vjezba6.model.User;
import com.anica.vjezba6.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void addConfiguration(Configuration configuration, String username) {
		User user = userRepository.findByUsername(username);
		if (user.getConfiguration() == null) {
			user.setConfiguration(configuration);
			userRepository.save(user);
		}
	}

	@Override
	public Configuration getConfiguration(String username) {
		User user = userRepository.findByUsername(username);
		if (user.getConfiguration() != null) {
			Configuration configuration = user.getConfiguration();
			return configuration;
		} else
			return null;

	}

}
