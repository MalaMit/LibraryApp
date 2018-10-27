package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "catalogue")
public class Catalogue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_ID", columnDefinition = "bigint(20)", nullable = false)
	private long book_ID;
	
	@Column(name = "localization", columnDefinition = "VARCHAR(10)", nullable = false)
	private String localization;
	
	@ManyToOne
	@JoinColumn(name = "isbn", foreignKey = @ForeignKey(name = "Isbn_Book_FK"), nullable = false)
	private Book book;

	public Catalogue() {
	}

	/**
	 * @return the book_ID
	 */
	public long getBook_ID() {
		return book_ID;
	}

	/**
	 * @param book_ID the book_ID to set
	 */
	public void setBook_ID(long book_ID) {
		this.book_ID = book_ID;
	}

	/**
	 * @return the localization
	 */
	public String getLocalization() {
		return localization;
	}

	/**
	 * @param localization the localization to set
	 */
	public void setLocalization(String localization) {
		this.localization = localization;
	}

	/**
	 * @return the book
	 */
	public Book getBook() {
		return book;
	}

	/**
	 * @param book the book to set
	 */
	public void setBook(Book book) {
		this.book = book;
	}

	
}
