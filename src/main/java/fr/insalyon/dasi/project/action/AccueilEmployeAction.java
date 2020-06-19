package fr.insalyon.dasi.project.action;

import fr.insalyon.dasi.td1.metier.modele.Client;
import fr.insalyon.dasi.td1.metier.modele.Consultation;
import fr.insalyon.dasi.td1.metier.modele.Medium;
import fr.insalyon.dasi.td1.metier.modele.ProfilAstral;
import fr.insalyon.dasi.td1.metier.service.Service;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AccueilEmployeAction extends Action{

    @Override
    public void executer(HttpServletRequest request) {

        Service service = new Service();
        HttpSession session = request.getSession();
        
        Long idEmploye = (Long) session.getAttribute("idEmploye");
        
        List<Consultation> consults= service.findEmployeById(idEmploye).getConsultations();
        
        Client client = null;
        Medium medium = null;
        
        for (Consultation consult : consults){
            if(consult.getDateHeureDemande() != null && consult.getDateHeureDebut() == null){
                client=consult.getClient();
                medium=consult.getMedium();
                break;
            }
        }

        if (client != null && medium != null){
            ProfilAstral profilAstral = client.getProfilAstral();
            request.setAttribute("nomClient", client.getNom());
            request.setAttribute("prenomClient", client.getPrenom());
            request.setAttribute("profilAstral", profilAstral);
            request.setAttribute("medium", medium);
        }

//        System.out.println("" + idEmploye);
//        System.out.println("###### ID CLIENT: " + idEmploye);
//        System.out.println("\n\n###### profil astral: \n");
//        System.out.println("annimal : " + profilAstral.getAnimalTotem() + "\n");
//        System.out.println("couleur : " + profilAstral.getCouleurPorteBonheur() + "\n");
//        System.out.println("signe astro chinois : " + profilAstral.getSigneAstrologiqueChinois() + "\n");
//        System.out.println("signe zodiac : " + profilAstral.getSigneZodiac() + "\n");
        
        


    }
}
