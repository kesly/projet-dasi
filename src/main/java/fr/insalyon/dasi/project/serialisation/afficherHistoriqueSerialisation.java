package fr.insalyon.dasi.project.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.td1.metier.modele.Client;
import fr.insalyon.dasi.td1.metier.modele.Consultation;
import fr.insalyon.dasi.td1.metier.modele.Medium;
import fr.insalyon.dasi.td1.metier.modele.ProfilAstral;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

public class afficherHistoriqueSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Consultation> consultations = null;
        if((List<Consultation>)request.getAttribute("consultations")!=null){
            consultations = (List<Consultation>) request.getAttribute("consultations");
        }
        

        JsonObject container = new JsonObject();
        if (!consultations.isEmpty() || consultations!=null) {

            JsonArray consultationArray = new JsonArray();

            for (Consultation consultation : consultations) {
                JsonObject jsonClient = new JsonObject();
                jsonClient.addProperty("consultation", consultation.getId());

                String commentaire = consultation.getCommentaire();
                Date dateHeureDebut = consultation.getDateHeureDebut();
                Date dateHeureFin = consultation.getDateHeureFin();
                String medium = consultation.getMedium().getDenomination();
                String mediumPresentation = consultation.getMedium().getPresentation();

                if (commentaire != null) {
                    jsonClient.addProperty("commentaire", commentaire);
                } else {
                    jsonClient.addProperty("commentaire", "Pas de commentaire");
                }
                if (dateHeureDebut != null) {
                    jsonClient.addProperty("datedebut", dateHeureDebut.toString());
                } else {
                    jsonClient.addProperty("datedebut", "Pas de date d'heure de début");
                }
                if (dateHeureFin != null) {
                    jsonClient.addProperty("datefin", dateHeureFin.toString());
                } else {
                    jsonClient.addProperty("datefin", "Pas de date d'heure de fin");
                }
                if (medium != null) {
                    jsonClient.addProperty("medium", medium);
                } else {
                    jsonClient.addProperty("medium", "Pas de medium");
                }
                if (mediumPresentation != null) {
                    jsonClient.addProperty("mediumpresentation", mediumPresentation);
                } else {
                    jsonClient.addProperty("mediumpresentation", "Pas de presentation du medium");
                }
                consultationArray.add(jsonClient);
            }
            container.add("consultations", consultationArray);
        }

        System.out.println(container);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();

    }
}
