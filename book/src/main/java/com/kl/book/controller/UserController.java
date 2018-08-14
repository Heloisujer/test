package com.kl.book.controller;

import java.io.File;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kl.book.pojo.Message;
import com.kl.book.pojo.User;
import com.kl.book.service.UserService;


/**
 * 用户控制层
 * @ClassName UserController
 * @Description TODO
 * @author 胡家兴
 * 
 */

@Controller
@RequestMapping("/users")
public class UserController {
	
	//@Autowired
	@Resource
	private UserService userService;
	
	@RequestMapping("/login")
	public ModelAndView login(User user,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Message msg = userService.login(user);
		if (msg.getMsg().equals("success")) {
//			msg.setMsg("");
			request.getSession().setAttribute("msg1",msg);
//			mav.addObject("msg",msg);
			mav.setViewName("/index.jsp");
			return mav;
		}else {
			mav.addObject("msg1",msg);
			mav.setViewName("/login.jsp");
			return mav;
		}
		
		
	}
	@RequestMapping("/findAll")
	public ModelAndView findAll() {
		ModelAndView mav = new ModelAndView();
		List<User> users =userService.findAll();
		mav.addObject("users",users);
		mav.setViewName("/userList.jsp");
		return mav;
	}
	
	@RequestMapping("/addUser")
	@ResponseBody
	public Message addUser(User user) {
		try {
			Message msg = userService.addUser(user);
			return msg;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Message msg =new Message();
			msg.setMsg("操作异常！");
			return msg;
		}
		
	}
	
	@RequestMapping("/deleteUser")
	@ResponseBody
	public Message deleteUser(String uId) {
		try {
			Message msg = userService.deleteUser(uId);
			return msg;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Message msg =new Message();
			msg.setMsg("操作异常！");
			return msg;
		}
	}
	@RequestMapping("/updateUser")
	@ResponseBody
	public Message updateUser(User user) {
		try {
			Message msg =userService.updateUser(user);
			return msg;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Message msg =new Message();
			msg.setMsg("操作异常！");
			return msg;
		}
	}
	@RequestMapping("/poi")
	@ResponseBody
	public Message poi(MultipartFile file,HttpServletRequest request) {
		//声明book模板 存放excel文件
		Workbook book = null;
		Message msg = new Message();
		if(file.getSize()==0) {
			msg.setMsg("请选择上传文件！");
			return msg;
		}
		try {
			//判断excel是否是最新版本  .xlsx为最新版本
			if (file.getOriginalFilename().endsWith(".xlsx")) {
				book = new XSSFWorkbook(file.getInputStream());
			}else {
				book =  new XSSFWorkbook(file.getInputStream());
			}
			//获取excel文件中的第一张表
			Sheet sheet = book.getSheetAt(0);
			if (sheet.getLastRowNum()<1) {
				msg.setMsg("请确认上传文件的内容是否有值！");
				return msg;
			}
			List<User> users = new ArrayList<User>();
			for (int i = 1; i < sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				String uName = row.getCell(0).getStringCellValue();
				String name = row.getCell(1).getStringCellValue();
				String phone = row.getCell(2).getStringCellValue();
				String email = row.getCell(3).getStringCellValue();
				User user = new User();
				user.setuName(uName);
				user.setName(name);
				user.setPhone(phone);
				user.setEmail(email);
				users.add(user);
			}
			try {
				userService.addUsers(users);
				msg.setMsg("添加成功！");
				return msg;
			} catch (Exception e) {
				// TODO: handle exception
				msg.setMsg("请核对上传信息是否重复！");
				return msg;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setMsg("操作异常！");
			return msg;
		}
		
	}
	@RequestMapping("/checkPwd")
	@ResponseBody
	public Message checkPwd(String opwd,HttpSession session) {
		Message message = (Message) session.getAttribute("msg1");
		User currentUser = message.getUser();
		Md5Hash md5 = new Md5Hash(opwd);
		opwd = md5.toString();
		if(opwd.equals(currentUser.getuPwd())) {
			message.setStatus(1);
			message.setMsg("密码正确！");
			return message;
		}else {
			message.setStatus(0);
			message.setMsg("密码错误！");
			return message;
		}
		
	}
	
	@RequestMapping("/changePwd")
	@ResponseBody
	public Message changePwd(String uPwd1,String uPwd2,HttpSession session) {
		Message msg = new Message();
		if(!uPwd1.equals(uPwd2)) {
			msg.setStatus(0);
			msg.setMsg("密码输入不一致！");
			return msg;
		}
		Message message2 = (Message) session.getAttribute("msg1");
		User currentUser = message2.getUser();
		try {
			msg = userService.changePwd(currentUser,uPwd1);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setStatus(0);
			msg.setMsg("操作异常！");
		}
		return msg;
	}

}







