package com.cesarwillymc.libraryprojectmpp.data.local;



import com.cesarwillymc.libraryprojectmpp.data.source.account.entity.LibraryMemberResponse;
import com.cesarwillymc.libraryprojectmpp.data.source.customer.entity.BookResponse;
import com.cesarwillymc.libraryprojectmpp.data.source.login.entity.EmployeeResponse;
import com.cesarwillymc.libraryprojectmpp.domain.exception.LibrarySystemException;
import com.cesarwillymc.libraryprojectmpp.domain.exception.LoginException;

import java.util.List;

public interface DataAccessDao {

    // Book
	List<BookResponse> getAllBooks();

	BookResponse getBookById(String id) throws LibrarySystemException;

	void addBook(BookResponse bookResponse) throws LibrarySystemException;

	void updateBook(BookResponse bookResponse) throws LibrarySystemException;

	void removeBook(String id) throws LibrarySystemException;

    // LibraryMember
	List<LibraryMemberResponse> getAllMembers();

	LibraryMemberResponse getMemberById(String id) throws LibrarySystemException;

	void addMember(LibraryMemberResponse memberResponse) throws LibrarySystemException;

	void removeMember(String id) throws LibrarySystemException;

    // User
	EmployeeResponse signIn(String id, String password) throws LoginException;
}
