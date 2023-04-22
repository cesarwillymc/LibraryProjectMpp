package com.cesarwillymc.libraryprojectmpp.data.source.account.entity;

import com.cesarwillymc.libraryprojectmpp.domain.annotation.EntityData;

import java.io.Serializable;
@EntityData
public class PersonResponse implements Serializable {
	private static final long serialVersionUID = 11137994345555555L;
	private String firstName;
	private String lastName;
	private String telephone;
	private AddressResponse address;
	public PersonResponse(String f, String l, String t, AddressResponse a) {
		firstName = f;
		lastName = l;
		telephone = t;
		address = a;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getTelephone() {
		return telephone;
	}
	public AddressResponse getAddress() {
		return address;
	}
}
