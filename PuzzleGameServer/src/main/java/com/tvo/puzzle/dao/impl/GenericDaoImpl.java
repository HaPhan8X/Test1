package com.tvo.puzzle.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.tvo.puzzle.dao.GenericDao;
import com.tvo.puzzle.exception.DuplicateNameException;

public abstract class GenericDaoImpl<E, PK extends Serializable> implements GenericDao<E, PK>
{
	private Class<E> type;

	HibernateTemplate hibernateTemplate;

	public GenericDaoImpl(Class<E> type)
	{
		this.type = type;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	public HibernateTemplate getHibernateTemplate()
	{
		return this.hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	public PK save(E newInstance)
	{
		try
		{
			return (PK) getHibernateTemplate().save(newInstance);
		}
		catch (DataIntegrityViolationException e)
		{
			throw new DuplicateNameException(e.getCause());
		}
	}

	public E findById(PK id)
	{
		return (E) getHibernateTemplate().get(this.type, id);
	}

	@SuppressWarnings("unchecked")
	public List<E> findAll()
	{
		return getHibernateTemplate().findByCriteria(createDetachedCriteria());
	}

	@SuppressWarnings("unchecked")
	public List<E> findAllByProperty(String propertyName, Object value)
	{
		DetachedCriteria criteria = createDetachedCriteria();
		criteria.add(Restrictions.eq(propertyName, value));
		return getHibernateTemplate().findByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	public List<E> findByExample(E object)
	{
		List<E> resultList = getHibernateTemplate().findByExample(object, 0, 1);
		return resultList;
	}

	@SuppressWarnings("unchecked")
	public List<E> findByExample(E object, int firstResult, int maxResults)
	{
		List<E> resultList = getHibernateTemplate().findByExample(object,
				firstResult, maxResults);
		return resultList;

	}

	public void update(E transientObject)
	{
		getHibernateTemplate().update(transientObject);
	}

	public void saveOrUpdate(E transientObject)
	{
		getHibernateTemplate().saveOrUpdate(transientObject);
	}

	public void delete(E persistentObject)
	{
		getHibernateTemplate().delete(persistentObject);
	}

	protected DetachedCriteria createDetachedCriteria()
	{
		return DetachedCriteria.forClass(this.type);
	}
}