package com.cesarwillymc.libraryprojectmpp.data.local;

import com.cesarwillymc.libraryprojectmpp.domain.annotation.EntityData;
import com.cesarwillymc.libraryprojectmpp.data.local.util.FileUtil;
import com.cesarwillymc.libraryprojectmpp.data.source.account.entity.LibraryMemberResponse;
import com.cesarwillymc.libraryprojectmpp.data.source.customer.entity.BookResponse;
import com.cesarwillymc.libraryprojectmpp.data.source.login.entity.EmployeeResponse;
import com.cesarwillymc.libraryprojectmpp.domain.exception.LibrarySystemException;
import com.cesarwillymc.libraryprojectmpp.domain.exception.LoginException;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


public class DataAccessFacade implements DataAccessDao {
    public static final String DATE_PATTERN = "MM/dd/yyyy";

    @Override
    public List<BookResponse> getAllBooks() {
        return readBookMap().values().stream().toList();
    }

    @Override
    public BookResponse getBookById(String id) throws LibrarySystemException {
        var book = Optional.ofNullable(readBookMap().get(id));
        if (book.isPresent()) {
            return book.get();
        } else {
            throw new LibrarySystemException("Book doesn't exist");
        }
    }

    @Override
    public void addBook(BookResponse bookResponse) throws LibrarySystemException {
        var booksMap = readBookMap();
        if (booksMap.containsKey(bookResponse.isbn())) {
            throw new LibrarySystemException("Book already exist");
        }
        booksMap.put(bookResponse.isbn(), bookResponse);
        FileUtil.saveToStorage(StorageType.BOOKS, booksMap);
    }

    @Override
    public void updateBook(BookResponse bookResponse) throws LibrarySystemException {
        var booksMap = readBookMap();
        if (booksMap.containsKey(bookResponse.isbn())) {
            booksMap.put(bookResponse.isbn(), bookResponse);
            FileUtil.saveToStorage(StorageType.BOOKS, booksMap);
        } else {
            throw new LibrarySystemException("Book doesn't exist");
        }
    }

    @Override
    public void removeBook(String id) throws LibrarySystemException {
        var book = Optional.ofNullable(readBookMap().remove(id));
        if (book.isEmpty()) {
            throw new LibrarySystemException("Delete no work, Book Id doesn't exist");
        }
    }

    @Override
    public List<LibraryMemberResponse> getAllMembers() {
        return readMemberMap().values().stream().toList();
    }

    @Override
    public LibraryMemberResponse getMemberById(String id) throws LibrarySystemException {
        var member = Optional.ofNullable(readMemberMap().get(id));
        if (member.isPresent()) {
            return member.get();
        } else {
            throw new LibrarySystemException("Member doesn't exist");
        }
    }

    @Override
    public void addMember(LibraryMemberResponse memberResponse) throws LibrarySystemException {
        var memberMap = readMemberMap();
        if (memberMap.containsKey(memberResponse.getMemberId())) {
            throw new LibrarySystemException("Member already exist");
        }
        memberMap.put(memberResponse.getMemberId(), memberResponse);
        FileUtil.saveToStorage(StorageType.BOOKS, memberMap);
    }

    @Override
    public void removeMember(String id) throws LibrarySystemException {
        var member = Optional.ofNullable(readMemberMap().remove(id));
        if (member.isEmpty()) {
            throw new LibrarySystemException("Delete no work, Member Id doesn't exist");
        }
    }

    @Override
    public EmployeeResponse signIn(String id, String password) throws LoginException {
        var user = Optional.ofNullable(readUserMap().get(id));
        if (user.isEmpty())
            throw new LoginException("Id doesn't exist");
        if (!user.get().password().equals(password))
            throw new LoginException("Password incorrect");

        return user.get();
    }

    private HashMap<String, @EntityData BookResponse> readBookMap() {
        return FileUtil.readFromStorage(StorageType.BOOKS);
    }

    private HashMap<String, @EntityData LibraryMemberResponse> readMemberMap() {
        return FileUtil.readFromStorage(StorageType.MEMBERS);
    }

    private HashMap<String, @EntityData EmployeeResponse> readUserMap() {
        return FileUtil.readFromStorage(StorageType.USERS);
    }
}
