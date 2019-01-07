/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.service.impl;

import ci.objis.dao.imp.RetraitDao;
import ci.objis.domaine.CompteCourant;
import ci.objis.domaine.CompteEpargne;
import ci.objis.domaine.Retrait;
import ci.objis.services.IRetraitService;
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
public class RetraitService implements IRetraitService {

    // objets gerés par les EJB
    @EJB
    private RetraitDao retraitDao;

    @EJB
    private NumeroCompteService numeroCompteService;

    @EJB
    private CompteCourantService compteCourantService;

    @EJB
    private CompteEpargneService compteEpargneService;

    /**
     * Mes méthodes de CRUD ( ajouter, readOne, readAll, modifier et supprimer)
     * aussi le count pour la taille
     *
     * @param t
     * @return
     */
    @Override
    public Retrait ajouter(Retrait t) {
        return retraitDao.ajouter(t);
    }

    @Override
    public Retrait modifier(Retrait t) {
        return retraitDao.modifier(t);
    }

    @Override
    public boolean supprimer(Retrait t) {
        return retraitDao.supprimer(t);
    }

    @Override
    public List<Retrait> readAll() {
        return retraitDao.readAll();
    }

    @Override
    public Retrait readOne(Long pk) {
        return retraitDao.readOne(pk);
    }

    @Override
    public Integer count() {
        return retraitDao.readAll().size();
    }

    /**
     * Cette methode permet de faire un versement àpres avoir vérifié le type
     * compte
     *
     * @param versement
     * @param numeCompte
     * @return
     */
    // méthode pour le versement
    @Override
    public Retrait retrait(Retrait retrait, String numeCompte) {

        // Objets initialisation
        CompteCourant compteCourant = null;
        CompteEpargne compteEpargne = null;

        if (verifierTypeCompte(numeCompte).equals("CompteCourant")) {
            /*on recupère le numero de compte auquel on ajoute le montant (solde initial - montant entré)*/
            compteCourant = compteCourantService.readOne(numeCompte);
            compteCourant.setSolde(compteCourant.getSolde() - retrait.getMontant());
            retrait.setCompte(compteCourant);

        } else {
            /*on recupère le numero de compte auquel on ajoute le montant (solde initial - montant entré)*/
            compteEpargne = compteEpargneService.readOne(numeCompte);
            compteEpargne.setSolde(compteEpargne.getSolde() - retrait.getMontant());
            retrait.setCompte(compteEpargne);

        }
        return retraitDao.ajouter(retrait);
    }

    /**
     * Liste retrait par client
     *
     * @param idClient
     * @return
     */
    @Override
    public List<Retrait> readAllRetraitClient(Long idClient) {
        return retraitDao.readAll();
    }

    /**
     *
     * Méthode verifant l'existance du compte grace a son numero de compte
     *
     * @param compte
     * @return
     */
    @Override
    public Boolean verificationExistanceCompte(String compte) {

        //Recuperation des numeros de compte
        CompteCourant compteCourant = compteCourantService.ReadOneByNumeCompte(compte);
        CompteEpargne compteEpargne = compteEpargneService.ReadOneByNumeCompte(compte);

        // conditon d'existance des numeros des comptes
        if (compteCourant != null) {
            return true;
        } else if (compteEpargne != null) {
            return true;
        }
        return false;
    }

    /**
     * genère de nouveau numero de reference tant que celui ci existe dans la BD
     *
     * @return reference
     */
    //genere le numero de reference
    @Override
    public String genererReference() {

        //declaration d'une variable plus affectation du numero generé
        String reference = numeroCompteService.generateNumeroCompte();

        /* condition tanque le numero existe dans la BD on continue de generer
            les numeros jusqu'a ce qu'il n'y est pas de doublon*/
        while (verifNumeroRefenrence(reference)) {
            reference = numeroCompteService.generateNumeroCompte();
        }
        return reference;
    }

    /**
     * verifie si le numero de reference existe deja dans la BD
     *
     * @param numero
     * @return
     */
    // verifie le numero de reference 
    @Override
    public Boolean verifNumeroRefenrence(String numero) {

        /*condition si ce numero est different de null on retourne vrai
        pour juste dire que ce numero existe déjà depuis la BD*/
        if (readOneByReference(numero) != null) {
            return true;
        }
        return false;
    }

    /**
     * lecture de la reference en verifiant qu'elle n'est pas nulle
     *
     * @param numeReference
     * @return
     */
    // lecture des numeros de reference dans la BD
    @Override
    public Retrait readOneByReference(String numeReference) {

        /*si le numero de reference est different de vide on retourne ce numero generé*/
        if (!"".equals(numeReference)) {
            return retraitDao.readOneByReference(numeReference);
        }
        return null;
    }

    /**
     * methode verifant juste le type de compte
     *
     * @param numCompte
     * @return
     */
    @Override
    public String verifierTypeCompte(String numCompte) {

        /* si le compte est different de null on renvoie CompteCourant sinon CompteEpargne */
        if (compteCourantService.ReadOneByNumeCompte(numCompte) != null) {
            return "CompteCourant";

        } else {
            return "CompteEpargne";
        }
    }

    /**
     * Methode verifiant juste le niveau de solde pour permetre la transaction
     *
     * @param numCompte
     * @param montant
     * @return
     */
    @Override
    public Boolean verifierSolde(String numCompte, Double montant) {

        /*si le compte est Courant on renvoie true en verifiant le niveau du solde sinon false*/
        if (verifierTypeCompte(numCompte).equals("CompteCourant")) {
            if (compteCourantService.verificationSolde(montant, numCompte)) {
                return true;
            } else {
                return false;
            }

        } else {
            /* cette condition permet de faire un virement de compteEpargne a compteEpargne a condition que la methode contrainteVirement puisse l'autoriser dans sa condition "else if" */
            if (compteEpargneService.verificationSolde(montant, numCompte)) {
                return true;
            } else {
                return false;
            }
        }
    }

}
