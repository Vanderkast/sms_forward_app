package com.vanderkast.smsforward.abs;

import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public class DataKeeperImpl implements DataKeeper {
    private final Map<String, Object> data;

    public DataKeeperImpl() {
        data = new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Nullable
    public <T> T get(String key, Class<T> clazz) {
        Object value = data.get(key);
        if(value != null && value.getClass().equals(clazz))
            return (T) value;
        return null;
    }

    @Override
    public void put(String key, Object data) {
        this.data.put(key, data);
    }

    @Override
    public void delete(String key) {
        data.remove(key);
    }
}
