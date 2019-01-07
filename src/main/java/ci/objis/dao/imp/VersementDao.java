/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.dao.imp;

import ci.objis.dao.IVersementDao;
import ci.objis.domaine.Versement;
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
 * VersementDao implements IVersementDao
 *
 * @author USER
 */
@Stateless
@LocalBean
public class VersementDao implements IVersementDao {

    // proprietes
    @PersistenceContext(unitName = "banquePU")// plus besoin de creer emf et d'ouvrir la transation
    EntityManager em;

    public Logger logger;

    private Query query;

    // constructeur
    public VersementDao() {
        this.logger = LogManager.getLogger(VersementDao.class.getName());
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
    public Versement ajouter(Versement t) {
        try {
            em.persist(t);
            this.logger.info("save with succes versement");

        } catch (EntityManagerSetupException ex) {
            this.logger.fatal("Error saving versement");
        }
        return t;
    }

    // lecture d'un element dans la liste de la BD
    @Override
    public Versement readOne(Long pk) {
        return em.find(Versement.class, pk);
    }

    /**
     * lecture de la referenceet retourne un seul element
     *
     * @param numeReference
     * @return
     */
    @Override
    public Versement readOneByReference(String numeReference) {

        Query query = em.createQuery("SELECT o FROM Versement o WHERE o.reference=:r");
        query.setParameter("r", numeReference);

        List<Versement> versements = query.getResultList();

        Versement versement = null;

        if (!versements.isEmpty()) {
            versement = versements.get(0);
        }
        return versement;

    }

    // Modification
    @Override
    public Versement modifier(Versement t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //suppression
    @Override
    public boolean supprimer(Versement t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //retourne liste total dans la BD
    @Override
    public List<Versement> readAll() {
        query = em.createQuery("SELECT o FROM " + Versement.class.getSimpleName() + " o");
        return query.getResultList();
    }

}
