package fr.insalyon.dasi.project.action;

import fr.insalyon.dasi.td1.metier.modele.Client;
import fr.insalyon.dasi.td1.metier.modele.Medium;
import fr.insalyon.dasi.td1.metier.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ListerMediumAction extends Action{

    @Override
    public void executer(HttpServletRequest request) {

        Service service = new Service();
        List<Medium> mediumList = null;
        mediumList = service.findAllMedium();

        System.out.println("mediums trouvé : "+ mediumList);
        request.setAttribute("mediums", mediumList);

        // Gestion de la Session: ici, enregistrer l'ID du Client authentifié
        HttpSession session = request.getSession();
    }
}
