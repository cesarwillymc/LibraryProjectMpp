package com.cesarwillymc.libraryprojectmpp.domain.entities;

import com.cesarwillymc.libraryprojectmpp.domain.annotation.EntityDomain;

import java.io.Serial;
import java.io.Serializable;

/**
 * Immutable class
 */
@EntityDomain
final public class BookCopy implements Serializable {
	
	@Serial
	private static final long serialVersionUID = -63976228084869815L;
	private final String isbn;
	private String name;
	private final int copyNum;
	private boolean isAvailable;
	public BookCopy(String isbn,String name, int copyNum, boolean isAvailable) {
		this.isbn = isbn;
		this.name = name;
		this.copyNum = copyNum;
		this.isAvailable = isAvailable;
	}
	
	public BookCopy(String isbn, int copyNum) {
		this.isbn = isbn;
		this.copyNum = copyNum;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getName() {
		return name;
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
		if(!(ob instanceof BookCopy copy)) return false;
		return copy.isbn.equals(getBookISBN()) && copy.copyNum == copyNum;
	}
	
}
