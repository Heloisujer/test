package com.kl.book.service;

import java.util.List;

import com.kl.book.pojo.Kind;
import com.kl.book.pojo.Message;

public interface KindService {

	List<Kind> findAll();

	Message addKind(Kind kind);

	Message updateKind(Kind kind);

	Message deleteById(String kId);

}
