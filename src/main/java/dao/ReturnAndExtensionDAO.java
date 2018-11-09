package dao;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

import org.hibernate.query.Query;

import entities.LendBook;
import entities.ReaderHistory;
import util.HibernateUtil;

public class ReturnAndExtensionDAO {
	
	public void returnBook (ReaderHistory readerHist, LendBook lendBook) {
		HibernateUtil.openSessionWithTransaction();
		
			HibernateUtil.getCurrentSession().save(readerHist);
			HibernateUtil.getCurrentSession().delete(lendBook);
		
		HibernateUtil.closeSessionWithTransaction();
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
	
	public long countBookTimeOut() {
		long result = 0;
	
		HibernateUtil.openSessionWithTransaction();
		
			Query query = HibernateUtil.getCurrentSession().createQuery("Select count(lB.lend_book_ID) from LendBook lB"
					+ " where lB.return_Date < :today")
					.setParameter("today",java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())));
	
			if (query.uniqueResult() != null) {
				result = (Long)query.uniqueResult();
			}
			
			System.out.println(result);
			
		HibernateUtil.closeSessionWithTransaction();
		
		return result;
	}
}
