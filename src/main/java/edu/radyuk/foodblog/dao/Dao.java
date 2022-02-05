package edu.radyuk.foodblog.dao;

import edu.radyuk.foodblog.exception.DaoException;

import java.util.Optional;

/**
 * The interface Dao.
 *
 * @param <T> the type parameter
 */
public interface Dao<T> {

    /**
     * Find entity by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<T> findEntityById(long id) throws DaoException;

    /**
     * Insert long.
     *
     * @param entity the entity
     * @return the long
     * @throws DaoException the dao exception
     */
    long insert(T entity) throws DaoException;

    /**
     * Update long.
     *
     * @param entity the entity
     * @return the long
     * @throws DaoException the dao exception
     */
    long update(T entity) throws DaoException;

    /**
     * Remove entity by id long.
     *
     * @param id the id
     * @return the long
     * @throws DaoException the dao exception
     */
    long removeEntityById(long id) throws DaoException;
}
