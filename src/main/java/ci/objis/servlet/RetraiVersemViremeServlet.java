/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.servlet;

import ci.objis.domaine.Retrait;
import ci.objis.domaine.Versement;
import ci.objis.domaine.Virement;
import ci.objis.service.impl.CompteCourantService;
import ci.objis.service.impl.CompteEpargneService;
import ci.objis.service.impl.RetraitService;
import ci.objis.service.impl.VersementService;
import ci.objis.service.impl.VirementService;
import java.io.IOException;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 *
 * CETTE SERVLET PERMET L'AFFICHAGE DE TROIS (03) VUES
 */
@WebServlet(name = "RetraiVersemViremeServlet", urlPatterns = {"/admin/retraitservlet", "/admin/versementservlet", "/admin/virementservlet", "/admin/enregistrementVersement" , "/admin/enregistrementVirement", "/admin/enregistrementRetrait" })
public class RetraiVersemViremeServlet extends HttpServlet {

    // proprietes
    private String liens;

    // objets
    @EJB
    private VirementService virementService;

    @EJB
    private CompteCourantService compteCourantService;

    @EJB
    private CompteEpargneService compteEpargneService;

    @EJB
    private VersementService versementService;

    @EJB
    private RetraitService retraitService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        liens = request.getRequestURL().toString();

        if (liens.equals("http://localhost:8080/banque/admin/retraitservlet")) {
            afficherVuRetrait(request, response);

        } else if (liens.equals("http://localhost:8080/banque/admin/versementservlet")) {

            afficherVuVersement(request, response);

        } else {
            afficherVuVirement(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        liens = request.getRequestURL().toString();

        if (liens.equals("http://localhost:8080/banque/admin/enregistrementVirement")) {

            enregistrerVirement(request, response);
        } else if (liens.equals("http://localhost:8080/banque/admin/enregistrementVersement")) {

            enregistrerVersement(request, response);
        } else{
            enregistrerRetrait(request, response);
        }

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

    // methode pour la servlet des operations(retrait, versement et virement) elle permet de les afficher 
    private void afficherVuRetrait(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //request.setAttribute("listeCourantClient", courantService.readAll());
        request.getRequestDispatcher("/pagesJsp/retrait.jsp").forward(request, response);
    }

    private void afficherVuVersement(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //request.setAttribute("listeCourantClient", courantService.readAll());
        request.getRequestDispatcher("/pagesJsp/versement.jsp").forward(request, response);
    }

    private void afficherVuVirement(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //request.setAttribute("listeCourantClient", courantService.readAll());
        request.getRequestDispatcher("/pagesJsp/virement.jsp").forward(request, response);
    }

    // methodes de transactions
    /* enregistrement du virement*/
    private void enregistrerVirement(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //objet
        Virement virementD = new Virement();
        Virement virementC = new Virement();

        // recuperation des données venant de la jsp
        String numeroCompteDebiteur = request.getParameter("numcomptedebiteur");
        String numeroCompteCrediteur = request.getParameter("numcomptecrediteur");

        String montantString = request.getParameter("montant");
        Double montant = null;

        // conversion du montant en Double
        if (!"".equals(montantString)) {
            montant = Double.parseDouble(montantString);
        }

        // condition 
        if (virementService.verifierTypeCompte(numeroCompteDebiteur).equals("CompteCourant")) {
            virementD.setCompte(compteCourantService.readOne(numeroCompteDebiteur));
        }

        //recuperation du montant et la date
        virementD.setMontant(montant);
        virementD.setDateOperation(new Date());

        // condition 
        if (virementService.verifierTypeCompte(numeroCompteCrediteur).equals("CompteCourant")) {
            virementC.setCompte(compteCourantService.readOne(numeroCompteCrediteur));
        } else {
            virementC.setCompte(compteEpargneService.readOne(numeroCompteCrediteur));
        }

        //recuperation du montant et de la date
        virementC.setMontant(montant);
        virementC.setDateOperation(new Date());

        virementService.effectuerVersement(virementD, virementC);
        request.getRequestDispatcher("/pagesJsp/virement.jsp").forward(request, response);
    }

    // enregistrement du versement
    private void enregistrerVersement(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // recuperation des données venant de la servlet
        String numeCompte = request.getParameter("numcompte");
        String montantString = request.getParameter("montant");

        // Initialisation du montant
        Double montant = null;

        //conversion du montant en Double
        if (!"".equals(montantString)) {
            montant = Double.parseDouble(montantString);
        }

        // objet
        Versement versement = null;
        versement = new Versement();

        // recuperation de la reference et du montant et de la date
        versement.setReference(versementService.genererReference());
        versement.setMontant(montant);
        versement.setDateOperation(new Date());

        // enregistrement dans la BD
        if (versementService.verser(versement, numeCompte) != null) {

            versement = null;
            request.getRequestDispatcher("/pagesJsp/versement.jsp").forward(request, response);
        }

    }
    
    
    
    // enregistrement du versement
    private void enregistrerRetrait(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // recuperation des données venant de la servlet
        String numeCompte = request.getParameter("numcompte");
        String montantString = request.getParameter("montant");

        // Initialisation du montant
        Double montant = null;

        //conversion du montant en Double
        if (!"".equals(montantString)) {
            montant = Double.parseDouble(montantString);
        }

        // objet
        Retrait retrait = null;
        retrait = new Retrait();

        // recuperation de la reference et du montant et de la date
        retrait.setReference(versementService.genererReference());
        retrait.setMontant(montant);
        retrait.setDateOperation(new Date());

        // enregistrement dans la BD
        if (retraitService.retrait(retrait, numeCompte) != null) {

            retrait = null;
            request.getRequestDispatcher("/pagesJsp/retrait.jsp").forward(request, response);
        }

    }
    
    
    
}
