package com.cesarwillymc.libraryprojectmpp.domain.entities;

import com.cesarwillymc.libraryprojectmpp.domain.annotation.EntityDomain;
import com.cesarwillymc.libraryprojectmpp.domain.entities.Address;
import com.cesarwillymc.libraryprojectmpp.domain.entities.Person;

import java.io.Serial;
import java.io.Serializable;

@EntityDomain
final public class LibraryMember extends Person implements Serializable {
	private final String memberId;
	
	public LibraryMember(String memberId, String fname, String lname, String tel, Address add) {
		super(fname,lname, tel, add);
		this.memberId = memberId;		
	}
	
	
	public String getMemberId() {
		return memberId;
	}

	
	
	@Override
	public String toString() {
		return "Member Info: " + "ID: " + memberId + ", name: " + getFirstName() + " " + getLastName() + 
				", " + getTelephone() + " " + getAddress();
	}

	@Serial
	private static final long serialVersionUID = -2226197306790714013L;
}
