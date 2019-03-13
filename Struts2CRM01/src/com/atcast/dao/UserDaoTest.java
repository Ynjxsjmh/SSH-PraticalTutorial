package com.atcast.dao;

import org.junit.Test;
import com.atcast.domain.User;

public class UserDaoTest {
	@Test
	public void testFindByNameAndPwd() {
		UserDao userDao = new UserDao();
		User user = new User();
		user.setUsername("123");
		user.setPassword("123");
		user = userDao.findByNameAndPwd(user);
		System.out.println(user);
	}
	
	public static void main() {
		UserDao userDao = new UserDao();
		User user = new User();
		user.setUsername("123");
		user.setPassword("123");
		user = userDao.findByNameAndPwd(user);
		System.out.println(user);
	}
}
