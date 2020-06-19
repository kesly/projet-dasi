package fr.insalyon.dasi.project.action;

import fr.insalyon.dasi.td1.metier.modele.Consultation;
import fr.insalyon.dasi.td1.metier.service.Service;

import javax.servlet.http.HttpServletRequest;

public class DemarrerConsultationAction  extends Action{
    @Override
    public void executer(HttpServletRequest request) {


        Service service = new Service();

        Long idConsultation = Long.valueOf(request.getParameter("idConsultation"));

        System.out.println("###### ID CONSULTATION: " + idConsultation);

        Consultation consultation = service.findConsultationById(idConsultation);

        Boolean consultationDemarree = service.demarrerConsultation(consultation);

        request.setAttribute("consultationDemarree", consultationDemarree);

    }
}
