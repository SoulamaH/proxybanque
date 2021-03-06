/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.dao;

import ci.objis.domaine.Virement;
import java.util.List;
import javax.ejb.Local;

/**
 * IVirementDao extends IDao
 *
 * @author USER
 */
@Local
public interface IVirementDao extends IDao<Virement, Long> {

    //methodes
    public Virement retirer(Virement virement);

    public Virement verser(Virement virement);

    public Boolean effectuerVersement(Virement virement1, Virement virement2);

    public List<Virement> listeVirementVerser();

    public List<Virement> listeVirementRetirer();

    public List<Virement> listeVirementVerserParCompteCourant(String numeroCompte);

    public List<Virement> listeVirementVerserParCompteEpargne(String numeroCompte);

    public List<Virement> listeVirementRetirerParCompteCourant(String numeroCompte);

    public List<Virement> listeVirementRetirerParCompteEpargne(String numeroCompte);

    public List<Virement> listeVirementVerserParClient(Long idClient);

    public List<Virement> listeVirementRetirerParClient(Long idClient);

    public List<Virement> VirementParReference(String reference);

    public Virement VirementVerserParReference(String reference);

    public Virement VirementRetirerParReference(String reference);

    public Virement readOneByReference(String numeReference);
}
