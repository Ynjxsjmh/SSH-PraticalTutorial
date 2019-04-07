package com.atcast.service;

import com.atcast.domain.User;

public interface UserService {

	User checkCode(String user_code);

	void save(User user);

	User login(User user);

}
