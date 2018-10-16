package org.xmlet.regex;

import org.xmlet.regex.visitor.RegexVisitor;
import org.xmlet.regexapi.Element;
import org.xmlet.regexapi.ElementVisitor;
import org.xmlet.regexapi.RegexMatchExpression;
import org.xmlet.regexapi.RegexSubstitutionExpression;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("WeakerAccess")
public class Regex implements Element<Regex, Element> {

    private final String matchRegex;
    private final String substitutionRegex;
    private final RegexVisitor matchVisitor;
    private final RegexVisitor substitutionVisitor;

    public Regex(Consumer<Regex> consumer){
        matchVisitor = new RegexVisitor();
        substitutionVisitor = new RegexVisitor();
        consumer.accept(this);

        matchRegex = matchVisitor.getRegex();
        substitutionRegex = substitutionVisitor.getRegex();
    }

    public static String quickExpr(Consumer<RegexMatchExpression<Regex>> expressionConsumer){
        return new Regex(regex -> expressionConsumer.accept(regex.matchRegex())).getMatchRegex();
    }

    public RegexMatchExpression<Regex> matchRegex(){
        return new RegexMatchExpression<>(matchVisitor);
    }

    public RegexSubstitutionExpression<Regex> substitutionRegex(){
        return new RegexSubstitutionExpression<>(substitutionVisitor);
    }

    /**
     * Applies the {@link Regex#matchRegex} to the received String {@code toReplace} and performs the replace using the
     * {@link Regex#substitutionRegex}.
     * @param toReplace The received String.
     * @return A list of substitution results.
     */
    public List<String> replace(String toReplace){
        return replace(toReplace,  false);
    }

    /**
     * Applies the {@link Regex#matchRegex} to the received String {@code toReplace} and performs the replace using the
     * {@link Regex#substitutionRegex}.
     * @param toReplace The received String.
     * @param printRegex Informs if the both {@link Regex#matchRegex} and {@link Regex#substitutionRegex} should be printed.
     * @return A list of substitution results.
     */
    public List<String> replace(String toReplace, boolean printRegex){
        return replace(toReplace,  printRegex, true);
    }

    /**
     * Applies the {@link Regex#matchRegex} to the received String {@code toReplace} and performs the replace using the
     * {@link Regex#substitutionRegex}.
     * @param toReplace The received String.
     * @param printRegex Informs if the both {@link Regex#matchRegex} and {@link Regex#substitutionRegex} should be printed.
     * @param ignoreEmptyStrings Informs if the method should ignore empty Strings.
     * @return A list of substitution results.
     */
    public List<String> replace(String toReplace, boolean printRegex, boolean ignoreEmptyStrings){
        if (printRegex){
            System.out.println("Match regex: " + matchRegex);
        }

        List<String> res = new ArrayList<>();
        Matcher matcher = Pattern.compile(this.matchRegex).matcher(toReplace);

        while(matcher.find()){
            String match = matcher.group();

            if (ignoreEmptyStrings && match.length() != 0){
                res.add(match.replaceAll(matchRegex, substitutionRegex));
            }
        }

        return res;
    }

    /**
     * Applies the {@link Regex#matchRegex} to the received String {@code toReplace}.
     * @param toMatch The received String.
     * @return A list of match results.
     */
    public List<String> match(String toMatch){
        return match(toMatch, false);
    }

    /**
     * Applies the {@link Regex#matchRegex} to the received String {@code toReplace}.
     * @param toMatch The received String.
     * @param printMatchRegex Informs if {@link Regex#matchRegex} should be printed.
     * @return A list of match results.
     */
    public List<String> match(String toMatch, boolean printMatchRegex){
        return match(toMatch, printMatchRegex, true);
    }

    /**
     * Applies the {@link Regex#matchRegex} to the received String {@code toReplace}.
     * @param toMatch The received String.
     * @param printMatchRegex Informs if {@link Regex#matchRegex} should be printed.
     * @param ignoreEmptyStrings Informs if the method should ignore empty Strings.
     * @return A list of match results.
     */
    public List<String> match(String toMatch, boolean printMatchRegex, boolean ignoreEmptyStrings){
        if (printMatchRegex){
            System.out.println("Match regex: " + matchRegex);
        }

        List<String> res = new ArrayList<>();
        Matcher matcher = Pattern.compile(this.matchRegex).matcher(toMatch);

        while(matcher.find()){
            String match = matcher.group();

            if (ignoreEmptyStrings && match.length() != 0){
                res.add(match);
            }
        }

        return res;
    }

    /**
     * Method for conditional regex. Applies the {@link Regex#matchRegex} to the received String {@code toReplace}.
     * @param toMatch The received String.
     * @return A list of match results.
     */
    public List<String> conditionalMatch(String toMatch) {
        return conditionalMatch(toMatch, false);
    }

    /**
     * Method for conditional regex. Applies the {@link Regex#matchRegex} to the received String {@code toReplace}.
     * @param toMatch The received String.
     * @param printMatchRegex Informs if {@link Regex#matchRegex} should be printed.
     * @return A list of match results.
     */
    public List<String> conditionalMatch(String toMatch, boolean printMatchRegex) {
        return conditionalMatch(toMatch, printMatchRegex, true);
    }

    /**
     * Method for conditional regex. Applies the {@link Regex#matchRegex} to the received String {@code toReplace}.
     * @param toMatch The received String.
     * @param printMatchRegex Informs if {@link Regex#matchRegex} should be printed.
     * @param ignoreEmptyStrings Informs if the method should ignore empty Strings.
     * @return A list of match results.
     */
    public List<String> conditionalMatch(String toMatch, boolean printMatchRegex, boolean ignoreEmptyStrings) {
        if (printMatchRegex){
            System.out.println("Match regex: " + matchRegex);
        }

        List<String> res = new ArrayList<>();
        jregex.Matcher matcher = new jregex.Pattern(this.matchRegex).matcher(toMatch);

        while(matcher.find()){
            String match = matcher.toString();

            if (ignoreEmptyStrings && match.length() != 0){
                res.add(match);
            }
        }

        return res;
    }

    public String getMatchRegex() {
        return matchRegex;
    }

    @Override
    public Regex self() {
        return this;
    }

    @Override
    public ElementVisitor getVisitor() {
        return matchVisitor;
    }

    @Override
    public String getName() {
        return "matchRegex";
    }

    @Override
    public Element __() {
        return null;
    }

    @Override
    public Element getParent() {
        return null;
    }
}
