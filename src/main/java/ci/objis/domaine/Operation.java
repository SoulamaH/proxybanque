/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.domaine;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe mère des Classes Retrait, Versement et Virement
 * @author Djabi
 */
@Data
@NoArgsConstructor
@Entity(name = "Operation")
@Table(name = "OPERATION")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "TYPE_OP", discriminatorType = DiscriminatorType.STRING, length = 1)
public abstract class Operation implements Serializable {

    //Proprietes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date dateOperation;

    private Double montant;
    private String type;

    // liaison
    @ManyToOne
    @JoinColumn(name = "CODE_CPTE") // colonne de jointure
    private Compte compte;

    //Constructeur sans "id"
    public Operation(Date dateOperation, Double montant, Compte compte) {
        this.dateOperation = dateOperation;
        this.montant = montant;
        this.compte = compte;
    }

}
