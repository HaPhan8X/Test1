package com.tvo.puzzle.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.tvo.puzzle.dao.PresentManagementDao;
import com.tvo.puzzle.entity.PresentManagement;

@Repository
public class PresentManagementDaoImpl extends
		GenericDaoImpl<PresentManagement, Integer> implements
		PresentManagementDao {

	public PresentManagementDaoImpl() {
		super(PresentManagement.class);
	}

	@Override
	public Integer countPMBykeywordAndCategory(final String keyword,
			final Integer selectedProductId, final Integer selectedProjectId) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuilder sqlBuilder = new StringBuilder();
				sqlBuilder.append("select COUNT(*) FROM tbl_present_management pm left join tbl_product p on pm.product_id = p.id where pm.gender != -1 ");
				if (null != keyword && keyword.length() > 0) {
					sqlBuilder.append(" AND p.product_name like '%");
					sqlBuilder.append(keyword);
					sqlBuilder.append("%' ");
				}
				if (null != selectedProductId && selectedProductId > 0) {
					sqlBuilder.append(" AND p.id=");
					sqlBuilder.append(selectedProductId);
				}
				sqlBuilder.append(" AND pm.project_id=");
				sqlBuilder.append(selectedProjectId);
				Query query = session.createSQLQuery(sqlBuilder.toString());
				return Integer.parseInt(query.uniqueResult().toString());
			}
		};
		return getHibernateTemplate().execute(callback);
	}

	@Override
	public List<PresentManagement> findRangePMByKeywordAndProduct(
			final String keyword, final Integer selectedProductId,
			final Integer selectedProjectId, final Integer limit,
			final Integer offset) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuilder sqlBuilder = new StringBuilder();
				sqlBuilder
						.append("select pm.* FROM tbl_present_management pm left join tbl_product p on pm.product_id = p.id where pm.gender != -1 ");
				if (null != keyword && keyword.length() > 0) {
					sqlBuilder.append(" AND p.product_name like '%");
					sqlBuilder.append(keyword);
					sqlBuilder.append("%' ");
				}
				if (null != selectedProductId && selectedProductId > 0) {
					sqlBuilder.append(" AND p.id=");
					sqlBuilder.append(selectedProductId);
				}
				sqlBuilder.append(" AND pm.project_id=");
				sqlBuilder.append(selectedProjectId);
				SQLQuery query = session.createSQLQuery(sqlBuilder.toString());
				query.addEntity(PresentManagement.class);
				query.setMaxResults(limit);
				query.setFirstResult(offset);
				return query.list();
			}
		};
		return (List<PresentManagement>) getHibernateTemplate().execute(callback);
	}

}
