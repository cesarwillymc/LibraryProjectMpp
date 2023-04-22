package com.cesarwillymc.libraryprojectmpp.data.local.generate;

import com.cesarwillymc.libraryprojectmpp.data.local.StorageType;
import com.cesarwillymc.libraryprojectmpp.data.local.util.FileUtil;
import com.cesarwillymc.libraryprojectmpp.data.source.account.entity.LibraryMemberResponse;
import com.cesarwillymc.libraryprojectmpp.data.source.account.mapper.AddressMapper;
import com.cesarwillymc.libraryprojectmpp.data.source.account.mapper.MemberMapper;
import com.cesarwillymc.libraryprojectmpp.data.source.customer.entity.BookResponse;
import com.cesarwillymc.libraryprojectmpp.data.source.customer.mapper.BookCopyMapper;
import com.cesarwillymc.libraryprojectmpp.data.source.customer.mapper.BookDataMapper;
import com.cesarwillymc.libraryprojectmpp.domain.entities.Book;
import com.cesarwillymc.libraryprojectmpp.domain.entities.LibraryMember;
import com.cesarwillymc.libraryprojectmpp.ui.checkoutBook.controller.CheckoutController;
import com.cesarwillymc.libraryprojectmpp.ui.di.DIControllers;
import kotlin.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GenerateMemberRecord {
    public static void main(String[] args) {
        var mapper = new MemberMapper(new AddressMapper());
        var mapper2 = new BookDataMapper(new BookCopyMapper());
        var readMembers =((HashMap<String,LibraryMemberResponse>) FileUtil.readFromStorage(StorageType.MEMBERS)).values().stream().map(mapper::dataToDomain).toList();
        var readBooks =( (HashMap<String, BookResponse> )FileUtil.readFromStorage(StorageType.BOOKS)).values().stream().map(mapper2::dataToDomain).toList();

        CheckoutController controller = DIControllers.createCheckoutController();
        Runnable runnable = () -> {
            for (int i =0;i<20;i++){

                    Pair<Book, LibraryMember> dataaaa =GenerateMemberRecord.getRandomObjects(
                        readBooks,
                            readMembers
                    );
                    controller.setLibraryMember(
                            dataaaa.component2()
                    );
                    controller.setBook(
                            dataaaa.component1()
                    );
                    controller.saveMemberCheckout();

            }
        };
        runnable.run();

    }
    public static <T, U> Pair<T, U> getRandomObjects(List<T> list1, List<U> list2) {
        Random rand = new Random();

        T object1 = list1.get(rand.nextInt(list1.size()));
        U object2 = list2.get(rand.nextInt(list2.size()));

        return new Pair<T, U>(object1, object2);
    }

}
