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

        List<Consultation> consultations = (List<Consultation>) request.getAttribute("consultations");

        JsonObject container = new JsonObject();
        if (!consultations.isEmpty()) {
               
            JsonArray consultationArray = new JsonArray();
           
            for (Consultation consultation : consultations) {
                JsonObject jsonClient = new JsonObject();
                jsonClient.addProperty("consultation", consultation.getId());

                String commentaire = consultation.getCommentaire();
                Date dateHeureDebut = consultation.getDateHeureDebut();
                Date dateHeureFin = consultation.getDateHeureFin();
                String medium = consultation.getMedium().getDenomination();
                String mediumPresentation = consultation.getMedium().getPresentation();
                

                jsonClient.addProperty("commentaire", commentaire);
                jsonClient.addProperty("datedebut", dateHeureDebut.toString());
                jsonClient.addProperty("datefin", dateHeureFin.toString());
                jsonClient.addProperty("medium", medium);
                jsonClient.addProperty("mediumpresentation", mediumPresentation);
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
