package com.kl.book.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import com.kl.book.dao.UserDao;
import com.kl.book.pojo.Message;
import com.kl.book.pojo.User;
import com.kl.book.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Resource
	private UserDao userDao;

	public Message login(User user) {
		// TODO Auto-generated method stub
		Message msg = new Message();
		User user1 = userDao.findByName(user.getuName());
		if (user1!=null) {
			String pwd = user.getuPwd();
			Md5Hash md5 = new Md5Hash(pwd);
			String pwd1 = md5.toString();
			if (user1.getuPwd().equals(pwd1)) {
				msg.setUser(user1);
				msg.setMsg("success");
				return msg;
			}else {
				msg.setMsg("�������");
				return msg;
			}
		}else {
			msg.setMsg("�û�������");
			return msg;
		}
		
	}

	public List<User> findAll() {
		// TODO Auto-generated method stub
		
		return userDao.findAll();
	}

	public Message addUser(User user) {
		// TODO Auto-generated method stub
		Message msg = new Message();
		User user1 = userDao.findByName(user.getuName());
		if (user1==null) {
			String id=UUID.randomUUID().toString().substring(0,4);
			Md5Hash md5 = new Md5Hash("123");
			String pwd = md5.toString();
			user.setId(id);
			user.setuPwd(pwd);
			userDao.addUser(user);
			msg.setMsg("��ӳɹ���");
			return msg;
		}else {
			msg.setMsg("���û��Ѵ��ڣ������ظ���ӣ�");
			return msg;
		}
	}

	public Message deleteUser(String uId) {
		// TODO Auto-generated method stub
		Message msg = new Message();
		User user = userDao.findById(uId);
		if (user!=null) {
			userDao.deleteUser(user);
			msg.setMsg("ɾ���ɹ���");
			return msg;
		}else {
			
			msg.setMsg("���û������ڣ�");
			return msg;
		}
	}

	public Message updateUser(User user) {
		// TODO Auto-generated method stub
		Message msg = new Message();
		User user1 = userDao.findById(user.getId());
		if (user1!=null) {
			userDao.updateUser(user);
			msg.setMsg("�޸ĳɹ���");
			return msg;
		}else {
			msg.setMsg("�ö��󲻴���");
			return msg;
		}
	}

	public void addUsers(List<User> users) {
		// TODO Auto-generated method stub
		for (User user : users) {
			String id =UUID.randomUUID().toString().substring(0,4);
			Md5Hash md5 = new Md5Hash("123");
			String uPwd = md5.toString();
			user.setId(id);
			user.setuPwd(uPwd);
			userDao.addUser(user);
		}
		
	}

	public Message changePwd(User currentUser, String uPwd1) {
		// TODO Auto-generated method stub
		Message msg = new Message();
		Md5Hash md5 = new Md5Hash(uPwd1);
		uPwd1 = md5.toString();
		currentUser.setuPwd(uPwd1);
		userDao.updateUser(currentUser);
		msg.setStatus(1);
		msg.setMsg("�����޸ĳɹ���");
		return msg;
	}
	

}
