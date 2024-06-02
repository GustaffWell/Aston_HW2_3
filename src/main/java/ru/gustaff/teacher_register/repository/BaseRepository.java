package ru.gustaff.teacher_register.repository;

import java.util.List;

public interface BaseRepository <T> {

    // null if not found, when updated
    T save(T object);

    // false if not found
    boolean delete(int id);

    // null if not found
    T get(int id);

    // null if not found
    List<T> getAll();
}
