package fr.insalyon.dasi.project.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.td1.metier.modele.Medium;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ListerMediumSerialisation extends Serialisation {
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Medium> mediumList = (List<Medium>) request.getAttribute("mediums");

        JsonObject container = new JsonObject();

        if (mediumList != null) {

            JsonArray jsonAllMedium = new JsonArray();

            for (Medium medium : mediumList) {

                JsonObject jsonMedium = new JsonObject();

                jsonMedium.addProperty("id", medium.getId());
                jsonMedium.addProperty("denomination", medium.getDenomination());
                jsonMedium.addProperty("genre", medium.getGenre());
                jsonMedium.addProperty("presentation", medium.getPresentation());

                jsonAllMedium.add(jsonMedium);
            }

            container.add("mediums", jsonAllMedium);

        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
