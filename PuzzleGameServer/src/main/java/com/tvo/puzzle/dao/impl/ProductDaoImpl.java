package com.tvo.puzzle.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tvo.puzzle.dao.ProductDao;
import com.tvo.puzzle.entity.Product;

@Repository
public class ProductDaoImpl extends GenericDaoImpl<Product, Integer> implements ProductDao {

	public ProductDaoImpl(){
		super(Product.class);
	}

	@Transactional
	public Product getProduct(final Integer id) {
		Product product = findById(id);
		return product;
		
	}
	@Transactional
	public List<Product> getListProduct(final int limit, final int offset) {
		HibernateCallback callback = new HibernateCallback()
		{
			public Object doInHibernate(Session session)
					throws HibernateException
			{
				StringBuilder sqlBuilder = new StringBuilder();
				sqlBuilder.append("FROM Product");
				Query query = session.createQuery(sqlBuilder.toString());
				query.setMaxResults(limit);
				query.setFirstResult(offset);
				return query.list();
			}
		};
		
		return (List<Product>) getHibernateTemplate().execute(callback);
	}

	public int countAll() {
		HibernateCallback callback = new HibernateCallback()
		{
			public Object doInHibernate(Session session)
					throws HibernateException
			{
				StringBuilder sqlBuilder = new StringBuilder();
				sqlBuilder.append("SELECT COUNT(*) FROM Product");
				Query query = session.createQuery(sqlBuilder.toString());
				return query.list().get(0);
			}
		};
		return ((Long) getHibernateTemplate().execute(callback))
				.intValue();
	}
}
