package com.kl.book.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kl.book.dao.BookDao;
import com.kl.book.dao.KindDao;
import com.kl.book.pojo.Book;
import com.kl.book.pojo.Kind;
import com.kl.book.pojo.Message;
import com.kl.book.service.BookService;
import com.kl.book.utils.UpUtils;

@Service
@Transactional
public class BookServiceImpl implements BookService{
	
	@Resource
	private BookDao bookDao;
	
	@Resource
	private KindDao kindDao;

	public List<Book> findAll() {
		// TODO Auto-generated method stub
		return bookDao.findAll();
	}

	public List<Book> findByLike(String bName) {
		// TODO Auto-generated method stub
		return bookDao.findByLike(bName);
	}

	public Message addBook(Book book, String type,MultipartFile file,HttpServletRequest request) {
		// TODO Auto-generated method stub
		Message msg =new Message();
		Kind kind = kindDao.findByType(type);
		if (kind!=null) {
			Book book1 = bookDao.findByName(book.getbName());
			if (book1==null) {
				String id =UUID.randomUUID().toString().substring(0,4);
				book.setId(id);
				book.setKind(kind);
				if (file.getSize()>0) {
		        String imgPath = UpUtils.upfile(file, request);
				book.setAddress(imgPath);
				}else {
					book.setAddress("");
				}
				bookDao.addBook(book);
				msg.setMsg("添加成功！");
				return msg;
			}else {
				msg.setMsg("该书已存在，请不要重复添加！");
				return msg;
			}
			
		} else {
			msg.setMsg("该类型不存在！");
			return msg;

		}
	}

	public Message updateBook(Book book, String type, MultipartFile file,HttpServletRequest request) {
		// TODO Auto-generated method stub
		Message msg =new Message();
		Kind kind = kindDao.findByType(type);
		if (kind!=null) {
			Book book1 = bookDao.findById(book.getId());
			if (book1!=null) {
				book.setKind(kind);
				if (file.getSize()>0) {
					String imgPath = UpUtils.upfile(file, request);
					book.setAddress(imgPath);
				}else {
					book.setAddress(book1.getAddress());
				}
				bookDao.updateBook(book);
				msg.setMsg("修改成功！！！");
				return msg;
			}else {
				msg.setMsg("该图书不存在！");
				return msg;
			}
		}else {
			msg.setMsg("该类型不存在！");
			return msg;
		}
	}



	public Message deleteById(String bId) {
		// TODO Auto-generated method stub
		Message msg =new Message();
		Book book = bookDao.findById(bId);
		if (book!=null) {
			bookDao.deleteById(book);
			msg.setMsg("删除成功！！！");
			return msg;
		}else {
			msg.setMsg("该对象已经被删除！");
			return msg;
		}
	}
	
}
