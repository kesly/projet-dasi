package fr.insalyon.dasi.project.action;

import fr.insalyon.dasi.td1.metier.modele.Client;
import fr.insalyon.dasi.td1.metier.modele.Employe;
import fr.insalyon.dasi.td1.metier.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthentifierEmployeAction extends Action{

    @Override
    public void executer(HttpServletRequest request) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Service service = new Service();
        Employe employe = service.getEmployeByAuthentification(login, password);

        System.out.println("employe trouvé : "+ employe.toString());
        request.setAttribute("employe", employe);

        // Gestion de la Session: ici, enregistrer l'ID du Client authentifié
        HttpSession session = request.getSession();
        if (employe != null) {
            session.setAttribute("idEmploye", employe.getId());
        }
        else {
            session.removeAttribute("idEmploye");
        }

    }
}
