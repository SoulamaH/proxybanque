/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.services;

import ci.objis.domaine.CompteEpargne;
import javax.ejb.Local;

/**
 * ICompteEpargneService extends IService (l'interface générique)
 * et ses methodes (verifNumeroCompte(String numero),genererNumero(),
 * ReadOneByNumeCompte(String compte),readOneBySoldeCompte(String compte),
 * verificationSolde(Double montant, String compte)
 * @author Djabi
 */
@Local
public interface ICompteEpargneService extends IService<CompteEpargne, String> {

    // methode de verification de l'existance du numero de compte dans la BD
    public Boolean verifNumeroCompte(String numero);

    // methode de generation du numero de compte
    public String genererNumero();

    // methode pour retourner le numero de compte
    public CompteEpargne ReadOneByNumeCompte(String compte);

    // retourne le solde
    public CompteEpargne readOneBySoldeCompte(String compte);
    
    // verifie le solde
    public Boolean verificationSolde(Double montant, String compte);

}
