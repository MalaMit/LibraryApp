package dao;

import entities.Catalogue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

	public ObservableList<Catalogue> findBooksByISBN (Long isbn){

		HibernateUtil.openSessionWithTransaction();

			Query<Catalogue> query = HibernateUtil.getCurrentSession().createQuery("select catalogue from Catalogue catalogue where catalogue.book.isbn = :isbn")
					.setParameter("isbn", isbn);
			ObservableList<Catalogue> localizationBoook = FXCollections.observableArrayList(query.getResultList());

		HibernateUtil.closeSessionWithTransaction();

		return localizationBoook;
	}

	public void deleteBookLocalization(Catalogue catalogue){

		HibernateUtil.openSessionWithTransaction();

			HibernateUtil.getCurrentSession().delete(catalogue);

		HibernateUtil.closeSessionWithTransaction();
	}

	public void addNewBookLocalization (long isbn, String newLocalization){

		HibernateUtil.openSessionWithTransaction();

			Query queryChangeStatus = HibernateUtil.getCurrentSession().createNativeQuery("insert into catalogue (localization, isbn) values (:newLocalization, :isbn)")
					.setParameter("newLocalization", newLocalization)
					.setParameter("isbn", isbn);
			queryChangeStatus.executeUpdate();

		HibernateUtil.closeSessionWithTransaction();
	}

	public void changeBookLokalization(long bookID, String newLocalization){

		HibernateUtil.openSessionWithTransaction();

		Query queryChangeStatus = HibernateUtil.getCurrentSession().createQuery("UPDATE Catalogue set localization=:newLocalization where book_ID = :bookID")
				.setParameter("newLocalization", newLocalization)
				.setParameter("bookID", bookID);
		queryChangeStatus.executeUpdate();

		HibernateUtil.closeSessionWithTransaction();
	}
}
