package com.kl.book.dao;

import java.util.List;

import com.kl.book.pojo.Kind;

public interface KindDao {

	List<Kind> findAll();

	Kind findByType(String type);

	void addKind(Kind kind);

	void updateKind(Kind kind);

	Kind findById(String kId);

	void deleteById(Kind kind);


	

}
