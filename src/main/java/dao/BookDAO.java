package dao;

import org.hibernate.query.Query;

import entities.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.HibernateUtil;

public class BookDAO {
	public ObservableList<Book> allBooks() {

		HibernateUtil.openSessionWithTransaction();

		Query<Book> query = HibernateUtil.getCurrentSession().createQuery("select book from Book book");
		ObservableList<Book> books = FXCollections.observableArrayList(query.getResultList());

		HibernateUtil.closeSessionWithTransaction();

		return books;
	}
	
	public void deleteBook(Book book) {

		HibernateUtil.openSessionWithTransaction();

		HibernateUtil.getCurrentSession().delete(book);

		HibernateUtil.closeSessionWithTransaction();
	}
	
	public void createBook(Book book) {

		HibernateUtil.openSessionWithTransaction();

		HibernateUtil.getCurrentSession().save(book);

		HibernateUtil.closeSessionWithTransaction();
	}
	
	public void updateBook(Book book) {

		HibernateUtil.openSessionWithTransaction();

		HibernateUtil.getCurrentSession().update(book);

		HibernateUtil.closeSessionWithTransaction();
	}
	
	public ObservableList<Book> searchBook(String parameter, String variant) {
		HibernateUtil.openSessionWithTransaction();

		Query query = null;

		switch (variant) {
		case "0":
			query = HibernateUtil.getCurrentSession()
					.createQuery("select book from Book book where book.isbn = :isbn")
					.setParameter("isbn", Long.valueOf(parameter));
			break;

		case "1":
			query = HibernateUtil.getCurrentSession()
					.createQuery("select book from Book book where book.autor = :autor")
					.setParameter("autor", parameter);
			break;

		case "2":
			query = HibernateUtil.getCurrentSession()
					.createQuery("select book from Book book where book.title = :title")
					.setParameter("title", parameter);
			break;
			
		case "3":
			query = HibernateUtil.getCurrentSession()
					.createQuery("select book from Book book where book.type = :type")
					.setParameter("type", parameter);
			break;

		default:
			break;
		}

		ObservableList<Book> books = FXCollections.observableArrayList(query.getResultList());

		HibernateUtil.closeSessionWithTransaction();

		return books;
	}
}
