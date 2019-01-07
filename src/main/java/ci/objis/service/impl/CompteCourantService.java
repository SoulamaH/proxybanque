/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.service.impl;

import ci.objis.dao.imp.CompteCourantDao;
import ci.objis.domaine.CompteCourant;
import ci.objis.services.ICompteCourantService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Djabi
 */
@LocalBean
@Stateless
public class CompteCourantService implements ICompteCourantService {

    // objets
    @EJB
    private CompteCourantDao compteCourantDao;

    @EJB
    private NumeroCompteService numeroCompteService;
    
    private CompteCourant compteCourant;
    
   /**
    *  Mes méthodes de CRUD ( ajouter, readOne, readAll, modifier et supprimer)
     * aussi le count pour la taille
    * @param t
    * @return 
    */
    //suppression
    @Override
    public boolean supprimer(CompteCourant t) {
        return compteCourantDao.supprimer(t);
    }

    //liste de tous les elements
    @Override
    public List<CompteCourant> readAll() {
        return compteCourantDao.readAll();
    }

    //nombre des element dans la BD
    @Override
    public Integer count() {
        return compteCourantDao.readAll().size();
    }

    //enregistrement
    @Override
    public CompteCourant ajouter(CompteCourant t) {
        return compteCourantDao.ajouter(t);

    }

    //modification
    @Override
    public CompteCourant modifier(CompteCourant t) {
        return compteCourantDao.modifier(t);
    }

    //lire un element
    @Override
    public CompteCourant readOne(String pk) {
        return compteCourantDao.readOne(pk);
    }

    /**
     * verifi qu'il n'y est pas de doublon de numero de compte d&ans la BD
     * @param numero
     * @return 
     */
    // verifie les numeros de comptes
    @Override
    public Boolean verifNumeroCompte(String numero) {
        if (readOne(numeroCompteService.generateNumeroCompte()) != null) {
            return true;
        }
        return false;

    }

    /**
     * genère de nouveau numero de reference tant que celui ci existe dans la BD
     * @return 
     */
    // elle regroupe les deux methodes en elle "geretNumeroCompte et verifNumeroCompte"
    @Override
    public String genererNumero() {
        String numeGenerer = numeroCompteService.generateNumeroCompte();

        // verifie qu'il n y est pas de doublon
        while (verifNumeroCompte(numeGenerer)) {
            numeGenerer = numeroCompteService.generateNumeroCompte();

        }
        return numeGenerer;
    }

    // retourn le numero de compte 
    @Override
    public CompteCourant ReadOneByNumeCompte(String compte) {
        return compteCourantDao.ReadOneByNumeCompte(compte);
    }

    // retourne le solde
    @Override
    public Double readOneBySoldeCompte(String compte) {
        return compteCourantDao.readOneBySoldeCompte(compte);
    }

    /**
     * 
     * verifie le niveau de solde dans la BD au montant qui sera envoie par le JSP
     * @param montant
     * @param compte
     * @return 
     */
    @Override
    public Boolean verificationSolde(Double montant, String compte) {
        
        // recuperation du compte par son numero
        compteCourant = ReadOneByNumeCompte(compte);
        
        // condition sur le solde
        /* On verifie si le niveau du solde dans la BD est ">=" au montant recuperé par la JSP
          mais d'abord on verifie si compte est different de null*/
        if (compteCourant != null) {
            if (compteCourant.getSolde() >= montant) {
                System.out.println(" ");
                System.out.println("verificationsolde compteeprgneservice " +compteCourant.getSolde() + " " + montant);
                System.out.println(" ");
                return false;
            }
        }
        return true;
    }
  
}
