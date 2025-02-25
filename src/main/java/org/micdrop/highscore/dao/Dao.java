package org.micdrop.highscore.dao;

import org.micdrop.highscore.model.AbstractModel;

import java.util.List;

public interface Dao <T extends AbstractModel>{

    List<T> findAll();

    T findById(Integer id);

    T saveOrUpdate(T model);

    void delete(Integer id);
}
