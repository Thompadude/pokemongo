package com.pokemongo.utilities;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * If GET request is sent to the path /images/* response with the file based on endpoint.
 */
@WebServlet("/images/*")
public class ImageServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Fetch endpoint in the url, after /images/
        String filename = req.getPathInfo().substring(1);

        // Look for the file based on the endpoint in the images directory in the application server.
        File file = new File(System.getProperty("jboss.server.data.dir") + "/images", filename);
    
        resp.setHeader("Content-Type", getServletContext().getMimeType(filename));
        resp.setHeader("Content-Length", String.valueOf(file.length()));
        resp.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
    
        // Response with the outputstream (the file)
        Files.copy(file.toPath(), resp.getOutputStream());
    }
}
