package fr.insalyon.dasi.project.action;

import fr.insalyon.dasi.td1.metier.modele.Client;
import fr.insalyon.dasi.td1.metier.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthentifierClientAction extends Action{

    @Override
    public void executer(HttpServletRequest request) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Service service = new Service();
        Client client = service.getClientByAuthentication(login, password);

        System.out.println("client trouvé : "+ client);
        request.setAttribute("client", client);

        // Gestion de la Session: ici, enregistrer l'ID du Client authentifié
        HttpSession session = request.getSession();
        if (client != null) {
            session.setAttribute("idClient", client.getId());
        }
        else {
            session.removeAttribute("idClient");
        }

    }
}
