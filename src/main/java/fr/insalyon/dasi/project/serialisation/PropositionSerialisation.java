package fr.insalyon.dasi.project.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.td1.metier.modele.Client;
import fr.insalyon.dasi.td1.metier.modele.ProfilAstral;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PropositionSerialisation extends Serialisation {
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ProfilAstral profilAstral = (ProfilAstral)request.getAttribute("profilAstral");

        JsonObject container = new JsonObject();


        if (profilAstral != null) {
            JsonObject jsonClient = new JsonObject();
            jsonClient.addProperty("animalTotem", profilAstral.getAnimalTotem());
            jsonClient.addProperty("couleurPorteBonheur", profilAstral.getCouleurPorteBonheur());
            jsonClient.addProperty("signeAstrologiqueChinois", profilAstral.getSigneAstrologiqueChinois());
            jsonClient.addProperty("signeZodiac", profilAstral.getSigneZodiac());

            container.add("profilAstral", jsonClient);
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();

    }
}
