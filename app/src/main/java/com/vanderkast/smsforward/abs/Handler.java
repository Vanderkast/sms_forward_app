package com.vanderkast.smsforward.abs;

public interface Handler {
    void addNext(Handler handler);
    void proceed();
}
