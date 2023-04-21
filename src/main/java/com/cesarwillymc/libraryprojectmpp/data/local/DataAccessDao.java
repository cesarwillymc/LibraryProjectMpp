package com.cesarwillymc.libraryprojectmpp.data.local;


import com.cesarwillymc.libraryprojectmpp.data.source.account.entity.LibraryMemberResponse;
import com.cesarwillymc.libraryprojectmpp.data.source.customer.entity.BookResponse;
import com.cesarwillymc.libraryprojectmpp.data.source.login.entity.UserResponse;
import com.cesarwillymc.libraryprojectmpp.data.source.memberRecord.entity.MemberRecordResponse;
import com.cesarwillymc.libraryprojectmpp.domain.entities.MemberRecord;
import com.cesarwillymc.libraryprojectmpp.domain.entities.User;
import com.cesarwillymc.libraryprojectmpp.domain.exception.LibrarySystemException;
import com.cesarwillymc.libraryprojectmpp.domain.exception.LoginException;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;

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

    void saveLoggedUser(UserResponse user);

    void signOut();
    UserResponse getUserLogged();
    // User
    UserResponse signIn(String id, String password) throws LoginException;

    // MemberRecord

    void addMemberRecord(MemberRecordResponse recordResponse) throws LibrarySystemException;

    void updateMemberRecord(MemberRecordResponse recordResponse) throws LibrarySystemException;

    List<MemberRecordResponse> getAllMembersRecord() throws LibrarySystemException;

}
