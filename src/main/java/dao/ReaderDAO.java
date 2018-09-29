package dao;

import org.hibernate.query.Query;

import entities.Reader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.HibernateUtil;

public class ReaderDAO {

	public ObservableList<Reader> allReaders() {

		HibernateUtil.openSessionWithTransaction();

		Query<Reader> query = HibernateUtil.getCurrentSession().createQuery("select reader from Reader reader");
		ObservableList<Reader> readers = FXCollections.observableArrayList(query.getResultList());

		HibernateUtil.closeSessionWithTransaction();

		return readers;
	}

	public void deleteReader(Reader reader) {

		HibernateUtil.openSessionWithTransaction();

		HibernateUtil.getCurrentSession().delete(reader);

		HibernateUtil.closeSessionWithTransaction();
	}
	
	public void updateReader(Reader reader) {
		HibernateUtil.openSessionWithTransaction();

		HibernateUtil.getCurrentSession().update(reader);

		HibernateUtil.closeSessionWithTransaction();
	}

	public void createReader(Reader reader) {
		HibernateUtil.openSessionWithTransaction();

		HibernateUtil.getCurrentSession().save(reader);

		HibernateUtil.closeSessionWithTransaction();
	}
	public ObservableList<Reader> searchReader(String parameter, String variant) {
		HibernateUtil.openSessionWithTransaction();

		Query query = null;

		switch (variant) {
		case "0":
			query = HibernateUtil.getCurrentSession()
					.createQuery("select reader from Reader reader where reader.reader_ID = :reader_ID")
					.setParameter("reader_ID", Long.valueOf(parameter));
			break;

		case "1":
			String[] parts = parameter.split(" ");
			String name = parts[0];
			String surName = parts[1];
			query = HibernateUtil.getCurrentSession()
					.createQuery("select reader from Reader reader where reader.name = :name and reader.surname = :surname")
					.setParameter("name", name).setParameter("surname", surName);
			break;

		case "2":
			query = HibernateUtil.getCurrentSession()
					.createQuery("select reader from Reader reader where reader.pesel = :pesel")
					.setParameter("pesel", Long.valueOf(parameter));
			break;

		default:
			break;
		}

		ObservableList<Reader> readers = FXCollections.observableArrayList(query.getResultList());

		HibernateUtil.closeSessionWithTransaction();

		return readers;
	}
	
	public Reader findReader(long id) {
		
		HibernateUtil.openSessionWithTransaction();

		Reader readers = HibernateUtil.getCurrentSession().get(Reader.class, id);
		
		HibernateUtil.closeSessionWithTransaction();
		return readers;
		
	}

}
