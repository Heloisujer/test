package com.kl.book.service;

import java.util.List;

import com.kl.book.pojo.Message;
import com.kl.book.pojo.User;

public interface UserService {

	Message login(User user);

	List<User> findAll();

	void addUsers(List<User> users);

	Message deleteUser(String uId);

	Message updateUser(User user);

	Message addUser(User user);

	Message changePwd(User currentUser, String uPwd1);

}
