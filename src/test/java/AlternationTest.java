import org.junit.Assert;
import org.junit.Test;
import org.xmlet.regex.Regex;

import java.util.List;

import static org.xmlet.regex.Regex.quickExpr;

public class AlternationTest {

    /**
     * <p>The and expression regex:
     * <p>{@code &&}
     * <p>In this case we execute the following regex:
     * <p>{@code [a-z&&[aeiou]]}
     * <p>With this regex it is expected to obtain all the vowels of a String.
     */
    @Test
    public void testAndRegex(){
        String toMatch = "This is a test.";
        Regex regex = new Regex(
                expr -> expr.matchRegex()
                    .characterGroup().attrCharGroup(quickExpr(subExpr -> subExpr.text("a-z").and().characterGroup().attrCharGroup("aeiou"))));
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(4, result.size());
        Assert.assertEquals("i", result.get(0));
        Assert.assertEquals("i", result.get(1));
        Assert.assertEquals("a", result.get(2));
        Assert.assertEquals("e", result.get(3));
    }

    /**
     * <p>The or expression regex:
     * <p>{@code |}
     * <p>In this case we execute the following regex:
     * <p>{@code \d|\s}
     * <p>With this regex it is expected to obtain all the digits and all the white spaces.
     */
    @Test
    public void testOrRegex() {
        String toMatch = "91 - 33 = 58 units.";
        Regex regex = new Regex(expr -> expr.matchRegex().anyDigit().or().anyWhiteSpace());
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(11, result.size());
        Assert.assertEquals("9", result.get(0));
        Assert.assertEquals("1", result.get(1));
        Assert.assertEquals(" ", result.get(2));
        Assert.assertEquals(" ", result.get(3));
        Assert.assertEquals("3", result.get(4));
        Assert.assertEquals("3", result.get(5));
        Assert.assertEquals(" ", result.get(6));
        Assert.assertEquals(" ", result.get(7));
        Assert.assertEquals("5", result.get(8));
        Assert.assertEquals("8", result.get(9));
        Assert.assertEquals(" ", result.get(10));
    }

    /**
     * <p>The conditional expression regex:
     * <p>{@code (?(expression)then|else)}
     * <p>It matches the "expression" and if the expression is matched it applies the "then" expression and if the "expression"
     * isn't matched it applies the "else" expression.
     * <p>In the test example we have the following expression:
     * <p>{@code (?(A)A\d{2}\b|\b\d{3}\b)}
     * <p>expression: {@code A}
     * <p>then: {@code A\d{2}\b}
     * <p>else: {@code \b\d{3}\b}
     * <p>This means that this expression will either match an 'A' followed by two digits and a boundary or three digits
     * between boundaries.
     */
    @Test
    public void testIfMatchRegex() {
        String toMatch = "A10 C103 910";
        Regex regex = new Regex(
                expr -> expr.matchRegex()
                            .ifMatch()
                                .ifMatchSubExpression(quickExpr(subExpr -> subExpr.text("A")))
                                .thenExpression(quickExpr(subExpr -> subExpr.text("A").anyDigit().matchPreviousNTimes().attrN(2).boundary()))
                                .elseExpression(quickExpr(subExpr -> subExpr.boundary().anyDigit().matchPreviousNTimes().attrN(3).boundary())));
        List<String> result = regex.conditionalMatch(toMatch);

        Assert.assertEquals(2, result.size());
        Assert.assertEquals("A10", result.get(0));
        Assert.assertEquals("910", result.get(1));
    }

    /**
     * <p>The named conditional expression regex:
     * <p>{@code (?(name)then|else)}
     * <p>It matches the named capturing group with the name "name", applying the "then" expression if a match occurs or
     * <p>the "else" expression if it doesn't.
     * <p>In this test example we have the following expression:
     * <p>{@code (?<quoted>")?(?(quoted).+?"|\S+\s)}
     * <p>Named capturing group: {@code (?<quoted>")?}
     * <p>name: {@code quoted}
     * <p>then: {@code .+?"}
     * <p>else: {@code \S+\s}
     * <p>There are two distinct aspects, first we define the named capturing group with {@code (?<quoted>")?} and then we
     * use the named conditional expression with {@code (?(quoted).+?"|\S+\s)}.
     * In the test example the expression asserts if the "quoted" capturing group occurs and if it does it matches
     * any String until a '"' char, i.e. the "then" expression, otherwise it matches the remaining text, i.e. the
     * "else" expression.
     */
    @Test
    public void testIfGroupMatchRegex() {
        String toMatch = "Dogs.jpg \"Yiska playing.jpg\"";
        Regex regex = new Regex(
                expr -> expr.matchRegex()
                        .conditionalNamedExpression().condNameFirst("quoted").condExpressionSecond("\"").__()
                        .zeroOrOne()
                        .ifGroupMatch()
                            .groupName("quoted")
                            .thenExpression(quickExpr(subExpr -> subExpr.anyChar().minMatchOneOrMore().text("\"")))
                            .elseExpression(quickExpr(subExpr -> subExpr.anyNonWhiteSpace().oneOrMore().anyWhiteSpace())));
        List<String> result = regex.conditionalMatch(toMatch);

        Assert.assertEquals(2, result.size());
        Assert.assertEquals("Dogs.jpg ", result.get(0));
        Assert.assertEquals("\"Yiska playing.jpg\"", result.get(1));
    }
}
