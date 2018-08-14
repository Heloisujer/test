package com.kl.book.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.xmlbeans.impl.xb.xsdschema.Attribute.Use;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.kl.book.dao.BookDao;
import com.kl.book.pojo.Book;

@Repository
public class BookDaoImpl implements BookDao{
	
	@Resource
	private SessionFactory sessionFactory;

	public List<Book> findBykId(String kId) {
		// TODO Auto-generated method stub
		String hql = "from Book b where b.kind.id=:kId";
		return sessionFactory.getCurrentSession().createQuery(hql,Book.class).setParameter("kId", kId).getResultList();
	}

	public void updateBook(Book book) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().merge(book);
	}

	public List<Book> findAll() {
		// TODO Auto-generated method stub
		String hql = "from Book b order by b.id";
		
		return sessionFactory.getCurrentSession().createQuery(hql,Book.class).getResultList();
	}

	public List<Book> findByLike(String bName) {
		// TODO Auto-generated method stub
		String hql = "from Book b where b.bName like :bName";
		return sessionFactory.getCurrentSession().createQuery(hql,Book.class).setParameter("bName","%"+bName+"%").getResultList();
	}

	public Book findByName(String bName) {
		// TODO Auto-generated method stub
		String hql = "from Book b where b.bName=:bName";
		return sessionFactory.getCurrentSession().createQuery(hql,Book.class).setParameter("bName",bName).uniqueResult();
	}

	public void addBook(Book book) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().persist(book);
	}

	public Book findById(String id) {
		// TODO Auto-generated method stub
		String hql ="from Book b where b.id=:id";
		return sessionFactory.getCurrentSession().createQuery(hql,Book.class).setParameter("id",id).uniqueResult();
	}

	public void deleteById(Book book) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(book);
	}
	
	

}
