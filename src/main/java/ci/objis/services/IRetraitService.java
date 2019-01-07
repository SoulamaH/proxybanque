/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.services;

import ci.objis.domaine.Retrait;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface IRetraitService extends IService<Retrait, Long>{

    // Methodes
    public Retrait retrait(Retrait retrait , String numeCompte); // virement

    public List<Retrait> readAllRetraitClient(Long idClient);

    public Boolean verificationExistanceCompte(String compte); // virement

    public String genererReference();//virement

    public Boolean verifNumeroRefenrence(String numero);//virement

    public Retrait readOneByReference(String numeReference); //virement

    public String verifierTypeCompte(String numCompte);
    
     public Boolean verifierSolde(String numCompte, Double montant);
}
