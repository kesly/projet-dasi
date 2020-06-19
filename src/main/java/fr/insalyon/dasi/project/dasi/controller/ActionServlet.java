/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.project.dasi.controller;

import fr.insalyon.dasi.project.action.*;
import fr.insalyon.dasi.project.serialisation.*;
import fr.insalyon.dasi.td1.dao.JpaUtil;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author keslygassant
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {


    @Override
    public void init() throws ServletException {
        super.init();
        JpaUtil.init();
    }

    @Override
    public void destroy() {
        JpaUtil.destroy();
        super.destroy();
    }


    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");


        HttpSession session = request.getSession(true);
        request.setCharacterEncoding("UTF-8");
        String todo = request.getParameter("todo");

        Action action = null;
        Serialisation serialisation = null;

        System.out.println("toto : "+todo);
        if (todo != null) {
            switch (todo) {
                case "connexionClient":
                    action = new AuthentifierClientAction();
                    serialisation = new ProfilClientSerialisation();
                    break;
                case "connexionEmploye":
                    action = new AuthentifierEmployeAction();
                    serialisation = new ProfilEmployeSerialisation();
                    break;
                case "inscriptionClient":
                    action = new InscriptionClientAction();
                    serialisation = new ValidationInscriptionSerialisation();
                    break;
                case "consulterHistorique":
                    action = new consulterHistoriqueAction();
                    serialisation = new afficherHistoriqueSerialisation();
                    break;
                case "profil-astral":
                    action = new GetProfilAstralAction();
                    serialisation = new ProfilAstralSerialisation();
                    break;
                case "listerMedium":
                    action = new ListerMediumAction();
                    serialisation = new ListerMediumSerialisation();
                    break;
                case "accueilEmploye":
                    action = new AccueilEmployeAction();
                    serialisation = new PropositionSerialisation();
                    break;
                case "demandeConsultation":
                    action = new DemanderConsultationAction();
                    serialisation = new DemanderConsultationSerialisation();
                    break;
                case "obtenirPrediction":
                    action = new ObtenirPredictionAction();
                    serialisation = new ObtenirPredictionSerialisation();
                    break;
                case "terminerConsultation":
                    action = new TerminerConsultationAction();
                    serialisation = new TerminerConsultationSerialisation();
                    break;

                case "demarrerConsultation":
                    action = new DemarrerConsultationAction();
                    serialisation = new DemarrerConsultationSerialisation();
                    break;

            }

            if (action != null) {
                action.executer(request);
                serialisation.serialiser(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erreur dans les paramètres de la requête");
            }
        }

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ActionServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ActionServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
