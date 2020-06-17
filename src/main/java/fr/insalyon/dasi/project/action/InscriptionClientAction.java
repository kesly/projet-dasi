/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.insalyon.dasi.project.action;

import fr.insalyon.dasi.td1.metier.modele.Client;
import fr.insalyon.dasi.td1.metier.service.Service;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 *
 * @author imad9
 */
public class InscriptionClientAction extends Action{

    @Override
    public void executer(HttpServletRequest request) {
        

        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        
        
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        Date date = null;
        try {
            date = simpleDateFormat.parse(request.getParameter("date"));
        } catch (ParseException ex) {
            Logger.getLogger(InscriptionClientAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        String adressePostale = request.getParameter("adressePostale");
        String codePostal = request.getParameter("codePostal");
        String commune = request.getParameter("commune");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        String mdp = request.getParameter("mdp");
        
        String addresseComplete = adressePostale+", "+codePostal+" "+commune;
        Service service = new Service();
        
       
        System.out.println("Test 1 :" +addresseComplete);
        System.out.println("Test 2 : "+ date);
        
        Client c = new Client(nom,prenom,date,addresseComplete,telephone,"M",email,mdp);

        
        try {
            service.inscriptionClient(c);
            request.setAttribute("reussi", true);
        } catch (IOException ex) {
            Logger.getLogger(InscriptionClientAction.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("reussi", false);
        }
    }
    
}
