/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.service.impl;

import ci.objis.dao.imp.ConseillerDao;
import ci.objis.domaine.Conseiller;
import ci.objis.services.IConseillerService;
import com.google.common.hash.Hashing;
import java.nio.charset.Charset;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * ConseillerService implements IConseillerService
 * @author USER
 */
@LocalBean
@Stateless
public class ConseillerService implements IConseillerService {

    // objet geré par EJB
    @EJB
    private ConseillerDao conseillerDao;

    /**
     * Mes méthodes de CRUD ( ajouter, readOne, readAll, modifier et supprimer)
     * aussi le count pour la taille
     * @param t
     * @return 
     */
    @Override
    public Conseiller ajouter(Conseiller t) {
        return conseillerDao.ajouter(t);
    }

    @Override
    public Conseiller modifier(Conseiller t) {
        return conseillerDao.modifier(t);
    }

    @Override
    public boolean supprimer(Conseiller t) {
        return conseillerDao.supprimer(t);
    }

    @Override
    public List<Conseiller> readAll() {
        return conseillerDao.readAll();
    }

    @Override
    public Conseiller readOne(Long pk) {
        return conseillerDao.readOne(pk);
    }

    // retourne le nombre
    @Override
    public Integer count() {
        return conseillerDao.readAll().size();
    }

    /**
     * Methode permettant de crypter le password
     * @param password
     * @return codeCrypter
     */
    // methode de cryptage
    @Override
    public String cryptagePassword(String password) {

        // methode permettant de cryptage le password
        String codeCrypter = Hashing.sha256().hashString(password, Charset.defaultCharset()).toString();
        
        return codeCrypter;
    }

    /**
     * lecture par le nom de L'user
     * @param userName
     * @return 
     */
    @Override
    public Conseiller readOneByUserName(String userName) {
        return conseillerDao.readOneByUserName(userName);
    }

}