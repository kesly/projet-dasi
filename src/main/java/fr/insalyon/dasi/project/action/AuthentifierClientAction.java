package fr.insalyon.dasi.project.action;

import javax.servlet.http.HttpServletRequest;

public class AuthentifierClientAction extends Action{

    @Override
    public void executer(HttpServletRequest request) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
//
//        Service service = new Service();
//        Client client = service.authentifierClient(login, password);
//
//        request.setAttribute("client", client);
//
//        // Gestion de la Session: ici, enregistrer l'ID du Client authentifié
//        HttpSession session = request.getSession();
//        if (client != null) {
//            session.setAttribute("idClient", client.getId());
//        }
//        else {
//            session.removeAttribute("idClient");
//        }

    }
}
