package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "reader")
public class Reader {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "reader_ID", columnDefinition = "bigint(20)")
	private Long reader_ID;
	
	@Column(name = "name", columnDefinition = "VARCHAR(30)" , nullable = false)
	private String name;
	
	@Column(name = "surname", columnDefinition = "VARCHAR(30)" ,nullable = false)
	private String surname;
	
	@Column(name = "pesel", columnDefinition = "bigint(20)" ,nullable = false)
	private Long pesel;
	
	@Column(name = "address", columnDefinition = "VARCHAR(60)" ,nullable = false)
	private String address;
	
	@Column(name = "phone_number", columnDefinition = "VARCHAR(15)" ,nullable = false)
	private String phone_number;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="reader")
	List<ReaderHistory> readerHistory = new ArrayList<>();

	public Reader() {
	
	}

	/**
	 * @return the reader_ID
	 */
	public Long getReader_ID() {
		return reader_ID;
	}

	/**
	 * @param reader_ID the reader_ID to set
	 */
	public void setReader_ID(long reader_ID) {
		this.reader_ID = reader_ID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the pesel
	 */
	public Long getPesel() {
		return pesel;
	}

	/**
	 * @param pesel the pesel to set
	 */
	public void setPesel(long pesel) {
		this.pesel = pesel;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the phone_number
	 */
	public String getPhone_number() {
		return phone_number;
	}

	/**
	 * @param phone_number the phone_number to set
	 */
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	/**
	 * @return the historyReader
	 */
	public List<ReaderHistory> getHistoryReader() {
		return readerHistory;
	}

	/**
	 * @param historyReader the historyReader to set
	 */
	public void setHistoryReader(List<ReaderHistory> readerHistory) {
		this.readerHistory = readerHistory;
	}
	
}
