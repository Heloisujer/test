package com.kl.book.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.kl.book.pojo.Book;
import com.kl.book.pojo.Message;

public interface BookService {

	List<Book> findAll();

	List<Book> findByLike(String bName);

	Message addBook(Book book, String type, MultipartFile file,HttpServletRequest request);

	Message updateBook(Book book, String type, MultipartFile file,HttpServletRequest request);
	
	Message deleteById(String bId);


}
