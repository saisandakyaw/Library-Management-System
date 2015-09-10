package edu.mum.lms.entities;

import java.util.List;

public class Book {

	public Book() {
	}

	private String isbn;
	private String title;
	private List<Author> authors;
	private int NoOfCopies;
	private int maximumCheckOutDays;

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public int getNoOfCopies() {
		return NoOfCopies;
	}

	public void setNoOfCopies(int noOfCopies) {
		NoOfCopies = noOfCopies;
	}

	public int getMaximumCheckOutDays() {
		return maximumCheckOutDays;
	}

	public void setMaximumCheckOutDays(int maximumCheckOutDays) {
		this.maximumCheckOutDays = maximumCheckOutDays;
	}

}