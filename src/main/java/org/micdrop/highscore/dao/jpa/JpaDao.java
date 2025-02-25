package org.micdrop.highscore.dao.jpa;

import org.micdrop.highscore.dao.Dao;
import org.micdrop.highscore.model.AbstractModel;
import org.micdrop.highscore.persistence.JpaSessionManager;

import javax.persistence.PersistenceException;
import java.util.List;

public abstract class JpaDao<T extends AbstractModel> implements Dao<T> {

    protected Class<T> modelType;
    protected JpaSessionManager sm;

    public JpaDao(Class<T> modelType) {
        this.modelType = modelType;
    }

    public Class<T> getModelType() {
        return modelType;
    }

    public void setModelType(Class<T> modelType) {
        this.modelType = modelType;
    }

    public JpaSessionManager getSm() {
        return sm;
    }

    public void setSm(JpaSessionManager sm) {
        this.sm = sm;
    }

    @Override
    public List<T> findAll() {
        return sm.getCurrentSession().createQuery("from " + modelType.getCanonicalName(), modelType).getResultList();
    }

    @Override
    public T findById(Integer id) {
        return sm.getCurrentSession().find(modelType, id);
    }

    @Override
    public T saveOrUpdate(T model) {
        try {
            return sm.getCurrentSession().merge(model);
        } catch (PersistenceException e) {
            throw e;
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            sm.getCurrentSession().remove(findById(id));
        } catch (PersistenceException e) {
            throw e;
        }
    }
}
