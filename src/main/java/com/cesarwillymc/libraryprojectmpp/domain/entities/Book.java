package com.cesarwillymc.libraryprojectmpp.domain.entities;

import com.cesarwillymc.libraryprojectmpp.domain.annotation.EntityDomain;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

/**
 *
 */
@EntityDomain
final public class Book implements Serializable {

    @Serial
    private static final long serialVersionUID = 6110690276685962829L;
    private BookCopy[] copies;
    private final List<Author> authors;
    private final String isbn;
    private final String title;
    private final LocalDate dateCreated;
    private final LocalDate dateUpdated;


    public Book(String isbn, String title, List<Author> authors) {
        this.isbn = isbn;
        this.title = title;
        this.authors = Collections.unmodifiableList(authors);
        copies = new BookCopy[]{new BookCopy(getIsbn(), getTitle(), 1, true)};
        dateCreated = LocalDate.now();
        dateUpdated = LocalDate.now();
    }

    public Book(String isbn, String title, List<Author> authors, Integer numOfCopies) {
        this.isbn = isbn;
        this.title = title;
        this.authors = Collections.unmodifiableList(authors);
        copies = Stream.iterate(1, x -> x + 1).map(x -> new BookCopy(getIsbn(), getTitle(), x, true)).limit(numOfCopies).toArray(BookCopy[]::new);
        dateCreated = LocalDate.now();
        dateUpdated = LocalDate.now();
    }

    public Book(BookCopy[] copies, List<Author> authors, String isbn, String title, LocalDate dateCreated, LocalDate dateUpdated) {
        this.copies = copies;
        this.authors = authors;
        this.isbn = isbn;
        this.title = title;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    public void updateCopies(BookCopy copy) {
        for (int i = 0; i < copies.length; ++i) {
            BookCopy c = copies[i];
            if (c.equals(copy)) {
                copies[i] = copy;

            }
        }
    }

    public List<Integer> getCopyNums() {
        List<Integer> retVal = new ArrayList<>();
        for (BookCopy c : copies) {
            retVal.add(c.getCopyNum());
        }
        return retVal;

    }

    public void addCopies(Integer num) {
        var copiesNew = Stream.iterate(copies.length, x -> x + 1).map(x -> new BookCopy(getIsbn(), getTitle(), x, true)).limit(num).toList();
        var copyOld = new ArrayList<>(List.of(copies));
        copyOld.addAll(copiesNew);
        copies = copyOld.toArray(BookCopy[]::new);
    }


    @Override
    public boolean equals(Object ob) {
        if (ob == null) return false;
        if (ob.getClass() != getClass()) return false;
        Book b = (Book) ob;
        return b.isbn.equals(isbn);
    }


    public boolean isAvailable() {
        if (copies == null) {
            return false;
        }
        return Arrays.stream(copies)
                .map(BookCopy::isAvailable)
                .reduce(false, (x, y) -> x || y);
    }

    @Override
    public String toString() {
        return "isbn: " + isbn + ", maxLength: " + ", available: " + isAvailable();
    }

    public int getNumCopies() {
        return copies.length;
    }

    public String getTitle() {
        return title;
    }

    public BookCopy[] getCopies() {
        return copies;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public String getIsbn() {
        return isbn;
    }

    public BookCopy getNextAvailableCopy() {
        Optional<BookCopy> optional
                = Arrays.stream(copies)
                .filter(BookCopy::isAvailable).findFirst();
        return optional.orElse(null);
    }

    public Integer getTotalBookAvailable() {
        List<BookCopy> optional
                = Arrays.stream(copies)
                .filter(BookCopy::isAvailable).toList();
        return optional.size();
    }

    public BookCopy getCopy(int copyNum) {
        for (BookCopy c : copies) {
            if (copyNum == c.getCopyNum()) {
                return c;
            }
        }
        return null;
    }


    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public LocalDate getDateUpdated() {
        return dateUpdated;
    }
}
