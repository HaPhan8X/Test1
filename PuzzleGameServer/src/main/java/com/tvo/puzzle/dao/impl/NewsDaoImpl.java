package com.tvo.puzzle.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.tvo.puzzle.dao.NewsDao;
import com.tvo.puzzle.entity.News;

@Repository
public class NewsDaoImpl extends GenericDaoImpl<News, Integer> implements NewsDao {

	public NewsDaoImpl() {
		super(News.class);		
	}

	@Override
	public Integer countNewsByKeywordAndType(final String keyword, final String type) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuilder sqlBuilder = new StringBuilder();
				sqlBuilder.append("select COUNT(*) FROM tbl_news where id != 0");
				if(keyword != null && keyword.length() > 0)
				{
					sqlBuilder.append(" AND title like '%");
					sqlBuilder.append(keyword);
					sqlBuilder.append("%' ");
				}
				if(type != null && type.length() > 0)
				{
					sqlBuilder.append(" AND type like '%");
					sqlBuilder.append(type);
					sqlBuilder.append("%' ");
				}
				Query query = session.createSQLQuery(sqlBuilder.toString());
				return Integer.parseInt(query.uniqueResult().toString());
			}
		};
		return getHibernateTemplate().execute(callback);
	}

	@Override
	public List<News> findRangeNewsByKeywordAndType(final String keyword,
			final String type, final Integer limit, final Integer offset) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuilder sqlBuilder = new StringBuilder();
				sqlBuilder.append("select * FROM tbl_news where id != 0");
				if(keyword != null && keyword.length() > 0)
				{
					sqlBuilder.append(" AND title like '%");
					sqlBuilder.append(keyword);
					sqlBuilder.append("%' ");
				}
				if(type != null && type.length() > 0)
				{
					sqlBuilder.append(" AND type like '%");
					sqlBuilder.append(type);
					sqlBuilder.append("%' ");
				}
				SQLQuery query = session.createSQLQuery(sqlBuilder.toString());
				query.addEntity(News.class);
				query.setMaxResults(limit);
				query.setFirstResult(offset);
				return query.list();
			}			
		};
		return (List<News>)getHibernateTemplate().execute(callback);
	}

}
