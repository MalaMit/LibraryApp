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
@Table(name = "Reader_History")
public class ReaderHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ReaderHist_ID", columnDefinition = "bigint(20)", nullable = false)
	private long ReaderHist_ID;
	
	@ManyToOne
	@JoinColumn(name = "reader", foreignKey = @ForeignKey(name = "Reader_History_FK"), nullable = false)
	private Reader reader;

	@ManyToOne
	@JoinColumn(name = "book", foreignKey = @ForeignKey(name = "Book_ID_FK"), nullable = false)
	private Book book;
	
	@Temporal(TemporalType.DATE)
	private Date lend_Date;
	
	@Temporal(TemporalType.DATE)
	private Date return_Date;

	public ReaderHistory() {
	
	}
	
	/**
	 * @return the readerHist_ID
	 */
	public long getReaderHist_ID() {
		return ReaderHist_ID;
	}

	/**
	 * @param readerHist_ID the readerHist_ID to set
	 */
	public void setReaderHist_ID(long readerHist_ID) {
		ReaderHist_ID = readerHist_ID;
	}

	/**
	 * @return the book_ID
	 */
	public Book getBook_ID() {
		return book;
	}

	/**
	 * @param book_ID the book_ID to set
	 */
	public void setBook_ID(Book book_ID) {
		this.book = book_ID;
	}

	/**
	 * @return the lend_Date
	 */
	public Date getLend_Date() {
		return lend_Date;
	}

	/**
	 * @param lend_Date the lend_Date to set
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
