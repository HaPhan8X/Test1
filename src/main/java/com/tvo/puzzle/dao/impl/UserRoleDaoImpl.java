package com.tvo.puzzle.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.tvo.puzzle.dao.UserRoleDao;
import com.tvo.puzzle.entity.UserRole;

@Repository
public class UserRoleDaoImpl extends GenericDaoImpl<UserRole, Integer> implements UserRoleDao
{
	public UserRoleDaoImpl()
	{
		super(UserRole.class);
	}

	@Override
	public List<UserRole> getByUser(final Integer userId) {
		HibernateCallback callback = new HibernateCallback()
		{
			public Object doInHibernate(Session session)
					throws HibernateException
			{
				StringBuilder sqlBuilder = new StringBuilder();
				sqlBuilder.append("FROM UserRole ur WHERE ur.tblUser.id=");
				sqlBuilder.append(userId);
				Query query = session.createQuery(sqlBuilder.toString());
				return query.list();
			}
		};
		return (List<UserRole>) getHibernateTemplate().execute(callback);
	}

	@Override
	public void deleteByUserID(final Integer id) {
		HibernateCallback callback = new HibernateCallback()
		{
			public Object doInHibernate(Session session) throws HibernateException
			{
				Query query = session
						.createQuery("DELETE FROM UserRole u WHERE u.tblUser.id=:id ");
				query.setInteger("id", id);
				return query.executeUpdate();
			}
		};
		getHibernateTemplate().execute(callback);
		
	}

}

