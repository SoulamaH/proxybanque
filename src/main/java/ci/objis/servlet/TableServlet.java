/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.servlet;

import ci.objis.service.impl.CompteCourantService;
import ci.objis.service.impl.CompteEpargneService;
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
 * @author Djabi & SOULAMA
 */
@WebServlet(name = "TableServlet", urlPatterns = {"/admin/tableclient"})
public class TableServlet extends HttpServlet {


    // objets
    @EJB
    private CompteCourantService compteCourantService;

    @EJB
    private CompteEpargneService compteEpargneService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // recuperation des enregistrements dans la table compte de la BD.
        request.setAttribute("listeCourantClient", compteCourantService.readAll());
        
        request.setAttribute("listeEpargneClient", compteEpargneService.readAll());

        // affichage de la jsp
        request.getRequestDispatcher("/pagesJsp/tables.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd = request.getRequestDispatcher("/pagesJsp/tables.jsp");
        rd.forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
