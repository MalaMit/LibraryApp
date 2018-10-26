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
@Table(name = "LendBook")
public class LendBook {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lend_Book_ID", columnDefinition = "bigint(20)", nullable = false)
	private long lend_book_ID;

	@ManyToOne
	@JoinColumn(name = "book_ID", foreignKey = @ForeignKey(name = "book_ID_FK"), nullable = false)
	private Catalogue catalogue;

	@ManyToOne
	@JoinColumn(name = "reader_ID", foreignKey = @ForeignKey(name = "reader_ID_FK"), nullable = false)
	private Reader reader;

	@Temporal(TemporalType.DATE)
	private Date lend_Date;
	
	@Temporal(TemporalType.DATE)
	private Date return_Date;

	public LendBook() {
	}

	public LendBook(long lend_book_ID, Catalogue catalogue, Reader reader, Date lend_Date,
			Date return_Date) {
		this.lend_book_ID = lend_book_ID;
		this.catalogue = catalogue;
		this.reader = reader;
		this.lend_Date = lend_Date;
		this.return_Date = return_Date;
	}

	/**
	 * @return the borrowed_book_ID
	 */
	public long getLend_book_ID() {
		return lend_book_ID;
	}

	/**
	 * @param borrowed_book_ID the borrowed_book_ID to set
	 */
	public void setLend_book_ID(long lend_book_ID) {
		this.lend_book_ID = lend_book_ID;
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
	public Date getLend_Date() {
		return lend_Date;
	}

	/**
	 * @param borrowed_Date the borrowed_Date to set
	 */
	public void setLend_Date(Date lend_Date) {
		this.lend_Date = lend_Date;
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
