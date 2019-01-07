/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.services;

import ci.objis.domaine.Conseiller;
import javax.ejb.Local;

/**
 * IConseillerService extends IService (l'interface générique)
 * mais elle posséde aussi ses methodes (cryptagePassword, readOneByUserName)
 * qui permettent de crypter le password et de retourner un conseiller
 * 
 * @author USER
 */
@Local
public interface IConseillerService extends IService<Conseiller, Long> {
/**
 * 
 * @param password
 * @return 
 */
    // methode de cryptage de password
    public String cryptagePassword(String password);
    
    // methode retournant le conseiller par userName
    public Conseiller readOneByUserName(String userName);
}
