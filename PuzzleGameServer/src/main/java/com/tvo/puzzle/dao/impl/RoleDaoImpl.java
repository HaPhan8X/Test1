package com.tvo.puzzle.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.tvo.puzzle.dao.RoleDao;
import com.tvo.puzzle.entity.Role;

@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role, Integer> implements RoleDao
{
	public RoleDaoImpl()
	{
		super(Role.class);
	}

	@Override
	public List<Role> findRoles() {
		HibernateCallback callback = new HibernateCallback()
		{
			public Object doInHibernate(Session session)
					throws HibernateException
			{
				StringBuilder sqlBuilder = new StringBuilder();
				sqlBuilder.append("FROM Role r WHERE r.id NOT IN (1)");
				Query query = session.createQuery(sqlBuilder.toString());
				return query.list();
			}
		};
		return (List<Role>) getHibernateTemplate().execute(callback);
	}

	@Override
	public List<Role> getRoleByUser(final Integer userId) {
		HibernateCallback callback = new HibernateCallback()
		{
			public Object doInHibernate(Session session)
					throws HibernateException
			{
				StringBuilder sqlBuilder = new StringBuilder();
				sqlBuilder.append("SELECT r.* FROM tbl_role r ");
				sqlBuilder.append("INNER JOIN tbl_user_role ur ON ur.role_id = r.id ");
				sqlBuilder.append(" WHERE ur.user_id = :userId");

				SQLQuery query = session.createSQLQuery(sqlBuilder.toString());
				query.addEntity(Role.class);
				query.setInteger("userId", userId);
				return query.list();
			}
		};
		return (List<Role>) getHibernateTemplate().execute(callback);
	}
}

