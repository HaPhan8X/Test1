package com.tvo.puzzle.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.tvo.puzzle.dao.SessionDao;
import com.tvo.puzzle.entity.Session;

@Repository
public class SessionDaoImpl extends GenericDaoImpl<Session, Integer> implements SessionDao
{
	public SessionDaoImpl()
	{
		super(Session.class);
	}

	public List<Session> findByPlayer(final int playerId) {
		HibernateCallback callback = new HibernateCallback()
		{
			public Object doInHibernate(org.hibernate.Session session) throws HibernateException
			{
				Query query = session.createQuery("FROM Session s WHERE s.playerId=:playerId ");
				query.setInteger("playerId", playerId);
				return query.list();
			}
		};
		return getHibernateTemplate().execute(callback);
	}

	public Session findBySession(final String sessionId) {
		HibernateCallback callback = new HibernateCallback()
		{
			public Object doInHibernate(org.hibernate.Session session) throws HibernateException
			{
				Query query = session
						.createQuery("FROM Session s WHERE s.sessionId=:sessionId");
				query.setString("sessionId", sessionId);
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
		return getHibernateTemplate().execute(callback);
	}

	public void deleteByPlayer(final int playerId) {
		HibernateCallback callback = new HibernateCallback()
		{
			public Object doInHibernate(org.hibernate.Session session) throws HibernateException
			{
				Query query = session
						.createQuery("DELETE FROM Session s WHERE s.playerId=:playerId");
				query.setInteger("playerId", playerId);
				return query.executeUpdate();
			}
		};
		getHibernateTemplate().execute(callback);
	}
}
