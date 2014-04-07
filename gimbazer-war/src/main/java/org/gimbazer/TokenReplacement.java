package org.gimbazer;

public class TokenReplacement {

    private final String from;

    private final String to;

    private final double prob;

    public TokenReplacement(String from, String to, double prob) {
        this.from = from;
        this.to = to;
        this.prob = prob;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public double getProb() {
        return prob;
    }

}
