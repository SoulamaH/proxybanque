/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.services;

import javax.ejb.Local;

/**
 * INumeroCompteService posséde une méthode permettant de generer des numeros 
 * @author USER
 */
@Local
public interface INumeroCompteService {

    // methode de generation du numero de compte
    public String generateNumeroCompte();

   
}
