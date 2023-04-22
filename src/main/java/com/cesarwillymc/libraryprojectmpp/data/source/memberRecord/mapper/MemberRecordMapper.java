package com.cesarwillymc.libraryprojectmpp.data.source.memberRecord.mapper;

import com.cesarwillymc.libraryprojectmpp.data.source.account.mapper.MemberMapper;
import com.cesarwillymc.libraryprojectmpp.data.source.customer.mapper.BookCopyMapper;
import com.cesarwillymc.libraryprojectmpp.data.source.memberRecord.entity.MemberRecordResponse;
import com.cesarwillymc.libraryprojectmpp.data.utils.mapper.Mapper;
import com.cesarwillymc.libraryprojectmpp.domain.entities.MemberRecord;

public class MemberRecordMapper extends Mapper<MemberRecord, MemberRecordResponse> {
    private final BookCopyMapper bookDataMapper;
    private final MemberMapper memberMapper;

    public MemberRecordMapper(BookCopyMapper bookDataMapper, MemberMapper memberMapper) {
        this.bookDataMapper = bookDataMapper;
        this.memberMapper = memberMapper;
    }

    @Override
    public MemberRecordResponse domainToData(MemberRecord data) {
        return new MemberRecordResponse(
                data.getId(),
                memberMapper.domainToData(data.getMemberResponse()),
                bookDataMapper.domainToData(data.getBook()),
                data.getDateBorrow(),
                data.getDateDue(),
                data.getDateReturned()
        );
    }

    @Override
    public MemberRecord dataToDomain(MemberRecordResponse data) {
        return new MemberRecord(
                data.id(),
                memberMapper.dataToDomain(data.memberResponse()),
                bookDataMapper.dataToDomain(data.book()),
                data.dateBorrow(),
                data.dateDue(),
                data.dateReturned()
        );
    }
}
