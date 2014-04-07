package org.gimbazer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TokenReplacerTextProcessor implements TextProcessor {

    private final Collection<TokenReplacement> replacements;

    private final Random random;

    public TokenReplacerTextProcessor(Collection<TokenReplacement> replacements, Random random) {
        this.replacements = replacements;
        this.random = random;
    }

    @Override
    public String process(String text) {
        Map<String, TokenReplacement> replacementMap = new HashMap<>();
        StringBuilder regex = new StringBuilder("(?i)(");
        for (TokenReplacement replacement : replacements) {
            if (replacement.getFrom().length() > 1) {
                regex.append(replacement.getFrom()).append("|");
            }
            replacementMap.put(replacement.getFrom(), replacement);
        }
        regex.append(".)");

        StringBuilder resultBuilder = new StringBuilder();
        Pattern searchPattern = Pattern.compile(regex.toString());
        Matcher matcher = searchPattern.matcher(text);
        while (matcher.find()) {
            String token = matcher.group();
            String lowerCaseToken = token.toLowerCase();
            if (replacementMap.containsKey(lowerCaseToken)) {
                TokenReplacement replacement = replacementMap.get(lowerCaseToken);
                if (random.nextDouble() < replacement.getProb()) {
                    resultBuilder.append(replacement.getTo());
                } else {
                    resultBuilder.append(token);
                }
            } else {
                resultBuilder.append(token);
            }
        }
        return resultBuilder.toString();
    }

}
