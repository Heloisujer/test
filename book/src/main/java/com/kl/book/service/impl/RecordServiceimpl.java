package com.kl.book.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.coyote.http2.HPackHuffman;
import org.apache.el.parser.ELParserTreeConstants;
import org.apache.xmlbeans.impl.jam.mutable.MMember;
import org.springframework.stereotype.Service;

import com.kl.book.dao.BookDao;
import com.kl.book.dao.RecordDao;
import com.kl.book.pojo.Book;
import com.kl.book.pojo.Message;
import com.kl.book.pojo.Record;
import com.kl.book.service.RecordService;

/**
 * @author 胡家兴
 *
 */
@Service
@Transactional
public class RecordServiceimpl implements RecordService{
	
	@Resource
	private RecordDao recordDao;
	
	@Resource
	private BookDao bookDao;

	public Message borrow(Record record) {
		// TODO Auto-generated method stub
		Message msg = new Message();
		Book book = bookDao.findByName(record.getbName());
		if (book!=null) {
			record.setState("已借");
		    String id = UUID.randomUUID().toString().substring(0,4);
		    record.setId(id);
		    recordDao.borrow(record);
		msg.setMsg("借书成功！！！");
		return msg;
		}else {
			msg.setMsg("无该图书！");
			return msg;
		}
		
	}

	public List<Record> findByLike(String uName) {
		// TODO Auto-generated method stub
		return recordDao.findByLike(uName);
	}

	public List<Record> findAll() {
		// TODO Auto-generated method stub
		
		return recordDao.findAll();
	}
	public Message updateRecored(String rId) {
		// TODO Auto-generated method stub
		Message msg = new Message();
		Record record = recordDao.findById(rId);
		if (record!=null) {
			record.setState("已还");
			recordDao.updateRecord(record);
			msg.setMsg("归还成功");
			return msg;
		}else {
			msg.setMsg("该记录不存在！");
			return msg;
		}
	
	}

}




