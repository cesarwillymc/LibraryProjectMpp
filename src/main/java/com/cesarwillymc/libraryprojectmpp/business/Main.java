package com.cesarwillymc.libraryprojectmpp.business;

import java.util.*;

import data.local.DataAccessDao;
import data.local.DataAccessFacade;
import domain.entities.Book;
import domain.entities.LibraryMember;

public class Main {

	public static void main(String[] args) {
		System.out.println(allWhoseZipContains3());
		System.out.println(allHavingOverdueBook());
		System.out.println(allHavingMultipleAuthors());

	}
	//Returns a list of all ids of LibraryMembers whose zipcode contains the digit 3
	public static List<String> allWhoseZipContains3() {
		DataAccessDao da = new DataAccessFacade();
		Collection<LibraryMember> members = da.readMemberMap().values();
		List<LibraryMember> mems = new ArrayList<>();
		mems.addAll(members);
		//implement
		return members.stream().filter(x->x.getAddress().getZip().contains("3")).map(LibraryMember::getMemberId).toList();
		
	}
	//Returns a list of all ids of  LibraryMembers that have an overdue book
	public static List<String> allHavingOverdueBook() {
		DataAccessDao da = new DataAccessFacade();
		Collection<Book> books =da.readBooksMap().values();
		Collection<LibraryMember> members = da.readMemberMap().values();
		List<LibraryMember> mems = new ArrayList<>();
		mems.addAll(members);
		List<Book> bs = new ArrayList<>();
		bs.addAll(books);
		//implement
		return da.readUserMap().values().stream().map(x-> x.toString()).toList();
		
	}
	
	//Returns a list of all isbns of  Books that have multiple authors
	public static List<String> allHavingMultipleAuthors() {
		DataAccessDao da = new DataAccessFacade();
		Collection<Book> books = da.readBooksMap().values();
		List<Book> bs = new ArrayList<>();
		bs.addAll(books);
		//implement
		return bs.stream().filter(x->x.getAuthors().size()>1).map(Book::getIsbn).toList();
		
		}

}
