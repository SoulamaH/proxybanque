/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.dao.imp;

import ci.objis.dao.IClientDao;
import ci.objis.domaine.Client;
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
public class ClientDao implements IClientDao {

    // proprietes
    @PersistenceContext(unitName = "banquePU") // plus besoin de creer emf et d'ouvrir la transation
    private EntityManager em;

    private Query query;

    public Logger logger;

    //constructeur
    public ClientDao() {
        this.logger = LogManager.getLogger(ClientDao.class.getName());
    }

    /**
     * Mes méthodes de CRUD ( ajouter, readOne, readAll, modifier et supprimer)
     * aussi le count pour la taille
     *
     * @param t
     * @return
     */
    // Enregistrement dans la BD
    @Override
    public Client ajouter(final Client t) {
        try {
            em.persist(t);
            this.logger.info("save with succes");

        } catch (EntityManagerSetupException ex) {
            this.logger.fatal("Error saving client");
        }
        return t;
    }

    // Modifier dans la BD
    @Override
    public Client modifier(final Client t) {
        em.merge(t);
        return t;
    }

    // suppression 
    @Override
    public boolean supprimer(final Client t) {
        em.remove(t);
        return (em.find(Client.class, t.getId()) == null) ? true : false; // si l'element est supprimé la methode renvoie "true" sinon "false" 

        /*meme code que celui du haut "return" */
//        if (t== null) {
//            return true;
//        }
//        return false;
    }

    // la liste de tous les Client dans la BD
    @Override
    public List<Client> readAll() {
        query = em.createQuery("SELECT o FROM Client "+Client.class.getSimpleName() + " o");
        return query.getResultList();
    }

    // la liste d'un client par l'ID
    @Override
    public Client readOne(final Long pk) {
        return em.find(Client.class, pk);
    }

}
