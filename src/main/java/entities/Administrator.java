package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "administrator")
public class Administrator {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admin_ID", columnDefinition = "bigint(20)")
	private long admin_ID;
	
	@Column(name = "name", columnDefinition = "VARCHAR(30)" ,nullable=false)
	private String name;
	
	@Column(name = "surname", columnDefinition = "VARCHAR(30)" ,nullable=false)
	private String surname;
	
	@Column(name = "login", columnDefinition = "VARCHAR(25)" ,nullable=false)
	private String login;
	
	@Column(name = "password", columnDefinition = "VARCHAR(30)" ,nullable=false)
	private String password;
	
	public Administrator() {
		
	}

	/**
	 * @return the adminID
	 */
	public long getAdminID() {
		return admin_ID;
	}

	/**
	 * @param adminID the adminID to set
	 */
	public void setAdminID(long admin_ID) {
		this.admin_ID = admin_ID;
	}

	/**
	 * @return the firstName
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the secondName
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param secondName the secondName to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	
}
