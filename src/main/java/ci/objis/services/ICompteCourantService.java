/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.services;

import ci.objis.domaine.CompteCourant;
import javax.ejb.Local;

/**
 * ICompteCourantService interface qui etends IService (l'interface générique)
 * et a des méthodes propre qui seront implemetées
 * 
 * @author Djabi
 */
@Local
public interface ICompteCourantService extends IService<CompteCourant, String> {

    //methode de verification de l'existance du numero de compte dans la BD
    public Boolean verifNumeroCompte(String numero);

    // methode de generation du numero de compte
    public String genererNumero();

    //methode 
    public CompteCourant ReadOneByNumeCompte(String compte);

    // retourne le solde
    public Double readOneBySoldeCompte(String compte);

    // verifie le solde
    public Boolean verificationSolde(Double montant, String compte);

}
