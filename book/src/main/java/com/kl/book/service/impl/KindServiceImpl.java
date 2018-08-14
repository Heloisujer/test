package com.kl.book.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.kl.book.dao.BookDao;
import com.kl.book.dao.KindDao;
import com.kl.book.pojo.Book;
import com.kl.book.pojo.Kind;
import com.kl.book.pojo.Message;
import com.kl.book.service.KindService;

@Service
@Transactional
public class KindServiceImpl implements KindService{
	
	@Resource
	private KindDao kindDao;
	
	@Resource
	private BookDao bookDao;

	public List<Kind> findAll() {
		// TODO Auto-generated method stub
		List<Kind> kinds = kindDao.findAll();
		
		return kinds;
	}

	public Message addKind(Kind kind) {
		// TODO Auto-generated method stub
		Message msg = new Message();
		Kind kind1 = kindDao.findByType(kind.getType());
		if (kind1==null) {
			String id = UUID.randomUUID().toString().substring(0, 4);
			kind.setId(id);
			kindDao.addKind(kind);
			msg.setKind(kind);
			msg.setMsg("添加成功！！！");
			return msg;
			
		}else {
			msg.setMsg("该类型已存在！");
			return msg;
		}
		
	}

	public Message updateKind(Kind kind) {
		// TODO Auto-generated method stub
		Message msg = new Message();
		Kind kind1 = kindDao.findByType(kind.getType());
		if (kind1==null) {
			kindDao.updateKind(kind);
			msg.setKind(kind);
			msg.setMsg("修改成功！");
			return msg;
		}else {
			msg.setMsg("输入的类型已经存在！");
			return msg;
		}
		
	}

	public Message deleteById(String kId) {
		// TODO Auto-generated method stub
		Message msg = new Message();
		Kind kind =kindDao.findById(kId);
		if (kind!=null) {
			List<Book> books = bookDao.findBykId(kId);
			for (Book book : books) {
				book.setKind(null);
				bookDao.updateBook(book);
			}
			kindDao.deleteById(kind);
			msg.setMsg("删除成功！！！");
			return msg;
		}else {
			msg.setMsg("该对象不存在！");
			return msg;
		}
	}

}
