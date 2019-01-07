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
 * Classe CompteCourant herite des propriétées de la classe Compte 
 * @author USER
 */
@Data
@NoArgsConstructor
@Entity(name = "CompteCourant")
@Table(name = "COMPTECOURANT")
@DiscriminatorValue("CC")
public class CompteCourant extends Compte {

    // Proprietes
    private double decouvert;

    // Constructeur sans "id"
    public CompteCourant(Date dateCreation, Double solde, Client client) {
        super(dateCreation, solde, client);
    }

}
