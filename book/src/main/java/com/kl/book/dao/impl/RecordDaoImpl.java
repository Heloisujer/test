package com.kl.book.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.TTCCLayout;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute.Use;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

import com.kl.book.dao.RecordDao;
import com.kl.book.pojo.Record;

/**
 * @author ºú¼ÒÐË
 *
 */
@Repository
public class RecordDaoImpl implements RecordDao{
	@Resource
	private SessionFactory sessionFactory;

	public void borrow(Record record) {
		// TODO Auto-generated method stub
		
		sessionFactory.getCurrentSession().persist(record);
	}
	public List<Record> findByLike(String uName) {
		// TODO Auto-generated method stub
		String hql ="from Record r where r.uName like :uName";
		return sessionFactory.getCurrentSession().createQuery(hql,Record.class).setParameter("uName","%"+uName+"%").getResultList();
	}

	public List<Record> findAll() {
		// TODO Auto-generated method stub
		String hql ="from Record r order by r.id";
		return sessionFactory.getCurrentSession().createQuery(hql,Record.class).getResultList();
	}
	public Record findById(String rId) {
		// TODO Auto-generated method stub
		String hql = "from Record r where r.id=:rId";
		
		return sessionFactory.getCurrentSession().createQuery(hql,Record.class).setParameter("rId", rId).uniqueResult();
	}
	public void updateRecord(Record record) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().merge(record);
	}

}





