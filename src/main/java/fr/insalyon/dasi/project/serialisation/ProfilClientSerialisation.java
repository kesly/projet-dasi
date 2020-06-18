package fr.insalyon.dasi.project.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.td1.metier.modele.Client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ProfilClientSerialisation extends Serialisation {
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Client client = (Client)request.getAttribute("client");

        JsonObject container = new JsonObject();

        Boolean connexion = (client != null);
        container.addProperty("connexion", connexion);

        if (client != null) {
            JsonObject jsonClient = new JsonObject();
            jsonClient.addProperty("id", client.getId());
            jsonClient.addProperty("nom", client.getNom());
            jsonClient.addProperty("prenom", client.getPrenom());
            jsonClient.addProperty("mail", client.getMail());

            container.add("client", jsonClient);
        }
        
        System.out.println("container"+ container);

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();

    }
}
