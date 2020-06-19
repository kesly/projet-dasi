package fr.insalyon.dasi.project.action;

import fr.insalyon.dasi.td1.metier.modele.Client;
import fr.insalyon.dasi.td1.metier.modele.Consultation;
import fr.insalyon.dasi.td1.metier.modele.Medium;
import fr.insalyon.dasi.td1.metier.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ObtenirPredictionAction  extends Action{
    @Override
    public void executer(HttpServletRequest request) {

        Service service = new Service();

        System.out.println("###### ID CLIENT: " + request.getParameter("idClient"));
        Long idClient = Long.valueOf(request.getParameter("idClient"));

        int amour = Integer.parseInt(request.getParameter("amour"));
        int sante = Integer.parseInt(request.getParameter("sante"));
        int travail = Integer.parseInt(request.getParameter("travail"));

        System.out.println("###### ID CLIENT: " + idClient);

        Client client = service.findClientById(idClient);
        
         System.out.println("###### amour: " + amour);
          System.out.println("###### sante: " + sante);
           System.out.println("###### travail: " + travail);
        
        System.out.println("###### CLIENT: " + client.toString());

        List<String> predictions = null;

        try {
            predictions = service.obtenirPredictions(client, amour, sante, travail);

            request.setAttribute("predictions", predictions);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
