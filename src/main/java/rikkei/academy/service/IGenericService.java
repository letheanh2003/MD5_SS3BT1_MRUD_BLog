package rikkei.academy.service;

import java.util.Optional;

public interface IGenericService<T> {
    Iterable<T> findAll();
    void  save(T t);
    void remove(int id);
    Optional<T> findById(int id);
}
