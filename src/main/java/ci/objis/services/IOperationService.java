/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.services;

import ci.objis.domaine.Operation;
import javax.ejb.Local;

/**
 * IOperationService extends IService (l'interface générique)
 * @author Djabi
 */
@Local
public interface IOperationService extends IService<Operation, Long>{
    
}
