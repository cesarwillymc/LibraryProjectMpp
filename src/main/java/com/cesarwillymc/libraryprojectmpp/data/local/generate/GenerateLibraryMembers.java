package com.cesarwillymc.libraryprojectmpp.data.local.generate;

import com.cesarwillymc.libraryprojectmpp.data.local.StorageType;
import com.cesarwillymc.libraryprojectmpp.data.local.util.FileUtil;
import com.cesarwillymc.libraryprojectmpp.data.source.account.entity.AddressResponse;
import com.cesarwillymc.libraryprojectmpp.data.source.account.entity.LibraryMemberResponse;

import java.util.HashMap;
import java.util.List;

public class GenerateLibraryMembers {
    public static void main(String[] args) {
        var hashMap = new HashMap<String, LibraryMemberResponse>();
       var data =List.of(new LibraryMemberResponse("LM001", "Michael", "Brown", "(123) 456-7890", new AddressResponse("123 Main St", "New York", "NY", "10001")),
                new LibraryMemberResponse("LM002", "Jennifer", "Johnson", "(234) 567-8901", new AddressResponse("456 Elm St", "Los Angeles", "CA", "90001")),
                new LibraryMemberResponse("LM003", "Christopher", "Lee", "(345) 678-9012", new AddressResponse("789 Oak St", "Chicago", "IL", "60007")),
                new LibraryMemberResponse("LM004", "Jessica", "Garcia", "(456) 789-0123", new AddressResponse("321 Pine St", "Houston", "TX", "77001")),
                new LibraryMemberResponse("LM005", "David", "Martinez", "(567) 890-1234", new AddressResponse("654 Maple Ave", "Philadelphia", "PA", "19019")),
                new LibraryMemberResponse("LM006", "Amanda", "Harris", "(678) 901-2345", new AddressResponse("987 Cedar St", "Phoenix", "AZ", "85001")),
                new LibraryMemberResponse("LM007", "William", "Thomas", "(789) 012-3456", new AddressResponse("246 Walnut St", "San Antonio", "TX", "78201")),
                new LibraryMemberResponse("LM008", "Emily", "Davis", "(890) 123-4567", new AddressResponse("135 Oakwood Dr", "Seattle", "WA", "98101")),
                new LibraryMemberResponse("LM009", "Daniel", "Rodriguez", "(901) 234-5678", new AddressResponse("864 Pine St", "Boston", "MA", "02108")),
                new LibraryMemberResponse("LM010", "Taylor", "Scott", "(012) 345-6789", new AddressResponse("753 Oak St", "Miami", "FL", "33101"))
                );
       for (LibraryMemberResponse library : data){
           hashMap.put(library.getMemberId(),library);
       }
        FileUtil.saveToStorage(StorageType.MEMBERS, hashMap);

    }
}
