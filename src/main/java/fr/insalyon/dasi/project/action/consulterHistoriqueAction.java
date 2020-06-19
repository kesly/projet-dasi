/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.insalyon.dasi.project.action;

import fr.insalyon.dasi.td1.metier.modele.Client;
import fr.insalyon.dasi.td1.metier.modele.Consultation;
import static fr.insalyon.dasi.td1.metier.modele.Consultation_.client;
import fr.insalyon.dasi.td1.metier.service.Service;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 *
 * @author imad9
 */
public class consulterHistoriqueAction extends Action{

    @Override
    public void executer(HttpServletRequest request) {
        

       
        Service service = new Service();
        HttpSession session = request.getSession();
        
        Long idClient = (Long) session.getAttribute("idClient");
        Client c = service.findClientById(idClient);
        List<Consultation> consultations = service.findAllConsultationByClient(c);

        request.setAttribute("consultations",consultations);
       
    }
    
}
