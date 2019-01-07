/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.services;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface generique
 * 
 * @author Djabi
 * @param <T>
 * @param <PK>
 */
@Local
public interface IService<T, PK extends Serializable> {

    public T ajouter(final T t);

    public T modifier(final T t);

    public boolean supprimer(final T t);

    public List<T> readAll();

    public T readOne(final PK pk);

    public Integer count();

}
