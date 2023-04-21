package com.cesarwillymc.libraryprojectmpp.data.source.customer.entity;

import com.cesarwillymc.libraryprojectmpp.domain.annotation.EntityData;
import com.cesarwillymc.libraryprojectmpp.domain.entities.Address;
import com.cesarwillymc.libraryprojectmpp.domain.entities.Person;

import java.io.Serializable;
@EntityData
final public class AuthorResponse extends Person implements Serializable {
	private final String bio;
	public String getBio() {
		return bio;
	}

	public AuthorResponse(String f, String l, String t, Address a, String bio) {
		super(f, l, t, a);
		this.bio = bio;
	}

	private static final long serialVersionUID = 7508481940058530471L;
}
