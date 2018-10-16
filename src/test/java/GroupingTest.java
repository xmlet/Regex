import org.junit.Assert;
import org.junit.Test;
import org.xmlet.regexapi.EnumOptionOptionType;
import org.xmlet.regexapi.MatchingOperationsAll1;
import org.xmlet.regex.Regex;

import java.util.List;

public class GroupingTest {

    /**
     * <p>The subExpression regex:
     * <p>(subExpr)
     * <p>In this example we use the following regex:
     * <p>(\w)\1
     * <p>We define a group using the "(\w)" and use a backReference after. This will match the \w two times. Using a
     * String "deep" the result should be "ee".
     */
    @Test
    public void testSubExpressionRegex(){
        String toMatch = "deep";
        Regex regex = new Regex(
                expr -> expr.matchRegex()
                        .subExpression().attrSubExpr(Regex.quickExpr(MatchingOperationsAll1::anyLetterOrDigit))
                        .otherChar().text("1"));
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals("ee", result.get(0));
    }

    /**
     * <p>The namedSubExpression regex:
     * <p>{@code (?<name>subExpr)}
     * <p>In this example we use the following regex:
     * <p>{@code (?<double>\w)\k<double>}
     * <p>This regex is divided in two aspects, first we define the namedSubExpression, i.e. {@code (?<double>\w)}, and then use a
     * namedBackReference. This will result in the pattern, \w, being matched 2 consecutive times. Using a String
     * "deep" the result should be "ee".
     */
    @Test
    public void testNamedSubExpressionRegex(){
        String toMatch = "deep";
        Regex regex = new Regex(
                expr -> expr.matchRegex()
                        .namedSubExpression()
                            .nameFirst("double")
                            .expression(Regex.quickExpr(MatchingOperationsAll1::anyLetterOrDigit)).__()
                        .namedBackReference().attrName("double"));
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals("ee", result.get(0));
    }

    /**
     * <p>The nonCapturingGroup regex:
     * <p>(?:subExpr)
     * <p>In this example we use the following regex:
     * <p>Write(?:Line)
     * <p>This will match any String containing "Write" or "WriteLine" since the "Line" is optional since it's defined inside
     * a non capturing group.
     */
    @Test
    public void testNonCapturingGroupRegex(){
        String toMatch1 = "Console.WriteLine()";
        String toMatch2 = "Console.Write(value)";

        Regex regex = new Regex(expr -> expr.matchRegex().text("Write").nonCapturingGroup().attrSubExpr("Line").zeroOrOne());

        List<String> result1 = regex.match(toMatch1);
        List<String> result2 = regex.match(toMatch2);

        Assert.assertEquals(1, result1.size());
        Assert.assertEquals("WriteLine", result1.get(0));

        Assert.assertEquals(1, result2.size());
        Assert.assertEquals("Write", result2.get(0));
    }

    /**
     * <p>The expressionWithOptions regex:
     * <p>(?imnsx-imnsx:subExpr)
     * <p>In this example we use the following regex:
     * <p>A\d{2}(?i:\w+)\b
     * <p>This expression will match any 'A' character followed by two digits then followed by a case insensitive, i.e. the
     * 'i' option, group of letters or digits. Using the String "A12xl A12XL a12xl" we will have two matches:
     * <p>1: A12xl
     * <p>2: A12XL
     */
    @Test
    public void testExpressionsWithOptionsRegex(){
        String toMatch = "A12xl A12XL a12xl";
        Regex regex = new Regex(
                expr -> expr.matchRegex()
                        .text("A")
                        .anyDigit().matchPreviousNTimes().attrN(2)
                        .expressionWithOptions()
                            .activateOption().attrOption(EnumOptionOptionType.I).__()
                            .attrOptionsSubExpr(Regex.quickExpr(subExpr -> subExpr.anyLetterOrDigit().oneOrMore()))
                        .boundary());
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(2, result.size());
        Assert.assertEquals("A12xl", result.get(0));
        Assert.assertEquals("A12XL", result.get(1));
    }

    /**
     * <p>The positiveLookAhead regex:
     * <p>(?=subExpr)
     * <p>In this example we use the following regex:
     * <p>\w+(?=\.)
     * <p>This matches any group of letters or digits that are followed by a '.' character.
     */
    @Test
    public void testPositiveLookAheadRegex(){
        String toMatch = "He is. The dog ran. The sun is out.";
        Regex regex = new Regex(expr -> expr.matchRegex().anyLetterOrDigit().zeroOrMore().positiveLookAhead()
                                            .attrSubExpr(Regex.quickExpr(subExpr -> subExpr.otherChar().attrOneChar("."))));
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(3, result.size());
        Assert.assertEquals("is", result.get(0));
        Assert.assertEquals("ran", result.get(1));
        Assert.assertEquals("out", result.get(2));
    }

    /**
     * <p>The negativeLookAhead regex:
     * <p>(?!subExpr)
     * <p>In this example we use the following regex:
     * <p>\b(?!un)\w+\b
     * <p>This matches group of letters or digits that don't have the "un" prefix that exist between boundaries. Using the
     * String "unsure sure unity used" we obtain 2 results:
     * <p>1: sure - from sure
     * <p>2: used - from used
     * <p>Both unsure and unity aren't eligible for results since they are prefix with "un".
     */
    @Test
    public void testNegativeLookAheadRegex(){
        String toMatch = "unsure sure unity used";
        Regex regex = new Regex(expr -> expr.matchRegex().boundary().negativeLookAhead().attrSubExpr("un").anyLetterOrDigit().zeroOrMore().boundary());
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(2, result.size());
        Assert.assertEquals("sure", result.get(0));
        Assert.assertEquals("used", result.get(1));
    }

    /**
     * <p>The positiveLookBehind regex:
     * <p>{@code (?<=subExpr)}
     * <p>In this example we use the following regex:
     * <p>{@code (?<=19)\d{2}\b}
     * <p>This matches any two numbers that are prefixed with "19". Using the String "1851 1999 1950 1905 2003" the results
     * are:
     * <p>1: 99 - from 1999
     * <p>2: 50 - from 1950
     * <p>3: 05 - from 1905
     */
    @Test
    public void testPositiveLookBehindRegex(){
        String toMatch = "1851 1999 1950 1905 2003";
        Regex regex = new Regex(expr -> expr.matchRegex().positiveLookBehind().attrSubExpr("19").anyDigit().matchPreviousNTimes().attrN(2).boundary());
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(3, result.size());
        Assert.assertEquals("99", result.get(0));
        Assert.assertEquals("50", result.get(1));
        Assert.assertEquals("05", result.get(2));
    }

    /**
     * <p>The negativeLookBehind regex:
     * <p>{@code (?<!subExpr)}
     * <p>In this example we use the following regex:
     * <p>{@code (?<!19)\d{2}\b}
     * <p>This matches any two numbers that aren't prefixed with "19". Using the String "1851 1999 1950 1905 2003" the results
     * are:
     * <p>1: 51 - from 1851
     * <p>2: 03 - from 2003
     */
    @Test
    public void testNegativeLookBehindRegex(){
        String toMatch = "1851 1999 1950 1905 2003";
        Regex regex = new Regex(expr -> expr.matchRegex().negativeLookBehind().attrSubExpr("19").anyDigit().matchPreviousNTimes().attrN(2).boundary());
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(2, result.size());
        Assert.assertEquals("51", result.get(0));
        Assert.assertEquals("03", result.get(1));
    }

    /**
     * <p>The nonBackTracking regex:
     * <p>{@code (?>subExpr)}
     * <p>In this example we use the following regex:
     * <p>{@code [13579](?>A+B+)}
     * <p>This will match any '1', '3', '5', '7', '9' character followed by multiple 'A' characters then followed
     * by multiple 'B' characters. Using the String "1ABB 3ABBC 5AB 5AC" the results should be:
     * <p>1: 1ABB
     * <p>2: 3ABB
     * <p>3: 5AB
     */
    @Test
    public void testNonBacktrackingRegex(){
        String toMatch = "1ABB 3ABBC 5AB 5AC";
        Regex regex = new Regex(expr -> expr.matchRegex().characterGroup().attrCharGroup("13579").nonBackTracking().attrSubExpr("A+B+"));
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(3, result.size());
        Assert.assertEquals("1ABB", result.get(0));
        Assert.assertEquals("3ABB", result.get(1));
        Assert.assertEquals("5AB", result.get(2));
    }
}
