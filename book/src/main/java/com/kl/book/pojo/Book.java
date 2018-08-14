package com.kl.book.pojo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 图书类
 * @author 胡家兴
 *
 */

@Entity
@Table
public class Book implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	//表示ID是主键
	@Column(length = 20)
	private String id;
	
	@Column(length = 20)
	private String bName;
	
	@Column(length = 20)
	private String author;
	
	@Column(length = 100)
	private String intro;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Kind getKind() {
		return kind;
	}

	public void setKind(Kind kind) {
		this.kind = kind;
	}

	@Column(length = 100)
	private String address;
	
	@ManyToOne
	@JoinColumn(name = "kind_id")//kind的id作为外键
	private Kind kind;
	
	
}
