package com.cesarwillymc.libraryprojectmpp.data.utils.mapper;

import com.cesarwillymc.libraryprojectmpp.domain.annotation.EntityData;
import com.cesarwillymc.libraryprojectmpp.domain.annotation.EntityDomain;

import java.util.List;

public abstract class Mapper<@EntityDomain I, @EntityData O> {
    public abstract O domainToData(I data);

    public abstract I dataToDomain(O data);

    public List<O> domainToData(List<I> data){
       return data.stream().map(this::domainToData).toList();
    }

    public List<I> dataToDomain(List<O> data){
       return data.stream().map(this::dataToDomain).toList();
    }
}
