package com.vanderkast.smsforward.abs;

public interface DataKeeper {
    String SMS_KEY = "SMS_KEY";

    <T> T get(String key, Class<T> clazz);

    void put(String key, Object data);

    void delete(String key);
}
