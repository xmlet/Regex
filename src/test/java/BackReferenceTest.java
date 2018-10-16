import org.junit.Assert;
import org.junit.Test;
import org.xmlet.regexapi.MatchingOperationsAll1;
import org.xmlet.regex.Regex;

import java.util.List;

public class BackReferenceTest {

    /**
     * <p>The backReference regex:
     * <p>\number
     * <p>In this case we use the following regex:
     * <p>(\w)\1
     * <p>In this test case the backReference matches the expression of the capturing group again. So, with the String "seek"
     * the capturing group, i.e. (\w), matches all the characters but using the backReference matches the same pattern,
     * obtaining the result "ee", because it's the only part of the String that is consecutively equal.
     */
    @Test
    public void testBackReferenceRegex(){
        String toMatch = "seek";
        Regex regex = new Regex(
                expr -> expr.matchRegex()
                        .subExpression().attrSubExpr(Regex.quickExpr(MatchingOperationsAll1::anyLetterOrDigit)).__()
                        .otherChar().text("1"));
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals("ee", result.get(0));
    }

    /**
     * <p>The namedBackReference regex:
     * <p>{@code \k<name>}
     * <p>In this case we use the following regex:
     * <p>{@code (?<char>\w)\k<char>}
     * <p>There are two parts on this regex, first we define a named capturing group, {@code (?<char>\w)}, and then we define the
     * <p>namedBackReference, {@code \k<char>}.
     * <p>This is very similar to the backReference regex. The named capturing group matches all the characters but using
     * the namedBackReference the same pattern is matched, obtaining a single result "ee" using the String "seek", because
     * it's the only part of the String that is consecutively equal.
     */
    @Test
    public void testNamedBackReferenceRegex(){
        String toMatch = "seek";
        Regex regex = new Regex(
                expr -> expr.matchRegex()
                        .namedSubExpression().nameFirst("char").expression(Regex.quickExpr(MatchingOperationsAll1::anyLetterOrDigit)).__()
                        .namedBackReference().attrName("char"));
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals("ee", result.get(0));
    }
}
