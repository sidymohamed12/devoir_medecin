package com.medecine.core;

import java.util.List;

public interface Service<T> {
    void create(T value);

    List<T> findAll();

    T getById(int id);
}
