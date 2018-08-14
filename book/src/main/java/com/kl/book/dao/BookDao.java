package com.kl.book.dao;

import java.util.List;

import com.kl.book.pojo.Book;

public interface BookDao {

	List<Book> findBykId(String kId);

	void updateBook(Book book);

	List<Book> findAll();

	List<Book> findByLike(String bName);

	Book findByName(String getbName);

	void addBook(Book book);

	Book findById(String id);

	void deleteById(Book book);

}
