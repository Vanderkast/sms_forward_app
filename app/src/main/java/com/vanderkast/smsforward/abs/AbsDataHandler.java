package com.vanderkast.smsforward.abs;

public abstract class AbsDataHandler extends AbsHandler {
    private final DataKeeper dataKeeper;

    protected AbsDataHandler(DataKeeper dataKeeper) {
        this.dataKeeper = dataKeeper;
    }
}
