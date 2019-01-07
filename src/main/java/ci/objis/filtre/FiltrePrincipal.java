/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.objis.filtre;

import ci.objis.domaine.Conseiller;
import ci.objis.service.impl.ConseillerService;
import ci.objis.services.IRetraitService;
import ci.objis.services.IVersementService;
import ci.objis.services.IVirementService;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author USER
 */
@WebFilter(filterName = "FiltrePrincipal", urlPatterns = {"/admin/accueil", "/admin/enregistrementVirement", "/admin/enregistrementVersement", "/admin/enregistrementRetrait"})
public class FiltrePrincipal implements Filter {

    //objets gere par EJB
    @EJB
    private ConseillerService conseillerService;

    @EJB
    private IVirementService iVirementService;

    @EJB
    IVersementService iVersementService;

    @EJB
    IRetraitService iRetraitService;

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public FiltrePrincipal() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FiltrePrincipal:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FiltrePrincipal:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        // permet de recuperer notre userName
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        HttpSession httpSession = (HttpSession) httpRequest.getSession();

        // renvoie les vues
        String liens = httpRequest.getRequestURL().toString();

        if (liens.equals("http://localhost:8080/banque/admin/enregistrementVirement")) {
            contrainteComptesVirement(request, response, chain);

        } else if (liens.equals("http://localhost:8080/banque/admin/enregistrementVersement")) {
            contrainteComptesVersement(request, response, chain);

        } else if (liens.equals("http://localhost:8080/banque/admin/enregistrementRetrait")) {
            contrainteComptesRetrait(request, response, chain);

        } else {
            //retourne 
            Conseiller conseiller = conseillerService.readOneByUserName(httpRequest.getRemoteUser());
            httpSession.setAttribute("user", conseiller);

            chain.doFilter(request, response);
        }

    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("FiltrePrincipal:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("FiltrePrincipal()");
        }
        StringBuffer sb = new StringBuffer("FiltrePrincipal(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

    // methode de verifications
    private boolean verificationCompteDebiteur(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        // recuperation des données venants de la JSP
        String numeroCompteDebiteur = request.getParameter("numcomptedebiteur");
        String numeroCompteCrediteur = request.getParameter("numcomptecrediteur");
        String montantString = request.getParameter("montant");

        // condition on reste sur la vue si non existance du compte
        if (!iVirementService.verificationExistanceCompte(numeroCompteDebiteur)) {

            request.setAttribute("numeroCompteDebiteur", numeroCompteDebiteur);
            request.setAttribute("numeroCompteCrediteur", numeroCompteCrediteur);
            request.setAttribute("montant", montantString);

            request.setAttribute("confirme", "Le Compte débiteur n'existe pas");

            return false;
        }
        return true;
    }

    private boolean verificationCompteCrediteur(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        // recuperation des données
        String numeroCompteDebiteur = request.getParameter("numcomptedebiteur");
        String numeroCompteCrediteur = request.getParameter("numcomptecrediteur");
        String montantString = request.getParameter("montant");

        // condition on reste sur la vue si non existance du compte
        if (!iVirementService.verificationExistanceCompte(numeroCompteCrediteur)) {

            request.setAttribute("numeroCompteDebiteur", numeroCompteDebiteur);
            request.setAttribute("numeroCompteCrediteur", numeroCompteCrediteur);
            request.setAttribute("montant", montantString);

            request.setAttribute("confirme", "Le Compte créditeur n'existe pas");
            return false;
        }
        return true;
    }

    // methode de contrainte pour les virements
    private void contrainteComptesVirement(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        //renvoie vers la servlet si la condition existe
        if (verificationCompteDebiteur(request, response, chain) && verificationCompteCrediteur(request, response, chain) && verificationEgaliteComptes(request, response, chain) && verificationVirementCompteACompte(request, response, chain) && verificationSolde(request, response, chain)) {
            chain.doFilter(request, response);
            System.out.println(" ");
            System.out.println(" ");
        } else {
            System.out.println(" ");
            System.out.println("contrainte compte virement doFilter else");
            System.out.println(" ");
            request.getRequestDispatcher("/pagesJsp/virement.jsp").forward(request, response);
        }
    }

    // methode de contrainte pour les versements
    private void contrainteComptesVersement(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        //renvoie vers la servlet si la condition existe
        if (verificationExistanceComptePourVersement(request, response, chain)) {
            chain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("/pagesJsp/versement.jsp").forward(request, response);
        }
    }

    
    // methode de contrainte pour les retrait
    private void contrainteComptesRetrait(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        //renvoie vers la servlet si la condition existe
        if (verificationExistanceComptePourRetrait(request, response, chain) && verificationSoldePourRetrait(request, response, chain)) {
            chain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("/pagesJsp/retrait.jsp").forward(request, response);
        }
    }

    // methode d'egalité entre les comptes
    private boolean verificationEgaliteComptes(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        // recuperation des données
        String numeroCompteDebiteur = request.getParameter("numcomptedebiteur");
        String numeroCompteCrediteur = request.getParameter("numcomptecrediteur");
        String montantString = request.getParameter("montant");

        // condition on reste sur la vue si les comptes sont identiques
        if (numeroCompteDebiteur.equals(numeroCompteCrediteur)) {

            request.setAttribute("numeroCompteDebiteur", numeroCompteDebiteur);
            request.setAttribute("numeroCompteCrediteur", numeroCompteCrediteur);
            request.setAttribute("montant", montantString);

            request.setAttribute("confirme", "Les Comptes sont identiques");

            return false;
        }
        return true;
    }

    // methode du type de compte
    private boolean verificationVirementCompteACompte(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        // recuperation des données
        String numeroCompteDebiteur = request.getParameter("numcomptedebiteur");
        String numeroCompteCrediteur = request.getParameter("numcomptecrediteur");
        String montantString = request.getParameter("montant");

        // condition on reste sur la vue si les comptes sont identiques
        if (!iVirementService.contrainteVirement(numeroCompteDebiteur, numeroCompteCrediteur)) {

            request.setAttribute("numeroCompteDebiteur", numeroCompteDebiteur);
            request.setAttribute("numeroCompteCrediteur", numeroCompteCrediteur);
            request.setAttribute("montant", montantString);

            request.setAttribute("confirme", "opération impossible entre ces deux comptes (Compte Debiteur = Epargne)");

            return false;
        }
        return true;
    }

    // methode de verification du solde
    private boolean verificationSolde(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        // recuperation des données
        String numeroCompteDebiteur = request.getParameter("numcomptedebiteur");
        String numeroCompteCrediteur = request.getParameter("numcomptecrediteur");
        String montantString = request.getParameter("montant");

        // condition on reste sur la vue si les comptes sont identiques
        if (iVirementService.verifierSolde(numeroCompteDebiteur, Double.parseDouble(montantString))) {

            request.setAttribute("numeroCompteDebiteur", numeroCompteDebiteur);
            request.setAttribute("numeroCompteCrediteur", numeroCompteCrediteur);
            request.setAttribute("montant", montantString);
            System.out.println(" ");
            System.out.println("solde virement");
            System.out.println(" ");
            request.setAttribute("confirme", "le solde est insuffisant");

            return false;
        }
        return true;
    }

    // verification d'existance du compte pour un versement
    private boolean verificationExistanceComptePourVersement(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        // recuperation des données
        String numeroCompte = request.getParameter("numcompte");
        String montantString = request.getParameter("montant");

        // condition on reste sur la vue si non existance du compte
        if (!iVersementService.verificationExistanceCompte(numeroCompte)) {

            request.setAttribute("numeroCompte", numeroCompte);
            request.setAttribute("montant", montantString);

            request.setAttribute("confirme", "Ce Compte n'existe pas");

            return false;
        }
        return true;
    }

    // verification d'existance du compte pour un retrait
    private boolean verificationExistanceComptePourRetrait(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        // recuperation des elements venant de la JSP
        String numeroCompte = request.getParameter("numcompte");
        String montantString = request.getParameter("montant");

        //condition on reste sur la vue si non existance du compte
        if (!iRetraitService.verificationExistanceCompte(numeroCompte)) {

            request.setAttribute("numeroCompte", numeroCompte);
            request.setAttribute("montant", montantString);

            request.setAttribute("confirme", "Ce Compte n'existe pas");

            return false;
        }
        return true;
    }

    // methode de verification du solde
    private boolean verificationSoldePourRetrait(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        // recuperation des données
        String numeroCompte = request.getParameter("numcompte");
        String montantString = request.getParameter("montant");

        // condition on reste sur la vue si les comptes sont identiques
        if (iRetraitService.verifierSolde(numeroCompte, Double.parseDouble(montantString))) {

            request.setAttribute("numeroCompte", numeroCompte);
            request.setAttribute("montant", montantString);

            request.setAttribute("confirme", "le solde est insuffisant pour effectuer un retrait");

            return false;
        }
        return true;
    }

}
