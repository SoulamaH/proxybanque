/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.dao.imp;

import ci.objis.dao.IDao;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Dao genérique
 *
 * @author Djabi
 * @param <T>
 * @param <PK>
 */
//@LocalBean
//@Stateless
public class Dao<T, PK extends Serializable> implements IDao<T, PK> {

    // proprietes
    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    private final Class<T> entity;

    private Query query;

    // constructeur
    public Dao(Class<T> entity) {
        this.entity = entity;
    }
    
    @Override
    public T ajouter(final T t) {
        em.persist(t);
        return t;
    }

    @Override
    public T modifier(final T t) {
        em.merge(t);
        return t;
    }

    @Override
    public boolean supprimer(final T t) {
        em.remove(t);

        if (t == null) {
            return true;
        }
        return false;
    }

    @Override
    public List<T> readAll() {
        query = em.createQuery("SELECT o FROM " + entity.getSimpleName());
        return query.getResultList();
    }

    @Override
    public T readOne(final PK pk) {
        return em.find(entity, pk);
    }

}
