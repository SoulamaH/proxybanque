/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.services;

import ci.objis.domaine.Compte;
import javax.ejb.Local;

/**
 *
 * @author Djabi
 */
@Local
public interface IServiceBanque {

    public Compte consulterCompte(String codeCpte);

    public void verser(String codeCpte, double montant);

    public void retirer(String codeCpte, double montant);
    
    public void virement(String codeCpte1, String codeCpte2, double montant);


}
