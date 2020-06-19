package fr.insalyon.dasi.project.action;

import fr.insalyon.dasi.td1.metier.modele.Client;
import fr.insalyon.dasi.td1.metier.modele.Consultation;
import fr.insalyon.dasi.td1.metier.service.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class TerminerConsultationAction extends Action {
    @Override
    public void executer(HttpServletRequest request) {

        Service service = new Service();

        Long idConsultation = Long.valueOf(request.getParameter("idConsultation"));

        System.out.println("###### ID CONSULTATION: " + idConsultation);

        Consultation consultation = service.findConsultationById(idConsultation);

        Boolean consultationTerminee = service.terminerConsultation(consultation);

        request.setAttribute("consultationTerminee", consultationTerminee);

    }
}
