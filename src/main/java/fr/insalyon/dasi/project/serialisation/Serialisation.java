/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.project.serialisation;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author keslygassant
 */
public abstract class Serialisation {
    public abstract void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException;
    
}
