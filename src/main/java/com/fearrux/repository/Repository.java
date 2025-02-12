package com.fearrux.repository;

import java.util.Optional;

public interface Repository<T, ID> {
    Optional<T> findById(ID id);
    T save(T entity);
}
