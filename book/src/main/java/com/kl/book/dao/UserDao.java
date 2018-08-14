package com.kl.book.dao;

import java.util.List;

import com.kl.book.pojo.User;

public interface UserDao {

	User findByName(String uName);

	List<User> findAll();

	void addUser(User user);

	User findById(String uId);

	void deleteUser(User user);

	void updateUser(User user);

	

}
