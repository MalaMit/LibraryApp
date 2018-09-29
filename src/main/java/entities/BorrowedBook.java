package entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Borrowed_Book")
public class BorrowedBook {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "borrowed_Book_ID", columnDefinition = "bigint(20)", nullable = false)
	private long borrowed_book_ID;

	@ManyToOne
	@JoinColumn(name = "book_ID", foreignKey = @ForeignKey(name = "book_ID_FK"), nullable = false)
	private Catalogue catalogue;

	@ManyToOne
	@JoinColumn(name = "reader_ID", foreignKey = @ForeignKey(name = "reader_ID_FK"), nullable = false)
	private Reader reader;

	@Temporal(TemporalType.DATE)
	private Date borrowed_Date;
	
	@Temporal(TemporalType.DATE)
	private Date return_Date;

	public BorrowedBook() {
	}

	public BorrowedBook(long borrowed_book_ID, Catalogue catalogue, Reader reader, Date borrowed_Date,
			Date return_Date) {
		this.borrowed_book_ID = borrowed_book_ID;
		this.catalogue = catalogue;
		this.reader = reader;
		this.borrowed_Date = borrowed_Date;
		this.return_Date = return_Date;
	}

	/**
	 * @return the borrowed_book_ID
	 */
	public long getBorrowed_book_ID() {
		return borrowed_book_ID;
	}

	/**
	 * @param borrowed_book_ID the borrowed_book_ID to set
	 */
	public void setBorrowed_book_ID(long borrowed_book_ID) {
		this.borrowed_book_ID = borrowed_book_ID;
	}

	/**
	 * @return the catalogue
	 */
	public Catalogue getCatalogue() {
		return catalogue;
	}

	/**
	 * @param catalogue the catalogue to set
	 */
	public void setCatalogue(Catalogue catalogue) {
		this.catalogue = catalogue;
	}

	/**
	 * @return the reader
	 */
	public Reader getReader() {
		return reader;
	}

	/**
	 * @param reader the reader to set
	 */
	public void setReader(Reader reader) {
		this.reader = reader;
	}

	/**
	 * @return the borrowed_Date
	 */
	public Date getBorrowed_Date() {
		return borrowed_Date;
	}

	/**
	 * @param borrowed_Date the borrowed_Date to set
	 */
	public void setBorrowed_Date(Date borrowed_Date) {
		this.borrowed_Date = borrowed_Date;
	}

	/**
	 * @return the return_Date
	 */
	public Date getReturn_Date() {
		return return_Date;
	}

	/**
	 * @param return_Date the return_Date to set
	 */
	public void setReturn_Date(Date return_Date) {
		this.return_Date = return_Date;
	}
	
	
}
