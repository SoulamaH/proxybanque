package ci.objis.domaine;

import ci.objis.domaine.Compte;
import ci.objis.domaine.Conseiller;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2018-12-28T17:26:46")
@StaticMetamodel(Client.class)
public class Client_ { 

    public static volatile CollectionAttribute<Client, Compte> comptes;
    public static volatile SingularAttribute<Client, Conseiller> conseiller;
    public static volatile SingularAttribute<Client, String> tel;
    public static volatile SingularAttribute<Client, Long> id;
    public static volatile SingularAttribute<Client, String> nom;
    public static volatile SingularAttribute<Client, String> prenom;

}