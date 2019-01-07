/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.domaine;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author USER
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Conseiller implements Serializable{

    // proprietes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String role;
    private String userName;
    private String password;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "Conseiller")
    private List<Client> clients;

    // constructeur sans "id et Cilent"
    public Conseiller(String nom, String role, String userName, String password) {
        this.nom = nom;
        this.role = role;
        this.userName = userName;
        this.password = password;
    }

    
}
