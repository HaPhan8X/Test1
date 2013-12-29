package com.tvo.puzzle.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.tvo.puzzle.dao.MemberDao;
import com.tvo.puzzle.entity.Member;
import com.tvo.puzzle.entity.PointManagement;
import com.tvo.puzzle.entity.PresentManagement;

@Repository
public class MemberDaoImpl extends GenericDaoImpl<Member, Integer> implements MemberDao {

	public MemberDaoImpl(){		
		super(Member.class);
	}
	
	@Override
	public void saveMember(final Member m) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				session.saveOrUpdate(m);
				return m;
			}
		};
		getHibernateTemplate().execute(callback);
	}

	@Override
	public void savePointLog(final PointManagement point) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				session.saveOrUpdate(point);
				return point;
			}
		};
		getHibernateTemplate().execute(callback);
	}

	@Override
	public void savePresent(final PresentManagement pm) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				session.saveOrUpdate(pm);
				return pm;
			}
		};
		getHibernateTemplate().execute(callback);
	}
}
