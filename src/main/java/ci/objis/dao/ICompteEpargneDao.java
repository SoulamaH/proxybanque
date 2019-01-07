/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.dao;

import ci.objis.domaine.CompteEpargne;
import javax.ejb.Local;

/**
 * ICompteEpargneDao extends IDao
 *
 * @author USER
 */
@Local
public interface ICompteEpargneDao extends IDao<CompteEpargne, String> {

    //retourn le numero de compte 
    public CompteEpargne ReadOneByNumeCompte(String compte);

    // retourne le solde
    public CompteEpargne readOneBySoldeCompte(String compte);

}
