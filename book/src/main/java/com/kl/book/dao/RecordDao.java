package com.kl.book.dao;

import java.util.List;

import org.apache.poi.ss.util.SSCellRange;

import com.kl.book.pojo.Record;

public interface RecordDao {

	void borrow(Record record);

	List<Record> findByLike(String uName);

	List<Record> findAll();

	Record findById(String rId);

	void updateRecord(Record record);

}
