package fr.insalyon.dasi.project.action;

import fr.insalyon.dasi.td1.metier.modele.Client;
import fr.insalyon.dasi.td1.metier.modele.Consultation;
import fr.insalyon.dasi.td1.metier.modele.Medium;
import fr.insalyon.dasi.td1.metier.modele.ProfilAstral;
import fr.insalyon.dasi.td1.metier.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DemanderConsultationAction extends Action{
    @Override
    public void executer(HttpServletRequest request) {

        Service service = new Service();
        HttpSession session = request.getSession();

        Long idClient = (Long) session.getAttribute("idClient");
        
        String idMediume = request.getParameter("idMedium");
        
        System.out.println("idMedium " + idMediume);

        Long idMedium = Long.valueOf(request.getParameter("idMedium"));

        System.out.println("###### ID CLIENT: " + idClient);

        Client client = service.findClientById(idClient);
        Medium medium = service.findMediumById(idMedium);

        Consultation consultation = null;

        consultation = service.demandeConsultation(client, medium);

        request.setAttribute("consultation", consultation);

    }
}
