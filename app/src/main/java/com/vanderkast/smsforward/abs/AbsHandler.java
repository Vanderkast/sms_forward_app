package com.vanderkast.smsforward.abs;

public abstract class AbsHandler implements Handler {
    private Handler next;

    @Override
    public void addNext(Handler handler) {
        if (next == null)
            next = handler;
        else
            next.addNext(handler);
    }

    @Override
    public void proceed() {
        if (next != null)
            next.proceed();
    }
}
