/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.dao;

import ci.objis.domaine.Versement;
import javax.ejb.Local;

/**
 * IVersementDao extends IDa
 *
 * @author USER
 */
@Local
public interface IVersementDao extends IDao<Versement, Long> {

    // Méthodes
    public Versement readOneByReference(String numeReference);

}
