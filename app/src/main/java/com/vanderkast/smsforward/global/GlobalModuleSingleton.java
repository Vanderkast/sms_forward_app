package com.vanderkast.smsforward.global;

public class GlobalModuleSingleton {
    private static GlobalModule module;

    public GlobalModuleSingleton(GlobalModule module) {
        GlobalModuleSingleton.module = module;
    }

    public static GlobalModule getModule() {
        return module;
    }
}
