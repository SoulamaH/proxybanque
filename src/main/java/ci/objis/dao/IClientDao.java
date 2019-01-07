/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.dao;

import ci.objis.domaine.Client;
import javax.ejb.Local;

/**
 * IClientDao extends IDao
 *
 * @author USER
 */
@Local
public interface IClientDao extends IDao<Client, Long> {

}
