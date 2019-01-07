/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.dao;

import ci.objis.domaine.Retrait;
import javax.ejb.Local;

/**
 * IRetraitDao extends IDao qui est la Dao generique
 *
 * @author USER
 */
@Local
public interface IRetraitDao extends IDao<Retrait, Long> {

    // Méthodes
    public Retrait readOneByReference(String numeReference);

}
