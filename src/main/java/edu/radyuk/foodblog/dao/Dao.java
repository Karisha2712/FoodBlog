package edu.radyuk.foodblog.dao;

import edu.radyuk.foodblog.exception.DaoException;

import java.util.List;

public interface Dao<T> {

    List<T> findAll() throws DaoException;

    T findEntityById(long id) throws DaoException;

    long insert(T entity) throws DaoException;

    long update(T entity) throws DaoException;

    long remove(T entity) throws DaoException;

    long removeEntityById(long id) throws DaoException;
}
