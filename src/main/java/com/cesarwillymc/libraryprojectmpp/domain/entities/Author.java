package com.cesarwillymc.libraryprojectmpp.domain.entities;

import com.cesarwillymc.libraryprojectmpp.domain.annotation.EntityDomain;

import java.io.Serializable;
import java.util.List;

@EntityDomain
final public class Author extends Person implements Serializable {
	private String bio;
	private List<Book> listBooks;

	public String getBio() {
		return bio;
	}
	
	public Author(String f, String l, String t, Address a, String bio) {
		super(f, l, t, a);
		this.bio = bio;
	}

	private static final long serialVersionUID = 7508481940058530471L;
}
