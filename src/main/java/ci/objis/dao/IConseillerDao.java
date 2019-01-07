/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.dao;

import ci.objis.domaine.Conseiller;
import javax.ejb.Local;

/**
 * IConseillerDao extends IDao
 *
 * @author USER
 */
@Local
public interface IConseillerDao extends IDao<Conseiller, Long> {

    // methode retournant le conseiller par userName
    public Conseiller readOneByUserName(String userName);
}
