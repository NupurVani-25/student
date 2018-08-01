package com.cg.jpacrud.client;

import com.cg.jpacrud.entities.Book;
import com.cg.jpacrud.service.BookService;
import com.cg.jpacrud.service.BookServiceImpl;

public class Client {

	public static void main(String[] args) {

		BookService service = new BookServiceImpl();

		System.out.println("List the author name for given book id.");
		System.out.println("Boo with ID 100:" + service.getBookById(100));

		System.out.println("Query all books in database. ");
		for (Book book : service.getAllBooks()) {
			System.out.println(book);
		}

		System.out.println("Query all books written by given author name");
		for (Book book : service.getAuthorBooks("Vikash")) {
			System.out.println(book);
		}

		System.out.println("List all books with given price range. e.g. between Rs. 500 to 1000");
		for (Book book : service.getBooksInPriceRange(500, 600)) {
			System.out.println(book);
		}

	}
}
