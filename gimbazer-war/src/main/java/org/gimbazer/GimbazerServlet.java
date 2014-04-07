package org.gimbazer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class GimbazerServlet extends HttpServlet {

    private static final double FLIP_PROB = 0.5;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String requestString = URLDecoder.decode(
                req.getRequestURI().replaceFirst("/", ""),
                StandardCharsets.UTF_8.displayName());

        TextProcessor processor = new TextFlipperTextProcessor(FLIP_PROB, new Random());
        String processedRequestString = processor.process(requestString);

        resp.getWriter().println(processedRequestString);
    }
}
