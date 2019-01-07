package ci.objis.domaine;

import ci.objis.domaine.Client;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2018-12-28T17:26:46")
@StaticMetamodel(Conseiller.class)
public class Conseiller_ { 

    public static volatile SingularAttribute<Conseiller, String> password;
    public static volatile SingularAttribute<Conseiller, String> role;
    public static volatile ListAttribute<Conseiller, Client> clients;
    public static volatile SingularAttribute<Conseiller, Long> id;
    public static volatile SingularAttribute<Conseiller, String> userName;
    public static volatile SingularAttribute<Conseiller, String> nom;

}