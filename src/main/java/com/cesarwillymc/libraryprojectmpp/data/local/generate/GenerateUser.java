package com.cesarwillymc.libraryprojectmpp.data.local.generate;

import com.cesarwillymc.libraryprojectmpp.data.local.StorageType;
import com.cesarwillymc.libraryprojectmpp.data.local.util.FileUtil;
import com.cesarwillymc.libraryprojectmpp.data.source.account.entity.AddressResponse;
import com.cesarwillymc.libraryprojectmpp.data.source.login.entity.UserResponse;
import com.cesarwillymc.libraryprojectmpp.domain.enums.TypeAuth;

import java.util.HashMap;

public class GenerateUser {
    public static void main(String[] args) {
        var userMap = new HashMap<String, UserResponse>();
        AddressResponse address1 = new AddressResponse("123 Main St", "New York", "NY", "10001");
        UserResponse user1 = new UserResponse("John", "Doe", "555-555-5555", address1, "1234", "123", TypeAuth.ADMIN, "https://cdn3.vectorstock.com/i/1000x1000/77/22/young-boy-cartoon-reading-book-vector-1457722.jpg");

        AddressResponse address2 = new AddressResponse("456 Oak Ave", "Los Angeles", "CA", "90001");
        UserResponse user2 = new UserResponse("Jane", "Smith", "555-444-3333", address2, "12345", "123", TypeAuth.EMPLOYEE, "https://mamiverse.com/wp-content/uploads/2015/01/Biblio-Zen-6-of-the-Best-Meditation-Books-to-Get-You-in-the-Mood-MainPhoto-e1498263109499-1.jpg");

        AddressResponse address3 = new AddressResponse("789 Elm St", "Chicago", "IL", "60601");
        UserResponse user3 = new UserResponse("Bob", "Johnson", "555-333-2222", address3, "123456", "123", TypeAuth.BOTH, "https://media.sproutsocial.com/uploads/2022/06/profile-picture.jpeg");

        userMap.put(user1.getId(), user1);
        userMap.put(user2.getId(), user2);
        userMap.put(user3.getId(), user3);

        FileUtil.saveToStorage(StorageType.USERS, userMap);
    }
}
