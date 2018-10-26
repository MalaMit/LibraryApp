package dao;

import org.hibernate.query.Query;

import entities.LendBook;
import util.HibernateUtil;

public class ReturnAndExtensionDAO {
	
	public void returnBook (long bookID) {
		
	}

	public LendBook extensionBook(long bookID) {

		LendBook lendBook = null;

		HibernateUtil.openSessionWithTransaction();

			Query query = HibernateUtil.getCurrentSession()
					.createQuery("select lB from LendBook lB join fetch lB.catalogue c where c.book_ID = :book_ID")
					.setParameter("book_ID", bookID);
			
			if (query.uniqueResult() != null) {
				lendBook = (LendBook) query.getSingleResult();
			}else {
				return null;
			}

		HibernateUtil.closeSessionWithTransaction();
		
		return lendBook;
	}
	
	public void extensionBookApply(LendBook lendBook) {
		
		HibernateUtil.openSessionWithTransaction();
		
		HibernateUtil.getCurrentSession().update(lendBook);
		
		HibernateUtil.closeSessionWithTransaction();

	}
}