/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.dao;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;

/**
 * Dao generique
 *
 * @author Djabi
 * @param <T>
 * @param <PK>
 */
@Local
public interface IDao<T, PK extends Serializable> {

    //methodes generique
    public T ajouter(final T t);

    public T modifier(final T t);

    public boolean supprimer(final T t);

    public List<T> readAll();

    public T readOne(final PK pk);

}
