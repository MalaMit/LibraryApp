package dao;

import java.util.List;

import org.hibernate.query.Query;

import entities.Administrator;
import util.HibernateUtil;

public class AdministratorDAO {

	public boolean checkLoginData(String login, String password) {

		boolean result = false;

		HibernateUtil.openSessionWithTransaction();
			
			Query query = HibernateUtil.getCurrentSession()
					.createQuery("select administrator from Administrator administrator where administrator.login = :login")
					.setParameter("login", login);
	
			List<Administrator> administrator = query.getResultList();
	
		HibernateUtil.closeSessionWithTransaction();

		if (!administrator.isEmpty()) {
			if (administrator.get(0).getPassword().contentEquals(password))
				result = true;
		}
		return result;
	}
}