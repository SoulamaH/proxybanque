/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.dao;

import ci.objis.domaine.CompteCourant;
import javax.ejb.Local;

/**
 * ICompteCourantDao extends IDao
 *
 * @author USER
 */
@Local
public interface ICompteCourantDao extends IDao<CompteCourant, String> {

    public CompteCourant ReadOneByNumeCompte(String compte);

    // retourne le solde
    public Double readOneBySoldeCompte(String compte);

}
