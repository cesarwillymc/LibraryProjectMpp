package com.cesarwillymc.libraryprojectmpp.data.source.memberRecord;

import com.cesarwillymc.libraryprojectmpp.data.local.DataAccessDao;
import com.cesarwillymc.libraryprojectmpp.data.source.account.mapper.AddressMapper;
import com.cesarwillymc.libraryprojectmpp.data.source.account.mapper.MemberMapper;
import com.cesarwillymc.libraryprojectmpp.data.source.customer.mapper.BookDataMapper;
import com.cesarwillymc.libraryprojectmpp.data.source.memberRecord.mapper.MemberRecordMapper;

public class MemberRecordFactoryDataSource {
    public static MemberRecordDataSource getMemberRecordDataSource(DataAccessDao access) {
        return new MemberRecordRepository(access, new MemberRecordMapper(new BookDataMapper(), new MemberMapper(new AddressMapper())));
    }
}
