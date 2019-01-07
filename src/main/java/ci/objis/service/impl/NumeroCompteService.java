/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.service.impl;

import ci.objis.services.INumeroCompteService;
import java.util.Random;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author USER
 */
@LocalBean
@Stateless
public class NumeroCompteService implements INumeroCompteService {

    /**
     * methode generant un numero qui sera assigné au compte
     * @return code
     */
    // methode generant le numero de compte
    @Override
    public String generateNumeroCompte() {

        String matricul = "0123456789AZERTYUIOPQSDFGHJKLMWXCVBN";

        // initialisation de la variable
        String code = "";
        
        // Objet permettant de faire des tries aléatoir
        Random aleatoir = new Random(); 

        for (int compt = 1; compt <= 6; compt++) {

            code = code + matricul.charAt(aleatoir.nextInt(matricul.length()));

        }
        return code;
    }
}
