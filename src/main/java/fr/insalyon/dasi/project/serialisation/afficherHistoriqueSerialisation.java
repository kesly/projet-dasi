package fr.insalyon.dasi.project.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.td1.metier.modele.Consultation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
                System.out.println("777777777777777777777777777");
                System.out.println(dateHeureDebut.toString());
                Date dateHeureFin = consultation.getDateHeureFin();
                String medium = consultation.getMedium().getDenomination();
                String mediumPresentation = consultation.getMedium().getPresentation();
                
                if(dateHeureDebut==null || dateHeureFin == null){
                    break;
                }
                Long secondesDebut = dateHeureDebut.getTime();
                Long secondesFin = dateHeureFin.getTime();
                Long duree = secondesFin-secondesDebut;
                duree=TimeUnit.MILLISECONDS.toMinutes(duree);
                System.out.println("999999999999999999999999999");
                System.out.println(duree);
                jsonClient.addProperty("duree", duree.toString()+" min(s)");


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
