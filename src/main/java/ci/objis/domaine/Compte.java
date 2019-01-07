/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.domaine;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe mère des classes CompteCourant et CompteEpargne
 * @author USER
 */
@Data
@NoArgsConstructor
@Entity(name= "Compte")
@Table(name= "COMPTE")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name="TYPE_CPTE", discriminatorType = DiscriminatorType.STRING,length=2)
public abstract class Compte implements Serializable{
    
    
    //Propriétés
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) pas besoin de ca pour "id" en string
    private String numeroCompte; //represente "id"
    
    @Temporal(TemporalType.DATE) // cette anotation permet de récupérer "l'heure,la minute, le jour, le mois et l'année"
    private Date dateCreation;
    
    private Double solde;
    
    // liason
    @ManyToOne
    @JoinColumn(name="CODE_CLI") // nom de la colonne de jointure
    private Client client;
    
    @OneToMany(mappedBy = "compte")
    private Collection<Operation> Operation; 


    // Constructeur sans "id"
    public Compte( Date dateCreation, Double solde, Client client) {
        
        this.dateCreation = dateCreation;
        this.solde = solde;
        this.client = client;
    }

}
