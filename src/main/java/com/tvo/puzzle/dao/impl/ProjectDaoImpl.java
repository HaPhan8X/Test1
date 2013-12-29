package com.tvo.puzzle.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tvo.puzzle.dao.ProjectDao;
import com.tvo.puzzle.entity.Project;

@Repository
public class ProjectDaoImpl extends GenericDaoImpl<Project, Integer> implements
		ProjectDao {

	public ProjectDaoImpl() {
		super(Project.class);
	}

	@Override
	@Transactional
	public List<Project> getAll() {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuilder sqlBuilder = new StringBuilder();
				sqlBuilder.append("FROM Project where id > 1");
				Query query = session.createQuery(sqlBuilder.toString());
				return query.list();
			}
		};
		return (List<Project>)getHibernateTemplate().execute(callback);
	}

}
