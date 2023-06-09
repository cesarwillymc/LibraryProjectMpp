package com.cesarwillymc.libraryprojectmpp.data.local;

import com.cesarwillymc.libraryprojectmpp.data.local.util.FileUtil;
import com.cesarwillymc.libraryprojectmpp.data.source.account.entity.LibraryMemberResponse;
import com.cesarwillymc.libraryprojectmpp.data.source.customer.entity.BookResponse;
import com.cesarwillymc.libraryprojectmpp.data.source.login.entity.UserResponse;
import com.cesarwillymc.libraryprojectmpp.data.source.memberRecord.entity.MemberRecordResponse;
import com.cesarwillymc.libraryprojectmpp.domain.annotation.EntityData;
import com.cesarwillymc.libraryprojectmpp.domain.entities.User;
import com.cesarwillymc.libraryprojectmpp.domain.exception.LibrarySystemException;
import com.cesarwillymc.libraryprojectmpp.domain.exception.LoginException;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


public class DataAccessFacade implements DataAccessDao {
    public static final String DATE_PATTERN = "MM/dd/yyyy";

    @Override
    public List<BookResponse> getAllBooks() throws LibrarySystemException {
        try {
            return readBookMap().values().stream().toList();
        }catch (Exception e){
            throw new LibrarySystemException("File no found");
        }
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
        var booksMap = readBookMap();
        var book = Optional.ofNullable(booksMap.remove(id));
        if (book.isEmpty()) {
            throw new LibrarySystemException("Delete no work, Book Id doesn't exist");
        }
        FileUtil.saveToStorage(StorageType.BOOKS, booksMap);
    }

    @Override
    public List<LibraryMemberResponse> getAllMembers() throws LibrarySystemException {
        try {
            return readMemberMap().values().stream().toList();
        }catch (Exception e){
            throw new LibrarySystemException("File no found");
        }

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
        FileUtil.saveToStorage(StorageType.MEMBERS, memberMap);
    }

    @Override
    public void removeMember(String id) throws LibrarySystemException {
        var memberMap = readMemberMap();
        var member = Optional.ofNullable(memberMap.remove(id));
        if (member.isEmpty()) {
            throw new LibrarySystemException("Delete no work, Member Id doesn't exist");
        }
        FileUtil.saveToStorage(StorageType.MEMBERS, memberMap);
    }

    @Override
    public void saveLoggedUser(UserResponse user) {
        var userMap = new HashMap<String, UserResponse>();
        userMap.put("userLogged", user);
        FileUtil.saveToStorage(StorageType.LOGGED, userMap);
    }

    @Override
    public void signOut() {
        var userMap = new HashMap<String, User>();
        FileUtil.saveToStorage(StorageType.LOGGED, userMap);
    }

    @Override
    public UserResponse getUserLogged() {
        Optional<HashMap<String, UserResponse>> userMap = Optional.ofNullable(FileUtil.readFromStorage(StorageType.LOGGED));
        if (userMap.isEmpty())
            return null;
        if (userMap.get().size() == 0) {
            return null;
        }
        return userMap.get().get("userLogged");
    }

    @Override
    public UserResponse signIn(String id, String password) throws LoginException {
        var user = Optional.ofNullable(readUserMap().get(id));
        System.out.println("user is " + user.isEmpty() + " and  " + id + " passport " + password);
        if (user.isEmpty())
            throw new LoginException("Id doesn't exist");
        System.out.println("user data " + user.get());
        if (!user.get().getPassword().equals(password))
            throw new LoginException("Password incorrect");

        return user.get();
    }

    @Override
    public void addMemberRecord(MemberRecordResponse recordResponse) throws LibrarySystemException {
        var data = readMemberRecord();
        data.put(recordResponse.id(), recordResponse);
        FileUtil.saveToStorage(StorageType.RECORD_MEMBER, data);
    }

    @Override
    public void updateMemberRecord(MemberRecordResponse recordResponse) throws LibrarySystemException {
        var data = readMemberRecord();
        data.put(recordResponse.id(), recordResponse);
        FileUtil.saveToStorage(StorageType.RECORD_MEMBER, data);
    }

    @Override
    public List<MemberRecordResponse> getAllMembersRecord() throws LibrarySystemException {
        try {
           return readMemberRecord().values().stream().toList();
        }catch (Exception e){
            throw new LibrarySystemException("File no found");
        }
    }

    private HashMap<String, @EntityData BookResponse> readBookMap() {
        return FileUtil.readFromStorage(StorageType.BOOKS);
    }

    private HashMap<String, @EntityData LibraryMemberResponse> readMemberMap() {
        return FileUtil.readFromStorage(StorageType.MEMBERS);
    }

    private HashMap<String, @EntityData UserResponse> readUserMap() {
        return FileUtil.readFromStorage(StorageType.USERS);
    }

    private HashMap<String, @EntityData MemberRecordResponse> readMemberRecord() {
        return FileUtil.readFromStorage(StorageType.RECORD_MEMBER);
    }
}
