/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.dao.imp;

import ci.objis.dao.IVirementDao;
import ci.objis.domaine.Virement;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author USER
 */
@LocalBean
@Stateless
public class VirementDao implements IVirementDao {

    /**
     *
     */
    // proprietes
    @PersistenceContext(unitName = "banquePU")// plus besoin de creer emf et d'ouvrir la transation
    EntityManager em;

    /**
     * Mes méthodes de CRUD ( ajouter, readOne, readAll, modifier et supprimer)
     * aussi le count pour la taille
     *
     * @param t
     * @return
     */
    @Override
    public Virement ajouter(Virement t) {
        em.persist(t);
        return t;
    }

    @Override
    public Virement modifier(Virement t) {
        em.merge(t);
        return t;
    }

    @Override
    public boolean supprimer(Virement t) {
        em.remove(t);
        if (t != null) {
            return false;
        }
        return true;
    }

    /**
     * retourne la lste virement en passant pr le JPQL
     *
     * @return
     */
    @Override
    public List<Virement> readAll() {
        return em.createQuery("SELECT o FROM Virement o").getResultList();
    }

    @Override
    public Virement readOne(Long pk) {
        return em.find(Virement.class, pk);
    }

    /**
     * permet de faire un retrait dans la BD
     *
     * @param virement
     * @return
     */
    //methode de retrait
    @Override
    public Virement retirer(Virement virement) {
        virement.setTypeVirement("retirer");
        return ajouter(virement);
    }

    /**
     * permet d'enregistrer un versement
     *
     * @param virement
     * @return
     */
    // methode de versement
    @Override
    public Virement verser(Virement virement) {
        virement.setTypeVirement("verser");
        return ajouter(virement);
    }

    /**
     * permet d'effectuer un versement
     *
     * @param virement1
     * @param virement2
     * @return
     */
    //methode effectuerVersement
    @Override
    /*oke*/
    public Boolean effectuerVersement(Virement virement1, Virement virement2) {

        //condition
        /*dans ces deux conditions on retire le montnat du compte a debiter dans la condition 1,
            puis on le verse sur le compte a crediter dans la condition du bas*/
        if (retirer(virement1) != null) {
            if (verser(virement2) != null) {
                return true;
            }
        }
        return false;
    }

    @Override/*oke*/
    public List<Virement> listeVirementVerser() {
        return em.createQuery("SELECT o FROM Virement o WHERE o.typeVirement='verser'").getResultList();

    }

    @Override
    public List<Virement> listeVirementRetirer() {
        return em.createQuery("SELECT o FROM Virement o WHERE o.typeVirement='retirer'").getResultList();
    }

    @Override/*oke*/
    public List<Virement> listeVirementVerserParCompteCourant(String numeroCompte) {

        Query query = em.createNativeQuery("SELECT virement.ID FROM virement,comptecourant Where comptecourant.ID=virement.CODE_CPTE AND comptecourant.ID=? AND virement.TYPEVIREMENT='verser'");

        List<Long> maListe = query.setParameter(1, numeroCompte).getResultList();

        List<Virement> listeVirement = new LinkedList<>();

        //
        for (Long long1 : maListe) {
            listeVirement.add(readOne(long1));
        }
        return listeVirement;
    }

    @Override/*oke*/
    public List<Virement> listeVirementVerserParCompteEpargne(String numeroCompte) {
        Query query = em.createNativeQuery("SELECT virement.ID FROM virement,compteepargne Where compteepargne.ID=virement.CODE_CPTE AND compteepargne.ID=? AND virement.TYPEVIREMENT='verser'");

        List<Long> maListe = query.setParameter(1, numeroCompte).getResultList();

        List<Virement> listeVirement = new LinkedList<>();

        //
        for (Long long1 : maListe) {
            listeVirement.add(readOne(long1));
        }
        return listeVirement;
    }

    @Override/*oke*/
    public List<Virement> listeVirementRetirerParCompteCourant(String numeroCompte) {

        Query query = em.createNativeQuery("SELECT virement.ID FROM virement,comptecourant Where comptecourant.ID=virement.CODE_CPTE AND comptecourant.ID=? AND virement.TYPEVIREMENT='retirer'");

        List<Long> maListe = query.setParameter(1, numeroCompte).getResultList();

        List<Virement> listeVirement = new LinkedList<>();

        //
        for (Long long1 : maListe) {
            listeVirement.add(readOne(long1));
        }
        return listeVirement;
    }

    @Override/*oke*/
    public List<Virement> listeVirementRetirerParCompteEpargne(String numeroCompte) {
        Query query = em.createNativeQuery("SELECT virement.ID FROM virement,compteepargne Where compteepargne.ID=virement.CODE_CPTE AND compteepargne.ID=? AND virement.TYPEVIREMENT='retirer'");

        List<Long> maListe = query.setParameter(1, numeroCompte).getResultList();

        List<Virement> listeVirement = new LinkedList<>();

        //
        for (Long long1 : maListe) {
            listeVirement.add(readOne(long1));
        }
        return listeVirement;
    }

    @Override
    /*oke il a deux methodes en son sein*/
    public List<Virement> listeVirementVerserParClient(Long idClient) {

        List<Virement> listeVirement = new LinkedList<>();

        Query query = em.createNativeQuery("SELECT virement.ID FROM virement,comptecourant,client Where client.ID=comptecourant.CODE_CLI AND comptecourant.ID=virement.CODE_CPTE AND client.ID=? AND virement.TYPEVIREMENT='verser'");

        List<Long> maListe = query.setParameter(1, idClient).getResultList();

        //
        for (Long long1 : maListe) {
            listeVirement.add(readOne(long1));
        }

        Query query2 = em.createNativeQuery("SELECT virement.ID FROM virement,compteepargne,client Where client.ID=compteepargne.CODE_CLI AND compteepargne.ID=virement.CODE_CPTE AND client.ID=? AND virement.TYPEVIREMENT='verser'");

        List<Long> maListe2 = query2.setParameter(1, idClient).getResultList();
        for (Long long2 : maListe2) {
            listeVirement.add(readOne(long2));
        }
        return listeVirement;

    }

    @Override/*oke*/
    public List<Virement> listeVirementRetirerParClient(Long idClient) {

        List<Virement> listeVirement = new LinkedList<>();

        Query query = em.createNativeQuery("SELECT virement.ID FROM virement,comptecourant,client Where client.ID=comptecourant.CODE_CLI AND comptecourant.ID=virement.CODE_CPTE AND client.ID=? AND virement.TYPEVIREMENT='retirer'");

        List<Long> maListe = query.setParameter(1, idClient).getResultList();

        //
        for (Long long1 : maListe) {
            listeVirement.add(readOne(long1));
        }

        Query query2 = em.createNativeQuery("SELECT virement.ID FROM virement,compteepargne,client Where client.ID=compteepargne.CODE_CLI AND compteepargne.ID=virement.CODE_CPTE AND client.ID=? AND virement.TYPEVIREMENT='retirer'");

        List<Long> maListe2 = query2.setParameter(1, idClient).getResultList();
        for (Long long2 : maListe2) {
            listeVirement.add(readOne(long2));
        }
        return listeVirement;
    }

    @Override
    public List<Virement> VirementParReference(String reference) {

        Query query = em.createQuery("SELECT o FROM Virement o WHERE o.reference=:'r'");
        query.setParameter("r", reference);

        return query.getResultList();
    }

    @Override/*oke*/
    public Virement VirementVerserParReference(String reference) {
        Query query = em.createQuery("SELECT o FROM Virement o WHERE o.reference=:'r' AND o.typeVirement='verser'");

        query.setParameter("r", reference);

        List<Virement> virements = query.getResultList();

        Virement virement = null;

        for (Virement virement1 : virements) {
            if (virement1 != null) {
                virement = virement1;
            }
        }
        return virement;
    }

    @Override/*oke*/
    public Virement VirementRetirerParReference(String reference) {

        Query query = em.createQuery("SELECT o FROM Virement o WHERE o.reference=:'r' AND o.typeVirement='retirer'");

        query.setParameter("r", reference);

        List<Virement> virements = query.getResultList();

        Virement virement = null;

        for (Virement virement1 : virements) {
            if (virement1 != null) {
                virement = virement1;
            }
        }
        return virement;
    }

    /**
     * lecture de la reference retourne un element
     * @param numeReference
     * @return 
     */
    @Override
    public Virement readOneByReference(String numeReference) {

        Query query = em.createQuery("SELECT o FROM Virement o WHERE o.reference=:r");
        query.setParameter("r", numeReference);

        List<Virement> virements = query.getResultList();

        Virement virement = null;

        if (!virements.isEmpty()) {
            virement = virements.get(0);
        }
        return virement;
    }
    
    
}
