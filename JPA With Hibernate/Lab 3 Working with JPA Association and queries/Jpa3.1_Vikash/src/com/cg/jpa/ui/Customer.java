package com.cg.jpa.ui;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.cg.jpa.bean.Author;
import com.cg.jpa.bean.Book;

public class Customer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA-PU");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		Author author = new Author();
		author.setName("Vikash");
		;
		// create two instances of Book
		Book b1 = new Book();
		b1.setPrice(550.0f);
		b1.setTitle("Java Fundamentals");

		Book b2 = new Book();
		b2.setPrice(560.0f);
		b2.setTitle("Android");

		author.addEmployee(b1);
		author.addEmployee(b2);

		em.persist(author);

		System.out.println("Added to Database");

		em.getTransaction().commit();
		em.close();
		factory.close();
	}

}
