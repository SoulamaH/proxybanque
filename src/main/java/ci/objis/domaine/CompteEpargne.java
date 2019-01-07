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
 * Classe CompteEpargne herite des propriétées de la classe Compte 
 * @author USER
 */
@Data
@NoArgsConstructor
@Entity(name= "CompteEpargne")
@Table(name= "COMPTEEPARGNE")
@DiscriminatorValue("CE")

public class CompteEpargne extends Compte{
    
    // proPriete
    private double taux;

    // Constructeur sans "id"
    public CompteEpargne(Date dateCreation, Double solde, Client client) {
        super(dateCreation, solde, client);
    }


    
}
