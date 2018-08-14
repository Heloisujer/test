package com.kl.book.dao.impl;

import java.util.List;

import javax.annotation.Resource;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute.Use;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.kl.book.dao.KindDao;
import com.kl.book.pojo.Kind;

@Repository
public class KindDaoImpl implements KindDao{
	
	@Resource
	private SessionFactory sessionFactory;

	public List<Kind> findAll() {
		// TODO Auto-generated method stub
		String hql = "from Kind k order by k.id";
		List<Kind> kinds = sessionFactory.getCurrentSession().createQuery(hql,Kind.class).getResultList();
		return kinds;
	}

	public Kind findByType(String type) {
		// TODO Auto-generated method stub
		String hql ="from Kind k where k.type=:type";
		Kind kind = sessionFactory.getCurrentSession().createQuery(hql,Kind.class).setParameter("type",type).uniqueResult();
		return kind;
	}

	public void addKind(Kind kind) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().persist(kind);
		
	}

	public void updateKind(Kind kind) {
		// TODO Auto-generated method stub
//		sessionFactory.getCurrentSession().update(kind);
		sessionFactory.getCurrentSession().merge(kind);
		
	}

	public Kind findById(String kId) {
		// TODO Auto-generated method stub
		String hql ="from Kind k where k.id =:kId";
		return sessionFactory.getCurrentSession().createQuery(hql,Kind.class).setParameter("kId",kId).uniqueResult();
	}

	public void deleteById(Kind kind) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(kind);
	}

}
