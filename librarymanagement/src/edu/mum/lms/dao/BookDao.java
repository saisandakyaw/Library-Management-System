package edu.mum.lms.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import edu.mum.lms.commonUtil.DbClient;
import edu.mum.lms.commonUtil.DbClient.FilterCondition;
import edu.mum.lms.commonUtil.JDBCUtil;
import edu.mum.lms.entities.Author;
import edu.mum.lms.entities.Book;
import edu.mum.lms.entities.Person;

public class BookDao {
	private JDBCUtil db = new JDBCUtil();
	private static final String TABLE_NAME = "Book";

	//adds Book to the Book table along with Author details
	public void addBook(Book b) {
		PersonDao pdao = new PersonDao();
		AuthorDao adao = new AuthorDao();
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("isbn", b.getIsbn());
		map.put("title", b.getTitle());
		map.put("noofcopy",b.getNoOfCopies());
		map.put("maximumcheckoutdays",b.getMaximumCheckOutDays());
		db.insertRow(TABLE_NAME, map, false);
		for (Author a : b.getAuthors()) {
			Person p = (Person) a;
			int personId = pdao.addPerson(p);
			int authorId = adao.addAuthor(personId, a);
			addBookAuthor(Integer.parseInt(b.getIsbn()), authorId);
		}

	}

	//add copy of a Book in the BookCopy table.Increase the copy Number of the book .
	public int addBookCopy(Book b) {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("isbn", b.getIsbn());
		String[] columns = new String[] { "copyNumber" };

		FilterCondition condition = new DbClient.FilterCondition();
		condition.addCondition("isbn", DbClient.EQUALS, b.getIsbn());

		List<Map<String, Object>> copyNumbers = db.get("BookCopy", columns, condition);
		List<Integer> cnos = new ArrayList<>();
		if (copyNumbers.isEmpty()) {
			return 0;
		}
		for (Map<String, Object> hmap : copyNumbers) {

			for (Map.Entry<String, Object> e : hmap.entrySet()) {
				Object value = e.getValue();
				cnos.add((Integer) value);
			}

		}

		int nextcopyNumber = Collections.max(cnos);
		System.out.println("max copy number is " + nextcopyNumber);
		map.put("copyNumber", ++nextcopyNumber);
		return db.insertRow("BookCopy", map, true);
	}

	//add book id and author id to bookauthor table .Maintains mutiple relation between book and author
	public void addBookAuthor(int bookId, int authorId) {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("book_id", bookId);
		map.put("author_id", authorId);

		db.insertRow("book_author", map, false);
	}

}
