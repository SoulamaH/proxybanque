/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.service.impl;

import ci.objis.dao.imp.CompteEpargneDao;
import ci.objis.domaine.CompteEpargne;
import ci.objis.services.ICompteEpargneService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author USER
 */
@Stateless
@LocalBean
public class CompteEpargneService implements ICompteEpargneService {

    //objets gerés par EJB
    @EJB
    private CompteEpargneDao compteEpargneDao;

    @EJB
    private NumeroCompteService numeroCompteService;

    private CompteEpargne compteEpargne;

    /**
     * Mes méthodes de CRUD ( ajouter, readOne, readAll, modifier et supprimer)
     * aussi le count pour la taille
     *
     * @param t
     * @return
     */
    //suppression
    @Override
    public boolean supprimer(CompteEpargne t) {
        return compteEpargneDao.supprimer(t);
    }

    //liste de tous les elements
    @Override
    public List<CompteEpargne> readAll() {
        return compteEpargneDao.readAll();
    }

    //nombre d'element
    @Override
    public Integer count() {
        return compteEpargneDao.readAll().size();
    }

    //enregistrer
    @Override
    public CompteEpargne ajouter(CompteEpargne t) {
        return compteEpargneDao.ajouter(t);
    }

    //modifier
    @Override
    public CompteEpargne modifier(CompteEpargne t) {
        return compteEpargneDao.modifier(t);
    }

    //lire un element
    @Override
    public CompteEpargne readOne(String pk) {
        return compteEpargneDao.readOne(pk);
    }

    /**
     * *
     * verifi qu'il n'y est pas de doublon de numero de compte d&ans la BD
     *
     * @param numero
     * @return
     */
    // verification du numero de compte dans la base de donnée
    @Override
    public Boolean verifNumeroCompte(String numero) {
        if (readOne(numeroCompteService.generateNumeroCompte()) != null) {
            return true;
        }
        return false;
    }

    /**
     * genère de nouveau numero de reference tant que celui ci existe dans la BD
     *
     * @return numerGenerer
     */
    // elle regroupe les deux methodes en elle "geretNumeroCompte et verifNumeroCompte"
    @Override
    public String genererNumero() {
        String numeGenerer = numeroCompteService.generateNumeroCompte();

        while (verifNumeroCompte(numeGenerer)) {
            numeGenerer = numeroCompteService.generateNumeroCompte();

        }
        return numeGenerer;
    }

    //retourne le numero de compte
    @Override
    public CompteEpargne ReadOneByNumeCompte(String compte) {
        return compteEpargneDao.ReadOneByNumeCompte(compte);
    }

    @Override
    public CompteEpargne readOneBySoldeCompte(String compte) {
        return compteEpargneDao.readOneBySoldeCompte(compte);
    }

    /**
     * verifie le niveau de solde dans la BD au montant qui sera envoie par le
     * JSP
     *
     * @param montant
     * @param compte
     * @return
     */
    @Override
    public Boolean verificationSolde(Double montant, String compte) {

        // recuperation du compe par son numero
        compteEpargne = ReadOneByNumeCompte(compte);

        // condition sur le solde
        /* On verifie si le niveau du solde dans la BD est ">=" au montant recuperé par la JSP
          mais d'abord on verifie si compte est different de null*/
        if (compteEpargne != null) {
            if (compteEpargne.getSolde() >= montant) {
                System.out.println(" ");
                System.out.println("verificationsolde compteeprgneservice " +compteEpargne.getSolde() + " " + montant);
                System.out.println(" ");
                return false;
            }
        }
        return true;
    }

}
