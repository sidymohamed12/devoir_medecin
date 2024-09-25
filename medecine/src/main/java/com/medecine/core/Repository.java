package com.medecine.core;

import java.util.List;

public interface Repository<T> {
    void insert(T value);

    List<T> selectAll();

    T getById(int id);
}
