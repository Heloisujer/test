package com.kl.book.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.xpath.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kl.book.pojo.Book;
import com.kl.book.pojo.Message;
import com.kl.book.service.BookService;
import com.kl.book.utils.UpUtils;


/**
 * 图书信息控制层
 * @ClassName UserController
 * @Description TODO
 * @author 胡家兴
 *
 */

@Controller
@RequestMapping("/books")
public class BookController {
	
	@Resource
	private BookService bookService;
	
	@RequestMapping("/findAll")
	public ModelAndView findAll() {
		ModelAndView mav = new ModelAndView();
		List<Book> books = bookService.findAll();
		mav.setViewName("/book.jsp");
		mav.addObject("books",books);
		return mav;
	}
	

	@RequestMapping("/findByLike")
	public ModelAndView findByLike(String bName) {
		ModelAndView mav = new ModelAndView();
		List<Book> books = bookService.findByLike(bName);
		mav.setViewName("/book.jsp");
		mav.addObject("books",books);
		return mav;
	}
	
	@RequestMapping("/addBook")
	@ResponseBody
	public Message addBook(Book book,String type,MultipartFile file,HttpServletRequest request) {
		try {
			Message msg = bookService.addBook(book,type,file,request);
		    return msg;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Message msg = new Message();
			msg.setMsg("操作异常！");
			return msg;
		}
		
		
	}
	@RequestMapping("/updateBook")
	@ResponseBody
	public Message updateBook(Book book,String type,MultipartFile file,HttpServletRequest request) {
		try {
			Message msg = bookService.updateBook(book,type,file,request);
			return msg;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Message msg = new Message();
			msg.setMsg("操作异常！");
			return msg;
		}
		
		
	}
	@RequestMapping("/deleteBook")
	@ResponseBody
	public Message deleteBook(String bId) {
		try {
			Message msg = bookService.deleteById(bId);
			return msg;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Message msg = new Message();
			msg.setMsg("操作异常！");
			return msg;
		}
		
		
	}
	
	@RequestMapping("/up")
	public void upImage(MultipartFile file,HttpServletRequest request) {
		String imgPath = UpUtils.upfile(file, request);
		System.out.println(imgPath);
	}


}
