/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.dao.imp;

import ci.objis.dao.IRetraitDao;
import ci.objis.domaine.Retrait;
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
 * RetraitDao implements IRetraitDao
 *
 * @author USER
 */
@Stateless
@LocalBean
public class RetraitDao implements IRetraitDao {

    // Propriétés
    @PersistenceContext(unitName = "banquePU")// plus besoin de creer emf et d'ouvrir la transation
    EntityManager em;

    public Logger logger;

    private Query query;

    // constructeur
    public RetraitDao() {
        this.logger = LogManager.getLogger(RetraitDao.class.getName());
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
    public Retrait ajouter(Retrait t) {
        try {
            em.persist(t);
            this.logger.info("save with succes retrait");

        } catch (EntityManagerSetupException ex) {
            this.logger.fatal("Error saving retrait");
        }
        return t;
    }

    @Override
    public Retrait modifier(Retrait t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supprimer(Retrait t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Retrait> readAll() {
        return em.createQuery("SELECT o FROM Retrait o").getResultList();
    }

    //lecture d'un element dans la liste da la BD
    @Override
    public Retrait readOne(Long pk) {
        return em.find(Retrait.class, pk);
    }

    /**
     * lecture de la reference et retourne un seul element
     *
     * @param numeReference
     * @return
     */
    @Override
    public Retrait readOneByReference(String numeReference) {

        //JPQL pour recuperer ma reference 
        Query query = em.createQuery("SELECT o FROM Retrait o WHERE o.reference=:r");
        query.setParameter("r", numeReference);

        // affectation de la liste retrait a un objet de type liste retrait
        List<Retrait> retraits = query.getResultList();

        // declaration et Initialisation de l'objet retrait
        Retrait retrait = null;

        // condition pour retourner un seul element car tel est le but
        if (!retraits.isEmpty()) {
            retrait = retraits.get(0);
        }
        return retrait;
    }
}
