package com.tvo.puzzle.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.tvo.puzzle.dao.UserDao;
import com.tvo.puzzle.entity.User;
import com.tvo.puzzle.util.PasswordSupport;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements UserDao
{
	public UserDaoImpl()
	{
		super(User.class);
	}
	@Override
	public List<User> findRangerUsers(final Integer limit,final Integer offset) {
		HibernateCallback callback = new HibernateCallback()
		{
			public Object doInHibernate(Session session)
					throws HibernateException
			{
				StringBuilder sqlBuilder = new StringBuilder();
				sqlBuilder.append("FROM User u WHERE u.id NOT IN (1)");
				Query query = session.createQuery(sqlBuilder.toString());
				query.setMaxResults(limit);
				query.setFirstResult(offset);
				return query.list();
			}
		};
		return (List<User>) getHibernateTemplate().execute(callback);
	}
	@Override
	public int countUsers() {
		HibernateCallback callback = new HibernateCallback()
		{
			public Object doInHibernate(Session session)
					throws HibernateException
			{
				StringBuilder sqlBuilder = new StringBuilder();
				sqlBuilder.append("SELECT COUNT(*) FROM User u WHERE u.id NOT IN (1)");
				Query query = session.createQuery(sqlBuilder.toString());
				return Integer.parseInt(query.uniqueResult().toString());
			}
		};
		return getHibernateTemplate().execute(callback);
	}
	@Override
	public User doLogin(final String userName,final String password) {
		HibernateCallback callback = new HibernateCallback()
		{
			public Object doInHibernate(Session session)
					throws HibernateException
			{
				StringBuilder sqlBuilder = new StringBuilder();
				sqlBuilder.append("SELECT u.* FROM tbl_user u WHERE u.user_name=:username AND u.password=:password");
				SQLQuery query = session.createSQLQuery(sqlBuilder.toString());
				query.setParameter("username",userName );
				query.setParameter("password",password );
				query.addEntity(User.class);
				return query.uniqueResult();
			}
		};
		return  getHibernateTemplate().execute(callback);
	}
	@Override
	public User searchByUsername(final String userName) {
		HibernateCallback callback = new HibernateCallback()
		{
			public Object doInHibernate(Session session)
					throws HibernateException
			{
				Query query = session
						.createQuery("FROM User u WHERE u.userName=:userName ");
				query.setString("userName", userName);
				if (query.list().size() > 0)
				{
					return query.list().get(0);
				}
				else
				{
					return null;
				}
			}
		};
		return (User) getHibernateTemplate().execute(callback);
	}
	@Override
	public User getUserByUsernameAndPassword(final String username, final String password) {
		HibernateCallback callback = new HibernateCallback()
		{
			public Object doInHibernate(Session session)
					throws HibernateException
			{
				Query query = session
						.createQuery("FROM User u Where u.userName=:username AND u.password =:password");
				query.setString("username", username);
				query.setString("password",
						PasswordSupport.getMD5Hash(password));
				if (query.list().size() > 0)
				{
					return query.list().get(0);
				}
				else
				{
					return null;
				}
			}
		};
		return (User) getHibernateTemplate().execute(callback);
	}
}
