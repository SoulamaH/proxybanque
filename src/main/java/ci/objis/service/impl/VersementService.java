/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.service.impl;

import ci.objis.dao.imp.VersementDao;
import ci.objis.domaine.CompteCourant;
import ci.objis.domaine.CompteEpargne;
import ci.objis.domaine.Versement;
import ci.objis.services.IVersementService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * VersementService implements IVersementService
 *
 * @author USER
 */
@LocalBean
@Stateless
public class VersementService implements IVersementService {

    //objets geré par les EJB
    @EJB
    VersementDao versementDao;

    @EJB
    NumeroCompteService numeroCompteService;

    @EJB
    private CompteCourantService compteCourantService;

    @EJB
    private CompteEpargneService compteEpargneService;

    /**
     * liste versement par client
     *
     * @param idClient
     * @return
     */
    @Override
    public List<Versement> readAllVersementClient(Long idClient) {
        return versementDao.readAll();
    }

    /**
     * Mes méthodes de CRUD ( ajouter, readOne, readAll, modifier et supprimer)
     * aussi le count pour la taille
     *
     * @param t
     * @return
     */
    @Override
    public Versement ajouter(Versement t) {
        return versementDao.ajouter(t);
    }

    @Override
    public Versement modifier(Versement t) {
        return versementDao.modifier(t);
    }

    @Override
    public boolean supprimer(Versement t) {
        return versementDao.supprimer(t);
    }

    @Override
    public List<Versement> readAll() {
        return versementDao.readAll();
    }

    @Override
    public Versement readOne(Long pk) {
        return versementDao.readOne(pk);
    }

    @Override
    public Integer count() {
        return versementDao.readAll().size();
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
    public Versement verser(Versement versement, String numeCompte) {

        // objets
        CompteCourant compteCourant = null;
        CompteEpargne compteEpargne = null;

        if (verifierTypeCompte(numeCompte).equals("CompteCourant")) {

            /*on recupère le numero de compte auquel on ajoute le montant (solde initial + montant entré)*/
            compteCourant = compteCourantService.readOne(numeCompte);
            compteCourant.setSolde(compteCourant.getSolde() + versement.getMontant());
            versement.setCompte(compteCourant);

        } else {
            /*on recupère le numero de compte auquel on ajoute le montant (solde initial + montant entré)*/
            compteEpargne = compteEpargneService.readOne(numeCompte);
            compteEpargne.setSolde(compteEpargne.getSolde() + versement.getMontant());
            versement.setCompte(compteEpargne);
        }
        return versementDao.ajouter(versement);
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

        //Récupertion des numeros de compte 
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
     * genère de nouveau numero de reference tant que celui ci existe dans la BD
     *
     * @return reference
     */
    //genere le numero de reference
    @Override
    public String genererReference() {

        //declaration d'une variable plus affectation du numero generé
        String refernce = numeroCompteService.generateNumeroCompte();

        /* condition tanque le numero existe dans la BD on continue de generer
            les numeros jusqu'a ce qu'il n'y est pas de doublon*/
        while (verifNumeroRefenrence(refernce)) {
            refernce = numeroCompteService.generateNumeroCompte();
        }
        return refernce;
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
    public Versement readOneByReference(String numeReference) {

        /*si le numero de reference est different de vide on retourne ce numero generé*/
        if (!"".equals(numeReference)) {
            return versementDao.readOneByReference(numeReference);
        }
        return null;
    }

}
