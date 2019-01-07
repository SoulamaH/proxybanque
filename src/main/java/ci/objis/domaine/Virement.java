/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.domaine;

import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe heritant des propriétées de la classe Operation
 * @author USER
 */
@Data
@NoArgsConstructor
@Entity(name = "Virement")
@Table(name = "VIREMENT")
@DiscriminatorValue("I")
public class Virement extends Operation {

    // Proprietes
    private String libelle;
    private String typeVirement;
    private String reference;

    // Constructeur sans "id"
    public Virement(String libelle, String typeVirement, String reference, Date dateOperation, Double montant, Compte compte) {
        super(dateOperation, montant, compte);
        this.libelle = libelle;
        this.typeVirement = typeVirement;
        this.reference = reference;
    }

}
