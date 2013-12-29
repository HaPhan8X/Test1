package com.tvo.puzzle.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tvo.puzzle.dao.GameDao;
import com.tvo.puzzle.entity.Game;
import com.tvo.puzzle.entity.GameDetail;


@Repository
public class GameDaoImpl extends GenericDaoImpl<Game, Integer> implements GameDao{

	public GameDaoImpl() {
		super(Game.class);
	}

	@Transactional
	public List<Game> getGameList() {
		HibernateCallback callback = new HibernateCallback()
		{
			public Object doInHibernate(Session session)
					throws HibernateException
			{
				String sql = "FROM Game";
				Query query = session.createQuery(sql);
				return query.list();
			}
		};
		return (List<Game>) getHibernateTemplate().execute(callback);
	}

	@Transactional
	public List<GameDetail> getGameDetails(final Integer selectedGame,final int limit,final int offset) {
		HibernateCallback callback = new HibernateCallback()
		{
			public Object doInHibernate(Session session)
					throws HibernateException
			{
				Criteria c = session.createCriteria(GameDetail.class);
				if(selectedGame != null){
					if(selectedGame != 0)
					c.add(Restrictions.eq("tblGame.id",selectedGame));
				}
				c.addOrder(Property.forName("id").desc());
				c.setFirstResult(offset);
				c.setMaxResults(limit);
				return c.list();
			}
		};
		return (List<GameDetail>) getHibernateTemplate().execute(callback);
	}

	@Transactional
	public Integer countAll(final Integer selectedGame) {
		HibernateCallback callback = new HibernateCallback()
		{
			public Object doInHibernate(Session session)
					throws HibernateException
			{
				Criteria c = session.createCriteria(GameDetail.class);
				if( selectedGame != null){
					if(selectedGame != 0)
					c.add(Restrictions.eq("tblGame.id",selectedGame));
				}
				c.setProjection(Projections.rowCount());
				return c.list().get(0);
			}
		};
		return ((Long) getHibernateTemplate().execute(callback))
				.intValue();
	}

	@Transactional
	public void saveGameDetail(final GameDetail gd) {
		HibernateCallback callback = new HibernateCallback()
		{
			public Object doInHibernate(Session session)
					throws HibernateException
			{
				session.save(gd);
				return gd;
			}
		};
		getHibernateTemplate().execute(callback);
	}

	@Override
	public int countScreenByDate(final Integer selectedGame,final String date) {
		HibernateCallback callback = new HibernateCallback()
		{
			public Object doInHibernate(Session session)
					throws HibernateException
			{
				Criteria c = session.createCriteria(GameDetail.class);
				c.add(Restrictions.eq("tblGame.id",selectedGame));	
				c.add(Restrictions.eq("folderName", date));
				c.setProjection(Projections.rowCount());
				return c.list().get(0);
			}
		};
		return ((Long) getHibernateTemplate().execute(callback))
				.intValue();
	}

	@Override
	public GameDetail getById(final int id) {
		HibernateCallback callback = new HibernateCallback()
		{
			public Object doInHibernate(Session session)
					throws HibernateException
			{
				GameDetail g = (GameDetail) session.get(GameDetail.class, id);
				return g;
			}
		};
		return getHibernateTemplate().execute(callback);
	}

}
