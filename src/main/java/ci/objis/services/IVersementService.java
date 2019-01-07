/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.services;

import ci.objis.domaine.Versement;
import java.util.List;
import javax.ejb.Local;

/**
 * interface qui etands IService (l'interface générique) et 
 * portant des méthodes propres qui seront implementées
 * 
 * @author Djabi
 */
@Local
public interface IVersementService extends IService<Versement, Long> {

   // Méthodes
    
    public Versement verser(Versement versement, String numeCompte); // virement
    
    public List<Versement> readAllVersementClient(Long idClient);
    
    public Boolean verificationExistanceCompte(String compte); // virement

     public String genererReference();//virement
    
    public Boolean verifNumeroRefenrence(String numero);//virement
    
    public Versement readOneByReference(String numeReference); //virement
    
    public String verifierTypeCompte(String numCompte);

}
