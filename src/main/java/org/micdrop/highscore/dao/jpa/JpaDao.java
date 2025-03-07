package org.micdrop.highscore.dao.jpa;

import org.micdrop.highscore.dao.Dao;
import org.micdrop.highscore.model.AbstractModel;
import org.micdrop.highscore.persistence.JpaSessionManager;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

@Repository
public abstract class JpaDao<T extends AbstractModel> implements Dao<T> {

    protected Class<T> modelType;

    protected EntityManager em;

    public JpaDao(Class<T> modelType) {
        this.modelType = modelType;
    }

    public Class<T> getModelType() {
        return modelType;
    }

    public void setModelType(Class<T> modelType) {
        this.modelType = modelType;
    }

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    public EntityManager getEm() {
        return em;
    }

    @Override
    public List<T> findAll() {
        return em.createQuery("from " + modelType.getCanonicalName(), modelType).getResultList();
    }

    @Override
    public T findById(Integer id) {
        return em.find(modelType, id);
    }

    @Override
    public T saveOrUpdate(T model) throws PersistenceException {
        return em.merge(model);
    }

    @Override
    public void delete(Integer id) {
        try {
            em.remove(findById(id));
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }
    }
}
