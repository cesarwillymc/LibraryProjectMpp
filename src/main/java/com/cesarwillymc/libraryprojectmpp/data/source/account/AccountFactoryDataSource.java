package com.cesarwillymc.libraryprojectmpp.data.source.account;

import com.cesarwillymc.libraryprojectmpp.data.local.DataAccessDao;
import com.cesarwillymc.libraryprojectmpp.data.source.account.mapper.MemberMapper;

public class AccountFactoryDataSource {
    public static MemberDataSource getMemberDataSource(DataAccessDao access) {
        return new MemberRepository(access, new MemberMapper());
    }
}
