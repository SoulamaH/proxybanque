/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.dao.imp;

import ci.objis.dao.ICompteEpargneDao;
import ci.objis.domaine.CompteEpargne;
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
public class CompteEpargneDao implements ICompteEpargneDao {

    // Proprietes
    @PersistenceContext(unitName = "banquePU")// plus besoin de creer emf et d'ouvrir la transation
    private EntityManager em;

    public Logger logger;

    private Query query;

    // Constructeur
    public CompteEpargneDao() {
        this.logger = LogManager.getLogger(CompteEpargneDao.class.getName());
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
    public CompteEpargne ajouter(CompteEpargne t) {
        try {
            em.persist(t);
            this.logger.info("save with succes compteEepargne");

        } catch (EntityManagerSetupException ex) {
            this.logger.fatal("Error saving compteEpargne");
        }
        return t;
    }

    // Modification dans la BD
    @Override
    public CompteEpargne modifier(CompteEpargne t) {
        em.merge(t);
        return t;
    }

    //suppression
    @Override
    public boolean supprimer(CompteEpargne t) {
        return (em.find(CompteEpargne.class, t.getNumeroCompte()) == null) ? true : false;
    }

    // retourne la liste de tous les elements dans la BD
    @Override
    public List<CompteEpargne> readAll() {
        query = em.createQuery("SELECT o FROM " + CompteEpargne.class.getSimpleName() + " o");
        return query.getResultList();
    }

    // rtourne un element dans la liste par l'id
    @Override
    public CompteEpargne readOne(String pk) {
        return em.find(CompteEpargne.class, pk);
    }

    /**
     * lecture d'une element du compte
     *
     * @param compte
     * @return
     */
    // recherche par numero de compte 
    @Override
    public CompteEpargne ReadOneByNumeCompte(String compte) {

        //recupere l'objet compteEpargne
        query = em.createQuery("SELECT o FROM CompteEpargne o WHERE o.numeroCompte=:c ");
        query.setParameter("c", compte);

        // recuperation de la liste
        List<CompteEpargne> listeEpargne = new LinkedList<CompteEpargne>();
        listeEpargne = query.getResultList();
       
        // condition sur la liste pour l'affichage d'un element 
        CompteEpargne compteEpargne = null;

        //parcourt du comptecourant
        for (CompteEpargne compteEpargneD : listeEpargne) {

            compteEpargne = compteEpargneD;
            
        }
        return compteEpargne;
    }

    /**
     * methode permetant retourne le solde
     *
     * @param compte
     * @return
     */
    //methode pour retourner le solde
    /*on desire retourner un element et non une liste*/
    @Override
    public CompteEpargne readOneBySoldeCompte(String compte) {

        // recupere l'objet
        Query query = em.createQuery("SELECT o FROM CompteEpargne o WHERE o.solde=:s ");
        query.setParameter("s", compte);

        // recupere la liste compte
        List<CompteEpargne> listEpargneSolde = query.getResultList();

        CompteEpargne compteEpargne = null;

        // parcours de la lsite 
        for (CompteEpargne compteEpargne1 : listEpargneSolde) {

            // condition 
            if (compteEpargne1 != null) {
                compteEpargne = compteEpargne1;
            }
        }
        return compteEpargne;
    }

}
