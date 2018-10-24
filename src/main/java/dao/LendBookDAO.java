package dao;

import java.time.LocalDate;
import java.util.Date;
import org.hibernate.query.Query;
import util.HibernateUtil;

public class LendBookDAO {
	
	public boolean checkExistBook(long bookID) {
		HibernateUtil.openSessionWithTransaction();

		Query query = HibernateUtil.getCurrentSession()
				.createQuery("select catalogue from Catalogue catalogue where catalogue.book_ID = :book_ID")
				.setParameter("book_ID", bookID);

		if (query.uniqueResult() == null) {
			HibernateUtil.closeSessionWithTransaction();
			return false;
		}
		HibernateUtil.closeSessionWithTransaction();
		
		return true;		
	}
	
	public void lendBook(long bookID, long readerID, Date returnDate) {
		HibernateUtil.openSessionWithTransaction();
		
		Query query = HibernateUtil.getCurrentSession()
				.createNativeQuery("INSERT INTO lendbook (lend_Date, return_Date, book_ID, reader_ID) VALUES (:lendDate, :retDate, :bookID, :readerID)")
				.setParameter("lendDate", java.sql.Date.valueOf(LocalDate.now()))
				.setParameter("retDate", returnDate)
				.setParameter("bookID", bookID)
				.setParameter("readerID", readerID);
		
		query.executeUpdate();

		HibernateUtil.closeSessionWithTransaction();
	}
}

