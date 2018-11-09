package dao;

import org.hibernate.query.Query;

import util.HibernateUtil;

public class CatalogueDAO {

	public long countCopyBook() {
		long result = 0;
		
		HibernateUtil.openSessionWithTransaction();
		
			Query query = HibernateUtil.getCurrentSession().createQuery("Select count(catalogue) from Catalogue catalogue");
			result = (Long)query.uniqueResult();
		
		HibernateUtil.closeSessionWithTransaction();
		
		return result;
	}
}
