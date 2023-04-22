package com.cesarwillymc.libraryprojectmpp.domain.entities;

import com.cesarwillymc.libraryprojectmpp.domain.annotation.EntityDomain;

import java.io.Serial;
import java.io.Serializable;

@EntityDomain
final public class Author extends Person implements Serializable {
    private final String bio;

    public String getBio() {
        return bio;
    }

    public Author(String f, String l, String t, Address a, String bio) {
        super(f, l, t, a);
        this.bio = bio;
    }
    public Author(String f, String l) {
        super(f, l, "", null);
        this.bio = "";
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName();
    }

    @Serial
    private static final long serialVersionUID = 7508481940058530471L;
}
