/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.dao.imp;

import ci.objis.dao.IConseillerDao;
import ci.objis.domaine.Conseiller;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author USER
 */
@LocalBean
@Stateless
public class ConseillerDao implements IConseillerDao {

    // proprietes
    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    // private Logger logger;
    private Query query;

    /**
     * Mes méthodes de CRUD ( ajouter, readOne, readAll, modifier et supprimer)
     * aussi le count pour la taille
     *
     * @param t
     * @return
     */
    @Override
    public Conseiller ajouter(Conseiller t) {
        em.persist(t);
        return t;
    }

    @Override
    public Conseiller modifier(Conseiller t) {
        em.merge(t);
        return t;
    }

    @Override
    public boolean supprimer(Conseiller t) {
        em.remove(t);
        if (t != null) {
            return false;
        }
        return true;
    }

    @Override
    public List<Conseiller> readAll() {
        return em.createQuery("SELECT o FROM Conseiller o").getResultList();
    }

    @Override
    public Conseiller readOne(Long pk) {
        return em.find(Conseiller.class, pk);
    }

    /**
     * lecture par le nom de L'user
     * @param userName
     * @return conseiller
     */
    @Override
    public Conseiller readOneByUserName(String userName) {
        query = em.createQuery("SELECT o FROM Conseiller o WHERE o.userName=:u");
        query.setParameter("u", userName);

        // liste conseillers
        List<Conseiller> conseillers = query.getResultList();

        Conseiller conseiller = null;

        // recupere la liste pour un parcours
        for (Conseiller conseiller1 : conseillers) {
            
            // condition si conseiller different de null on a affection conseiller1 à conseiller
            if (conseiller1 != null) {
                conseiller = conseiller1;
            }
        }
        return conseiller;
    }

}
