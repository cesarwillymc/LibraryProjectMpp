package com.cesarwillymc.libraryprojectmpp.domain.resource;

import java.util.Optional;
import java.util.function.Consumer;

@SuppressWarnings("ALL")
public class Resource<T> {
    private Optional<Object> data;
    private Optional<Exception> exception;

    Resource(Exception e) {
        exception = Optional.of(e);
        data = Optional.empty();
    }

    Resource(T da) {
        exception = Optional.empty();
        data = Optional.of(da);
    }

    public static <T> Resource<T> Sucess(T data) {
        return new Resource<>(data);
    }

    public static <T> Resource<T> Error(Exception exception) {
        return new Resource<T>(exception);
    }

    public T getData() {
        return (T) data.get();
    }

    public synchronized void apply(Consumer<T> consumer, Consumer<Exception> fail) {
        if (exception.isPresent()) {
            fail.accept(exception.get());
        } else {
            consumer.accept((T) data.get());
        }
    }
}
