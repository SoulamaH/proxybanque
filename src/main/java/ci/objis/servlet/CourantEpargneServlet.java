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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
@WebServlet(name = "CourantEpargneServlet", urlPatterns = {"/admin/courantservlet", "/admin/epargneservlet"})
public class CourantEpargneServlet extends HttpServlet {

    // propriets
    @EJB
    private CompteCourantService courantService;

    @EJB
    private CompteEpargneService epargneService;
    
    private String lien;

    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        lien = request.getRequestURL().toString();

        if (lien.equals("http://localhost:8080/banque/admin/courantservlet")) {
            afficherVuTableCourant(request, response);
        }else{
            afficherVuTableEpargne(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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

    
    
    // methode pour la servlet des comptes courant et epargne elle permet d'afficher leur tableau
    private void afficherVuTableCourant(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("listeCourantClient", courantService.readAll());
        request.getRequestDispatcher("/pagesJsp/compteCourant.jsp").forward(request, response);
    }

    private void afficherVuTableEpargne(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("listeEpargneClient", epargneService.readAll());
        request.getRequestDispatcher("/pagesJsp/compteEpargne.jsp").forward(request, response);
    }
}
