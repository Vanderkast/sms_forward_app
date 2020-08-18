package com.vanderkast.smsforward.abs;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class DataKeeperImplTest {
    private DataKeeper dataKeeper;
    private final String key = "TEST_KEY";

    @Before
    public void setUp() throws Exception {
        dataKeeper = new DataKeeperImpl();
    }

    @Test
    public void test() {
        A a = new A();
        dataKeeper.put(key, a);
        A gotA = dataKeeper.get(key, a.getClass());
        assertNotNull(gotA);
        assertEquals(a, gotA);
    }

    static class A {

    }
}
