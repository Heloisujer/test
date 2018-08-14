package com.kl.book.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kl.book.pojo.Message;
import com.kl.book.pojo.Record;
import com.kl.book.service.RecordService;

@Controller
@RequestMapping("/borrows")
public class BorrowController {
 @Resource
 public RecordService recordService;
 @RequestMapping("/borrow")
 public ModelAndView borrow(Record record) {
	 ModelAndView mav= new ModelAndView();
	 try {
		
		 Message msg=recordService.borrow(record);
		 mav.setViewName("/borrow.jsp");
		 mav.addObject("msg",msg);
		 return mav;
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		Message msg= new Message();
		msg.setMsg("²Ù×÷Òì³£");
		mav.setViewName("/borrow.jsp");
		mav.addObject("msg",msg);
		return mav;
	}
 }
}

