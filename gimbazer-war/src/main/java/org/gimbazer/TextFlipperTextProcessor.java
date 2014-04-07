package org.gimbazer;

import java.util.Random;

public class TextFlipperTextProcessor implements TextProcessor {

    private final double flipProbability;

    private final Random random;

    public TextFlipperTextProcessor(double flipProbability, Random random) {
        this.flipProbability = flipProbability;
        this.random = random;
    }

    @Override
    public String process(String text) {
        StringBuilder responseBuilder = new StringBuilder();
        for (char character : text.toCharArray()) {
            if (Character.isLetter(character) && random.nextDouble() < flipProbability) {
                if (Character.isUpperCase(character)) {
                    responseBuilder.append(Character.toLowerCase(character));
                } else {
                    responseBuilder.append(Character.toUpperCase(character));
                }
            } else {
                responseBuilder.append(character);
            }
        }
        return responseBuilder.toString();
    }

}
