/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.servlet;

import ci.objis.dao.IClientDao;
import ci.objis.domaine.Client;
import ci.objis.domaine.CompteCourant;
import ci.objis.domaine.CompteEpargne;
import ci.objis.domaine.Conseiller;
import ci.objis.service.impl.CompteCourantService;
import ci.objis.service.impl.CompteEpargneService;
import ci.objis.service.impl.ConseillerService;
import ci.objis.services.IClientService;
import java.io.IOException;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
@WebServlet(name = "EjbServlet", urlPatterns = {"/admin/formeenregistrementclient"})
public class EjbServlet extends HttpServlet {

    // Variables
    String infoNom = "";
    String infoPrenom = "";
    String confirme = "";
    String infSolde = "";

    // creation d'objets 
    @EJB
    private IClientDao clientDao;

    @EJB
    private CompteCourantService compteCourantService;

    @EJB
    private CompteEpargneService compteEpargneService;

    @EJB
    private ConseillerService conseillerService;

    @EJB
    private IClientService iClientService;
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // juste pour faire une modif
//        // recuperation de l' ID du client
//        Long idClient =Long.valueOf( request.getParameter("idClient"));
//        
//        // recuperation du client par l'ID
//        
//        Client client = iClientService.readOne(idClient);
//        
//        // envoye du client a la JSP
//        request.setAttribute("clientUpdate", client);
        
        
        
        request.getRequestDispatcher("/pagesJsp/enregistrementclient.jsp").forward(request, response);

        // test de cryptage
       // System.out.println("ejbservlet cryptage code" + " " + conseillerService.cryptagePassword("soulamajava"));
        // conseillerService.ajouter(new Conseiller("objis", "admin", "proxy", conseillerService.cryptagePassword("banque")));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // recuperation des elements venant de la jsp
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String tel = request.getParameter("tel");

        Double solde = Double.valueOf(request.getParameter("solde"));
        String compte = request.getParameter("compte");

        // test sur le nom prenom solde tel
        if ("".equals(nom)) {
            this.infoNom = "ok";
            request.setAttribute("nom", infoNom);
        }

        if ("".equals(prenom)) {
            this.infoPrenom = "ok";
            request.setAttribute("prenom", infoPrenom);
        }

        if ((!"".equals(nom)) && (!"".equals(prenom))) {

            // recuperation du conseiller authentifiÃ© 
            Conseiller conseiller = (Conseiller) request.getSession().getAttribute("user");
            
//            Long idConseiller = conseiller.getId();
//            
//            Conseiller conseiller1 = conseillerService.readOne(idConseiller);

            //Enregistrement dans client
            Client client = clientDao.ajouter(new Client(nom, prenom, tel, conseiller));

//            conseiller.getId;
            if (compte.equals("CC")) {

                // enregistrement dans le compte
                CompteCourant compteCourant = new CompteCourant(new Date(), solde, client);
                compteCourant.setNumeroCompte(compteCourantService.genererNumero());
                compteCourantService.ajouter(compteCourant);
            } else {

                // dans le CompteEpargne 
                CompteEpargne compteEpargne = new CompteEpargne(new Date(), solde, client);
                compteEpargne.setNumeroCompte(compteEpargneService.genererNumero());
                compteEpargneService.ajouter(compteEpargne);
            }

//          courantDao.ajouterCompteCourant(new CompteCourant(new Date(), solde, 
//                                        (new ClientDao()).rechercher(client.getId())));
            this.confirme = "Enregistrement fait avec succes";
            request.setAttribute("confirme", confirme);
        }

        request.setAttribute("valnom", nom);
        request.setAttribute("valprenom", prenom);

        RequestDispatcher rd = request.getRequestDispatcher("/pagesJsp/enregistrementclient.jsp");
        rd.forward(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
