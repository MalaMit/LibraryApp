package dao;

import java.time.LocalDate;
import java.util.Date;
import org.hibernate.query.Query;
import util.HibernateUtil;

public class LendBookDAO {

	public boolean bookIsAvaible(long bookID){

		HibernateUtil.openSessionWithTransaction();

		Query query = HibernateUtil.getCurrentSession()
				.createQuery("Select lendBook from LendBook lendBook where lendBook.catalogue.book_ID = :book_ID")
				.setParameter("book_ID", bookID);

		if (query.uniqueResult() == null) {
			HibernateUtil.closeSessionWithTransaction();
			return true;
		}
		HibernateUtil.closeSessionWithTransaction();

		return false;
	}

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

			Query queryLendBook = HibernateUtil.getCurrentSession()
					.createNativeQuery("INSERT INTO lendbook (lend_Date, return_Date, book_ID, reader_ID) VALUES (:lendDate, :retDate, :bookID, :readerID)")
					.setParameter("lendDate", java.sql.Date.valueOf(LocalDate.now()))
					.setParameter("retDate", returnDate)
					.setParameter("bookID", bookID)
					.setParameter("readerID", readerID);

			queryLendBook.executeUpdate();

			Query queryChangeStatus = HibernateUtil.getCurrentSession().createQuery("UPDATE Catalogue set localization='Borrowed' where book_ID = :book_ID")
					.setParameter("book_ID", bookID);
			queryChangeStatus.executeUpdate();

		HibernateUtil.closeSessionWithTransaction();
	}

	public Long countLendBook() {
		long result = 0;

		HibernateUtil.openSessionWithTransaction();

			Query query = HibernateUtil.getCurrentSession().createQuery("Select count(lendBook) from LendBook lendBook");
			result = (Long)query.uniqueResult();
			
		HibernateUtil.closeSessionWithTransaction();
	
		return result;
	}
}

