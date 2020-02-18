package com.vanderkast.smsforward.global;

/**
 * It's bad practice to work with context,
 *  but I still didn't find the best way to work with android fields.
 *  Improvement is adding onDestroy listening:
 *      when activity (f.e) is going to be destroyed every client must to remove its' activity dependency
 *      to avoid memory leak and NullPointerExceptions.
 */
public class GlobalModuleSingleton {
    private static GlobalModule module;

    public GlobalModuleSingleton(GlobalModule module) {
        GlobalModuleSingleton.module = module;
    }

    public static GlobalModule getModule() {
        return module;
    }
}
