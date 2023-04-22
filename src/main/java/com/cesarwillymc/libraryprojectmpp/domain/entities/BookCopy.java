package com.cesarwillymc.libraryprojectmpp.domain.entities;

import com.cesarwillymc.libraryprojectmpp.domain.annotation.EntityDomain;

import java.io.Serializable;

/**
 * Immutable class
 */
@EntityDomain
final public class BookCopy implements Serializable {
	
	private static final long serialVersionUID = -63976228084869815L;
	private String isbn;
	private int copyNum;
	private boolean isAvailable;
	public BookCopy(String isbn, int copyNum, boolean isAvailable) {
		this.isbn = isbn;
		this.copyNum = copyNum;
		this.isAvailable = isAvailable;
	}
	
	public BookCopy(String isbn, int copyNum) {
		this.isbn = isbn;
		this.copyNum = copyNum;
	}
	
	
	public boolean isAvailable() {
		return isAvailable;
	}

	public int getCopyNum() {
		return copyNum;
	}
	
	public String getBookISBN() {
		return isbn;
	}
	
	public void changeAvailability() {
		isAvailable = !isAvailable;
	}
	
	@Override
	public boolean equals(Object ob) {
		if(ob == null) return false;
		if(!(ob instanceof BookCopy)) return false;
		BookCopy copy = (BookCopy)ob;
		return copy.isbn.equals(getBookISBN()) && copy.copyNum == copyNum;
	}
	
}
