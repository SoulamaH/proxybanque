/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.service.impl;

import ci.objis.dao.imp.ClientDao;
import ci.objis.domaine.Client;
import ci.objis.services.IClientService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Djabi
 */
@Stateless
@LocalBean
public class ClientService implements IClientService {

    //objet
    @EJB
    private ClientDao clientDao;

    /**
     * Mes méthodes de CRUD ( ajouter, readOne, readAll, modifier et supprimer)
     * aussi le count pour la taille
     *
     * @param t
     * @return
     */
    //enregistrement grace à l'objet Dao
    @Override
    public Client ajouter(Client t) {
        return clientDao.ajouter(t);
    }

    //modife
    @Override
    public Client modifier(Client t) {
        return clientDao.modifier(t);
    }

    // supprssion
    @Override
    public boolean supprimer(Client t) {
        return clientDao.supprimer(t);
    }

    // liste de tous les elements
    @Override
    public List<Client> readAll() {
        return clientDao.readAll();
    }

    // lecture d'un element
    @Override
    public Client readOne(Long pk) {
        return clientDao.readOne(pk);
    }

    // nombre d'element dans la BD
    @Override
    public Integer count() {
        return clientDao.readAll().size();
    }

}
