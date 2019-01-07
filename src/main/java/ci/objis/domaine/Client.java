/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.domaine;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author USER
 */
@Data
@NoArgsConstructor
@Entity(name = "Client")
@Table(name = "CLIENT")

public class Client implements Serializable {

    //proprietes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nom;
    private String prenom;
    private String tel;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Collection<Compte> comptes;
    
    // liaison du client et du conseiller
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "idConseiller")// nom de la colonne dans la BD
    private Conseiller conseiller; 
    
    // Constructeur sans "id"
    public Client(String nom, String prenom, String tel, Conseiller conseiller) {
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.conseiller = conseiller;
    }

}
