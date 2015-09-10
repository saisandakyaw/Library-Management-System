package edu.mum.lms.dao;

import java.util.LinkedHashMap;

import edu.mum.lms.entities.Author;
import edu.mum.lms.commonUtil.JDBCUtil;

public class AuthorDao {
	private JDBCUtil db = new JDBCUtil();
	private static final String TABLE_NAME = "Author";

	public int addAuthor(int personId, Author a) {

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("credentials", a.getCredentials());
		map.put("shortBio", a.getShortBio());
		map.put("person_id", personId);

		int authorId = db.insertRow(TABLE_NAME, map, true);
		return authorId;
	}

	
}
