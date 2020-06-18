package fr.insalyon.dasi.project.action;

import fr.insalyon.dasi.td1.metier.modele.ProfilAstral;
import fr.insalyon.dasi.td1.metier.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AccueilEmployeAction extends Action{

    @Override
    public void executer(HttpServletRequest request) {

        Service service = new Service();
        HttpSession session = request.getSession();
        
        Long idEmploye = (Long) session.getAttribute("idEmploye");
        
        

        System.out.println("" + idClient);
        System.out.println("###### ID CLIENT: " + idClient);
        ProfilAstral profilAstral = service.findClientById(idClient).getProfilAstral();
        System.out.println("\n\n###### profil astral: \n");
        System.out.println("annimal : " + profilAstral.getAnimalTotem() + "\n");
        System.out.println("couleur : " + profilAstral.getCouleurPorteBonheur() + "\n");
        System.out.println("signe astro chinois : " + profilAstral.getSigneAstrologiqueChinois() + "\n");
        System.out.println("signe zodiac : " + profilAstral.getSigneZodiac() + "\n");
        
        
        request.setAttribute("profilAstral", profilAstral);


    }
}
