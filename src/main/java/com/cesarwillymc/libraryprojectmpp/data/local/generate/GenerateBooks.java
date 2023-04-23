package com.cesarwillymc.libraryprojectmpp.data.local.generate;

import com.cesarwillymc.libraryprojectmpp.data.local.StorageType;
import com.cesarwillymc.libraryprojectmpp.data.local.util.FileUtil;
import com.cesarwillymc.libraryprojectmpp.data.source.customer.entity.BookCopyResponse;
import com.cesarwillymc.libraryprojectmpp.data.source.customer.entity.BookResponse;
import com.cesarwillymc.libraryprojectmpp.domain.entities.Author;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class GenerateBooks {
    public static void main(String[] args) {
        BookResponse[] books = new BookResponse[11];

        books[0] = new BookResponse(
                new BookCopyResponse[]{new BookCopyResponse("9780521799561", "The Catcher in the Rye", 1, true)},
                List.of(new Author("J.D.", "Salinger")),
                "9780521799561",
                "The Catcher in the Rye",
                LocalDate.of(2022, 1, 1),
                LocalDate.of(2022, 1, 2)
        );
        books[1] = new BookResponse(
                new BookCopyResponse[]{new BookCopyResponse("9780670020553", "The Fault in Our Stars", 1, true)},
                List.of(new Author("John", "Green")),
                "9780670020553",
                "The Fault in Our Stars",
                LocalDate.of(2022, 1, 3),
                LocalDate.of(2022, 1, 4)
        );
        books[2] = new BookResponse(
                new BookCopyResponse[]{new BookCopyResponse("9780061122415", "To Kill a Mockingbird", 1, true), new BookCopyResponse("9780061122415", "To Kill a Mockingbird", 1, true)},
                List.of(new Author("Harper", "Lee")),
                "9780061122415",
                "To Kill a Mockingbird",
                LocalDate.of(2022, 1, 5),
                LocalDate.of(2022, 1, 6)
        );
        books[3] = new BookResponse(
                new BookCopyResponse[]{new BookCopyResponse("9780743273565", "The Da Vinci Code", 1, true)},
                List.of(new Author("Dan", "Brown")),
                "9780743273565",
                "The Da Vinci Code",
                LocalDate.of(2022, 1, 7),
                LocalDate.of(2022, 1, 8)
        );
        books[4] = new BookResponse(
                new BookCopyResponse[]{new BookCopyResponse("9780739326222", "The Kite Runner", 1, true), new BookCopyResponse("9780739326222", "The Kite Runner", 2, true)},
                List.of(new Author("Khaled", "Hosseini")),
                "9780739326222",
                "The Kite Runner",
                LocalDate.of(2022, 1, 9),
                LocalDate.of(2022, 1, 10)
        );
        books[5] = new BookResponse(
                new BookCopyResponse[]{new BookCopyResponse("9780132350884", "Effective Java: Programming Language Guide", 1, true)},
                List.of(new Author("Joshua", "Bloch")),
                "9780132350884",
                "Effective Java: Programming Language Guide",
                LocalDate.of(2022, 1, 1),
                LocalDate.of(2022, 1, 2)
        );
        books[6] = new BookResponse(
                new BookCopyResponse[]{new BookCopyResponse("9780137081073", "Clean Code: A Handbook of Agile Software Craftsmanship", 1, true)},
                List.of(new Author("Robert", "Martin")),
                "9780137081073",
                "Clean Code: A Handbook of Agile Software Craftsmanship",
                LocalDate.of(2022, 1, 3),
                LocalDate.of(2022, 1, 4)
        );
        books[7] = new BookResponse(
                new BookCopyResponse[]{new BookCopyResponse("9780321714114", "The C++ Programming Language", 1, true)},
                List.of(new Author("Bjarne", "Stroustrup")),
                "9780321714114",
                "The C++ Programming Language",
                LocalDate.of(2022, 1, 5),
                LocalDate.of(2022, 1, 6)
        );
        books[8] = new BookResponse(
                new BookCopyResponse[]{new BookCopyResponse("9780596009205",   "Head First Design Patterns", 1, true)},
                List.of(new Author("Eric", "Freeman"), new Author("Elisabeth", "Robson")),
                "9780596009205",
                "Head First Design Patterns",
                LocalDate.of(2022, 1, 7),
                LocalDate.of(2022, 1, 8)
        );
        books[9] = new BookResponse(
                new BookCopyResponse[]{new BookCopyResponse("9780132350884", "Effective Java: Programming Language Guide", 1, true)},
                List.of(new Author("Joshua", "Bloch")),
                "9780132350884",
                "Effective Java: Programming Language Guide",
                LocalDate.of(2022, 1, 9),
                LocalDate.of(2022, 1, 10)
        );
        books[10] = new BookResponse(
                new BookCopyResponse[]{new BookCopyResponse("9781491904244", "You Don't Know JS", 1, true)},
                List.of(new Author("Kyle", "Simpson")),
                "9781491904244",
                "You Don't Know JS",
                LocalDate.of(2022, 1, 11),
                LocalDate.of(2022, 1, 12)
        );

        var data =new HashMap<String, BookResponse>();
        for (BookResponse book : books){
            data.put(book.isbn(),book);
        }
       //FileUtil.saveToStorage(StorageType.BOOKS, data);


        HashMap<String, BookResponse> dataRead =FileUtil.readFromStorage(StorageType.BOOKS);
        dataRead.values().stream().forEach(
                System.out::println
        );

    }
}
