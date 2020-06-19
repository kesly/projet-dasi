package fr.insalyon.dasi.project.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DemarrerConsultationSerialisation extends Serialisation {
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Boolean consultationDemarree = (Boolean) request.getAttribute("consultationDemarree");

        JsonObject container = new JsonObject();

        container.addProperty("consultationDemarree", consultationDemarree);

        System.out.println("container"+ container);

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
