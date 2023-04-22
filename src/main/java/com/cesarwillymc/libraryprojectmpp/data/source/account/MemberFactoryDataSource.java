package com.cesarwillymc.libraryprojectmpp.data.source.account;

import com.cesarwillymc.libraryprojectmpp.data.local.DataAccessDao;
import com.cesarwillymc.libraryprojectmpp.data.source.account.mapper.AddressMapper;
import com.cesarwillymc.libraryprojectmpp.data.source.account.mapper.MemberMapper;

public class MemberFactoryDataSource {
    public static MemberDataSource getMemberDataSource(DataAccessDao access) {
        return new MemberRepository(access, new MemberMapper(new AddressMapper()));
    }
}
