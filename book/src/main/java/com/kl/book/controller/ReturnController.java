package com.kl.book.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kl.book.pojo.Message;
import com.kl.book.pojo.Record;
import com.kl.book.service.RecordService;

@Controller
@RequestMapping("/returns")
public class ReturnController {
	@Resource
	private RecordService recordService;
	
	@RequestMapping("/findByLike")
	public ModelAndView findByLike(String uName) {
		ModelAndView mav = new ModelAndView();
		List<Record> records =recordService.findByLike(uName);
		 mav.setViewName("/return.jsp");
		 mav.addObject("records",records);
		 return mav;
		
		
	}

	@RequestMapping("/findAll")
	public ModelAndView findAll() {
		ModelAndView mav = new ModelAndView();
		List<Record> records =recordService.findAll();
		 mav.setViewName("/return.jsp");
		 mav.addObject("records",records);
		 return mav;
		
		
	}
	
	@RequestMapping("/returnBook")
	@ResponseBody
	public Message returnBook(String rId) {
		 try {
    		 Message msg= recordService.updateRecored(rId);
    		 return msg;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Message msg = new Message();
			msg.setMsg("²Ù×÷Òì³££¡");
			return msg;
		}
		
	}

}
