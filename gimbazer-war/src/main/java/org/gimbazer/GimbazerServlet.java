package org.gimbazer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GimbazerServlet extends HttpServlet {

    private static final double FLIP_PROB = 0.4;

    private static final List<TokenReplacement> TOKEN_REPLACEMENTS = Collections.unmodifiableList(Arrays.asList(
            new TokenReplacement("u", "oo", 0.4),
            new TokenReplacement("sz", "sh", 0.4),
            new TokenReplacement("o", "0", 0.1),
            new TokenReplacement("l", "1", 0.1),
            new TokenReplacement("e", "3", 0.1),
            new TokenReplacement("s", "5", 0.1),
            new TokenReplacement("g", "9", 0.1)));

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String requestString = URLDecoder.decode(
                req.getRequestURI().replaceFirst("/", ""),
                StandardCharsets.UTF_8.displayName());

        TextProcessor replacer = new TokenReplacerTextProcessor(TOKEN_REPLACEMENTS, new Random());
        TextProcessor flipper = new CaseFlipperTextProcessor(FLIP_PROB, new Random());
        String processedRequestString = flipper.process(replacer.process(requestString));

        resp.getWriter().println(processedRequestString);
    }

}
