package com.cesarwillymc.libraryprojectmpp.domain.entities;

import com.cesarwillymc.libraryprojectmpp.domain.annotation.EntityDomain;

import java.io.Serial;
import java.io.Serializable;

/* Immutable */
@EntityDomain
final public class Address implements Serializable {
	
	@Serial
	private static final long serialVersionUID = -891229800414574888L;
	private final String street;
	private final String city;
	private final String state;
	private final String zip;
	public Address(String street, String city, String state, String zip) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	public String getStreet() {
		return street;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getZip() {
		return zip;
	}
	@Override
	public String toString() {
		return String.format("%s, %s, %s %s", street,city,state, zip);
	}
}
