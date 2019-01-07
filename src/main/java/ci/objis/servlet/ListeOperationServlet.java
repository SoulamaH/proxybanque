/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.servlet;

import ci.objis.service.impl.CompteCourantService;
import ci.objis.service.impl.CompteEpargneService;
import ci.objis.service.impl.RetraitService;
import ci.objis.service.impl.VersementService;
import ci.objis.service.impl.VirementService;
import java.io.IOException;
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
/**
 * ******* pour l'instant je ne vois pas l'utilité de cette servlet et de la
 * jsp forms ********
 */
@WebServlet(name = "ListeOperationServlet", urlPatterns = {"/admin/listeoperation"})
public class ListeOperationServlet extends HttpServlet {

    // objets
    @EJB
    private CompteCourantService compteCourantService;

    @EJB
    private CompteEpargneService compteEpargneService;

    @EJB
    private VersementService versementService;

    @EJB
    private VirementService virementService;

    @EJB
    private RetraitService retraitService;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // recuperation des enregistrements dans les differentes  tables (versement, virement et retrait) de la BD.
        request.setAttribute("listeCourantClient", compteCourantService.readAll());

        request.setAttribute("listeEpargneClient", compteEpargneService.readAll());

        request.setAttribute("listeVersement", versementService.readAll());

        request.setAttribute("listeVirement", virementService.readAll());

        request.setAttribute("listeRetrait", retraitService.readAll());

        request.getRequestDispatcher("/pagesJsp/listeoperations.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd = request.getRequestDispatcher("/pagesJsp/listeoperations.jsp");
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
