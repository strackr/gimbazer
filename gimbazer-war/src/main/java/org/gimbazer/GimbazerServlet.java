package org.gimbazer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class GimbazerServlet extends HttpServlet {

    private static final double FLIP_PROB = 0.5;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String requestString = URLDecoder.decode(
                req.getRequestURI().replaceFirst("/", ""),
                StandardCharsets.UTF_8.displayName());
        StringBuilder responseBuilder = new StringBuilder();
        for (char character : requestString.toCharArray()) {
            if (Math.random() < FLIP_PROB) {
                if (Character.isUpperCase(character)) {
                    responseBuilder.append(Character.toLowerCase(character));
                } else {
                    responseBuilder.append(Character.toUpperCase(character));
                }
            } else {
                responseBuilder.append(character);
            }
        }
        resp.getWriter().println(responseBuilder.toString());
    }
}
