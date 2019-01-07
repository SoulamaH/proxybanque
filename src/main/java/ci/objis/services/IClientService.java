/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.services;

import ci.objis.domaine.Client;
import javax.ejb.Local;

/**
 * IClientService extends IService (l'interface générique)
 * @author Djabi
 */
@Local
public interface IClientService extends IService<Client, Long> {


}
