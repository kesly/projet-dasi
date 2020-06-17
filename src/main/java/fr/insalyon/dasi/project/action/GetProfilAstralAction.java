package fr.insalyon.dasi.project.action;

import fr.insalyon.dasi.td1.metier.modele.ProfilAstral;
import fr.insalyon.dasi.td1.metier.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GetProfilAstralAction extends Action{

    @Override
    public void executer(HttpServletRequest request) {

        String idClient = request.getParameter("id");

        System.out.println("###### ID CLIENT: " + idClient);
        
        
        Service service = new Service();
        HttpSession session = request.getSession();
        
//        ProfilAstral profilAstral = service.findClientById(idClient).getProfilAstral();

        

    }
}
