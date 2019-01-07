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
 * @author Djabi
 */
@Data
@NoArgsConstructor
@Entity(name = "Versement")
@Table(name = "VERSEMENT")
@DiscriminatorValue("V")
public class Versement extends Operation {

    // Proprietes
    private String libelle;
    private String reference;

    // Constructeur sans "id"
    public Versement(String libelle, String reference, Date dateOperation, Double montant, Compte compte) {
        super(dateOperation, montant, compte);
        this.libelle = libelle;
        this.reference = reference;
    }

}
