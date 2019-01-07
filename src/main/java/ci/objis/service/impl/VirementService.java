/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.service.impl;

import ci.objis.dao.imp.VirementDao;
import ci.objis.domaine.CompteCourant;
import ci.objis.domaine.CompteEpargne;
import ci.objis.domaine.Virement;
import ci.objis.services.IVirementService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * VirementService implements IVirementService
 *
 * @author USER
 */
@LocalBean
@Stateless
public class VirementService implements IVirementService {

    //objets geré par les EJB
    @EJB
    private VirementDao virementDao;

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
    // enregistrement
    @Override
    public Virement ajouter(Virement t) {
        return virementDao.ajouter(t);
    }

    // modifier
    @Override
    public Virement modifier(Virement t) {
        return virementDao.modifier(t);
    }

    //suppression
    @Override
    public boolean supprimer(Virement t) {
        return virementDao.supprimer(t);
    }

    // lecture d'un element
    @Override
    public Virement readOne(Long pk) {
        return virementDao.readOne(pk);
    }

    // lecture de tous les elements (une liste)
    @Override
    public List readAll() {
        return virementDao.readAll();
    }

    // connaitre total de nos elements
    @Override
    public Integer count() {
        return virementDao.readAll().size();
    }

    /**
     * cette methode permet de faire un retrait d'un compte apres avoir vérifie
     * le type de compte
     *
     * @param virement
     * @return
     */
    @Override
    public Virement retirer(Virement virement) {

        // declaration des objets
        CompteCourant compteCourant;
        CompteEpargne compteEpargne;

        // verifie le type de compte
        if (verifierTypeCompte(virement.getCompte().getNumeroCompte()).equals("CompteCourant")) {

            /*recupertion du compte par le virment*/
            compteCourant = (CompteCourant) virement.getCompte();
            
            System.out.println(" ");
            System.out.println(" virementservice retirer compte courant" + compteCourant);
            System.out.println(" ");
            /*à ce compte on modifie le solde*/
            compteCourant.setSolde(compteCourant.getSolde() - virement.getMontant());
            
            compteCourantService.modifier(compteCourant);
            
            System.out.println(" ");
            System.out.println(" virementservice retirer modifier" + compteCourantService.modifier(compteCourant));
            System.out.println(" ");
        } else {

            /*recupertion du compte par le virment*/
            compteEpargne = (CompteEpargne) virement.getCompte();
            
            System.out.println(" ");
            System.out.println(" virementservice retirer compte epargne else" + compteEpargne);
            System.out.println(" ");

            /*à ce compte on modifie le solde*/
            compteEpargne.setSolde(compteEpargne.getSolde() - virement.getMontant());
            compteEpargneService.modifier(compteEpargne);
            
            System.out.println(" ");
            System.out.println(" virementservice retirer modifier else" + compteEpargneService.modifier(compteEpargne));
            System.out.println(" ");
        }
            System.out.println(" ");
            System.out.println(" virementservice retirer return" + virementDao.retirer(virement));
            System.out.println(" ");
        return virementDao.retirer(virement);
    }

    /**
     * Cette methode permet de faire un versement a pres avoir vérifié le type
     * compte
     *
     * @param virement
     * @return
     */
    @Override
    public Virement verser(Virement virement) {

        CompteCourant compteCourant;
        CompteEpargne compteEpargne;

        // verifie le type de compte
        if (verifierTypeCompte(virement.getCompte().getNumeroCompte()).equals("CompteCourant")) {

            /*recupertion du compte par le virment*/
            compteCourant = (CompteCourant) virement.getCompte();

            /*à ce compte on modifie le solde*/
            compteCourant.setSolde(compteCourant.getSolde() + virement.getMontant());
            compteCourantService.modifier(compteCourant);
        } else {

            /*recupertion du compte par le virment*/
            compteEpargne = (CompteEpargne) virement.getCompte();

            /*à ce compte on modifie le solde*/
            compteEpargne.setSolde(compteEpargne.getSolde() + virement.getMontant());
            compteEpargneService.modifier(compteEpargne);
        }
        return virementDao.verser(virement);
    }

    /**
     * Cette methode permet d'effectuer le versement apres avoir recupéré la
     * reference de la transaction entre le retrait et le versement
     *
     * @param virement1
     * @param virement2
     * @return
     */
    // effectuerVersement
    @Override
    public Boolean effectuerVersement(Virement virement1, Virement virement2) {

        // declaration et affectation
        String reference = genererReference();

        virement1.setReference(reference);

        virement2.setReference(reference);

        // condition sur l'etat de la reference 
        if (retirer(virement1) != null) {
            if (verser(virement2) != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Virement> listeVirementVerser() {
        return virementDao.listeVirementVerser();
    }

    @Override
    public List<Virement> listeVirementRetirer() {
        return virementDao.listeVirementRetirer();
    }

    @Override
    public List<Virement> listeVirementVerserParCompteCourant(String numeroCompte) {
        return virementDao.listeVirementVerserParCompteCourant(numeroCompte);
    }

    @Override
    public List<Virement> listeVirementVerserParCompteEpargne(String numeroCompte) {
        return virementDao.listeVirementVerserParCompteEpargne(numeroCompte);
    }

    @Override
    public List<Virement> listeVirementRetirerParCompteCourant(String numeroCompte) {
        return virementDao.listeVirementRetirerParCompteCourant(numeroCompte);
    }

    @Override
    public List<Virement> listeVirementRetirerParCompteEpargne(String numeroCompte) {
        return virementDao.listeVirementRetirerParCompteEpargne(numeroCompte);
    }

    @Override
    public List<Virement> listeVirementVerserParClient(Long idClient) {
        return virementDao.listeVirementVerserParClient(idClient);
    }

    @Override
    public List<Virement> listeVirementRetirerParClient(Long idClient) {
        return virementDao.listeVirementRetirerParClient(idClient);
    }

    @Override
    public List<Virement> VirementParReference(String reference) {
        return virementDao.VirementParReference(reference);
    }

    @Override
    public Virement VirementVerserParReference(String reference) {
        return virementDao.VirementVerserParReference(reference);
    }

    @Override
    public Virement VirementRetirerParReference(String reference) {
        return virementDao.VirementRetirerParReference(reference);
    }

    /**
     * genere de nouveau numero de reference tant que celui ci existe dans la BD
     *
     * @return refrence
     */
    // genere le numero de de reference 
    @Override
    public String genererReference() {
        String reference = numeroCompteService.generateNumeroCompte();

        while (verifNumeroRefenrence(reference)) {
            reference = numeroCompteService.generateNumeroCompte();
        }
        return reference;
    }

    /**
     * verifie si le numero de reference existe deja dans la BD
     *
     * @param numero
     * @return false
     */
    // verifie le numero de reference depuis la BD
    @Override
    public Boolean verifNumeroRefenrence(String numero) {

        // numero de refernce different de null on retourne vrai pour son existance
        if (readOneByReference(numero) != null) {
            return true;
        }
        return false;
    }

    /**
     * Méthode verifant l'existance du compte grace a son numero de compte
     *
     * @param compte
     * @return false
     */
    // verification 
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
     * Cette methode est une contrainte de virement entre en certain type de
     * compte
     *
     * @param compteDebiteur
     * @param compteCrediteur
     * @return true
     */
    @Override
    public Boolean contrainteVirement(String compteDebiteur, String compteCrediteur) {

        /* Empeche de faire des virementent entre "compteEpargne et CompteCourant puis entre et CompteEpargne et CompteEpargne"*/
        if (verifierTypeCompte(compteDebiteur).equals("CompteEpargne") && verifierTypeCompte(compteCrediteur).equals("CompteCourant")) {
            return false;
        } else if (verifierTypeCompte(compteDebiteur).equals("CompteEpargne") && verifierTypeCompte(compteCrediteur).equals("CompteEpargne")) {
            return false;
        }
        return true;
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
                System.out.println(" ");
                System.out.println("verifiersolde virementservice" + compteEpargneService.verificationSolde(montant, numCompte));
                System.out.println(" ");
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * lecture de la reference en verifiant qu'elle n'est pas nulle
     *
     * @param numeReference
     * @return
     */
    @Override
    public Virement readOneByReference(String numeReference) {

        if (!"".equals(numeReference)) {
            return virementDao.readOneByReference(numeReference);
        }
        return null;
    }

}
