package edu.mum.lms.controller;

import java.util.ArrayList;
import java.util.List;

import edu.mum.lms.commonUtil.JDBCUtil;
import edu.mum.lms.dao.AuthorDao;
import edu.mum.lms.dao.BookDao;
import edu.mum.lms.dao.PersonDao;
import edu.mum.lms.entities.Author;
import edu.mum.lms.entities.Book;

public class UpdateBookCollection {

	PersonDao pdao = new PersonDao();
	AuthorDao adao = new AuthorDao();
	BookDao bdao = new BookDao();
	JDBCUtil db = new JDBCUtil();

	public void addCopyOfBook(Book b) {

		if (bdao.addBookCopy(b) != 0)
			System.out.println("Inserted");

	}

	public void addBookToCollection(Book b) {
		bdao.addBook(b);

	}

	public static void main(String[] args) {

		// Book b = new Book();
		// b.setIsbn("210");
		// b.setTitle("head Second!!!");

		// BookDao bdao = new BookDao();
		// System.out.println(bdao.addBookCopy(b));

		BookDao bdao = new BookDao();
		// Adding the book
		Book b = new Book();
		b.setIsbn("700");
		b.setTitle("HELLO Second!!!");
		b.setNoOfCopies(2);
		b.setMaximumCheckOutDays(4);
		
		List<Author> authors = new ArrayList<>();

		Author author = new Author();
		author.setFirstName("Bidhut");
		author.setLastName("KARKI");
		author.setPhoneNo("655-333-768");
		author.setStreet("1000 N");
		author.setCity("Farifield");
		author.setState("IA");
		author.setZip(54657);
		author.setCredentials("has won Putlizer award");
		author.setShortBio("he is the wonderful teacher");
		authors.add(author);
		b.setAuthors(authors);

		bdao.addBook(b);
		System.out.println("Inserted!!");

	}

}
