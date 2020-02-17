package com.vanderkast.smsforward.model.handlers;

public interface DataHandler<T> {
    void save(T data);
    T get();
}
