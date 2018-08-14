package com.kl.book.service;

import java.util.List;

import com.kl.book.pojo.Message;
import com.kl.book.pojo.Record;

public interface RecordService {

	Message borrow(Record record);

	List<Record> findByLike(String uName);

	List<Record> findAll();

	Message updateRecored(String rId);

}
