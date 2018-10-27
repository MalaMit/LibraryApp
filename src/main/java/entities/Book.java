package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name =  "book")
public class Book {
	
	@Id
	@Column(name = "isbn", columnDefinition = "bigint(20)")
	private long isbn;
	
	@Column(name = "title", columnDefinition = "VARCHAR(60)", nullable=false)
	private String title;
	
	@Column(name  = "autor", columnDefinition = "VARCHAR(40)", nullable=false)
	private String autor;
	
	@Column(name = "publication_year", columnDefinition = "int", nullable=false)
	private int publication_year;
	
	@Column(name = "type", columnDefinition = "VARCHAR(30)", nullable=false)
	private String type;
	
	public Book(){
		
	}

	/**
	 * @return the isbn
	 */
	public long getIsbn() {
		return isbn;
	}

	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the autor
	 */
	public String getAutor() {
		return autor;
	}

	/**
	 * @param autor the autor to set
	 */
	public void setAutor(String autor) {
		this.autor = autor;
	}

	/**
	 * @return the publication_year
	 */
	public int getPublication_year() {
		return publication_year;
	}

	/**
	 * @param publication_year the publication_year to set
	 */
	public void setPublication_year(int publication_year) {
		this.publication_year = publication_year;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	
	
}
