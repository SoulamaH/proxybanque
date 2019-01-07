/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.dao.imp;

import ci.objis.dao.ICompteCourantDao;
import ci.objis.domaine.CompteCourant;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.persistence.exceptions.EntityManagerSetupException;

/**
 *
 * @author USER
 */
@Stateless
@LocalBean
public class CompteCourantDao implements ICompteCourantDao {

    // Proprietes
    @PersistenceContext(unitName = "banquePU")// plus besoin de creer emf et d'ouvrir la transation
    private EntityManager em;

    public Logger logger;

    private Query query;

    //conStructeur
    public CompteCourantDao() {
        this.logger = LogManager.getLogger(CompteCourantDao.class.getName());
    }

    /**
     * Mes méthodes de CRUD ( ajouter, readOne, readAll, modifier et supprimer)
     * aussi le count pour la taille
     *
     * @param t
     * @return
     */
    // enregistrement dans la BD
    @Override
    public CompteCourant ajouter(CompteCourant t) {
        try {
            em.persist(t);
            this.logger.info("save with succes compteCourant");

        } catch (EntityManagerSetupException ex) {
            this.logger.fatal("Error saving compteCourant");
        }
        return t;
    }

    // Modification dans la BD
    @Override
    public CompteCourant modifier(CompteCourant t) {
        em.merge(t);
        return t;
    }

    // suppression
    @Override
    public boolean supprimer(CompteCourant t) {
        em.remove(t);
        return (em.find(CompteCourant.class, t.getNumeroCompte()) == null) ? true : false;
    }

    // retourn la liste de tous les enregistrement compteCourant
    @Override
    public List<CompteCourant> readAll() {
        query = em.createQuery("SELECT o FROM " + CompteCourant.class.getSimpleName() + " o");
        return query.getResultList();
    }

    // retourne un element de la liste par l'id
    @Override
    public CompteCourant readOne(String pk) {
        return em.find(CompteCourant.class, pk);
    }

    /**
     * lecture du numero de compte 
     * @param compte
     * @return 
     */
    // recherche par numero de compte 
    /*on desire retourner un element et non une liste*/
    @Override
    public CompteCourant ReadOneByNumeCompte(String compte) {

        //recupere l'objet compteEpargne
        query = em.createQuery("SELECT o FROM CompteCourant o WHERE o.numeroCompte=:c ");
        query.setParameter("c", compte);

        //liste des comptes courant 
        /* List<CompteCourant> listeCompte = query.getResultList();*/
        //recuperation de la liste
        List<CompteCourant> listeCompte = new LinkedList<CompteCourant>();
        listeCompte = query.getResultList();

        //condition pour retourner un element 
//            System.out.println("******* CompteCourantDao avant le IF ****");
//            System.out.println("liste des compte" + " "+listeCompte);
//        if (listeCompte != null) {
//            return (CompteCourant)listeCompte.get(0);
//        }


        //initialisation de l'objet compteEpargne
        CompteCourant compteCourant = null;

        //parcourt du comptecourant
        for (CompteCourant compteCourantD : listeCompte) {

            compteCourant = compteCourantD;

        }
        return compteCourant ;
    }

    /**
     * lecture du solode
     * @param compte
     * @return 
     */
    //renvoie le solde
    /*on desire retourner un element et non une liste*/
    @Override
    public Double readOneBySoldeCompte(String compte) {
        return ReadOneByNumeCompte(compte).getSolde();
    }

    // verifie le compte
    public Boolean verificationSolde(Double montant, String compte) {
               
        return true;
    }

}
