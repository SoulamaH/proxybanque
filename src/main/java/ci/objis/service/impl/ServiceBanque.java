/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.service.impl;

import ci.objis.domaine.Compte;
import ci.objis.services.IServiceBanque;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Djabi
 */

@LocalBean
@Stateless
public class ServiceBanque implements IServiceBanque{
    
    

    @Override
    public Compte consulterCompte(String codeCpte) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void verser(String codeCpte, double montant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void retirer(String codeCpte, double montant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void virement(String codeCpte1, String codeCpte2, double montant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
