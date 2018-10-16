import org.junit.Assert;
import org.junit.Test;
import org.xmlet.regex.Regex;

import java.util.List;

public class AnchorTest {

    /**
     * <p>The atBeginning regex:
     * <p>^
     * <p>In this case we execute the following regex:
     * <p>^\d{3}
     * <p>This should match any three digits at the beginning of a String. As a result we can see that it obtains "901" as a
     * result and not "333" since it's not at the beginning of the String.
     */
    @Test
    public void testAtBeginningRegex(){
        String toMatch = "901-333-";
        Regex regex = new Regex(expr -> expr.matchRegex().atBeginning().anyDigit().matchPreviousNTimes().attrN(3));
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals("901", result.get(0));
    }

    /**
     * <p>The atEnd regex:
     * <p>$
     * <p>In this case we execute the following regex:
     * <p>-\d{3}$
     * <p>This should match any '-' followed by three digits at the end of a String. As a result we can see that it obtains
     * "-333" as a result and not "-901" since it's not at the end of the String.
     */
    @Test
    public void testAtEndRegex(){
        String toMatch = "-901-333";
        Regex regex = new Regex(expr -> expr.matchRegex().text("-").anyDigit().matchPreviousNTimes().attrN(3).atEnd());
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals("-333", result.get(0));
    }

    /**
     * <p>The atStringBeginning regex:
     * <p>\A
     * <p>In this case we execute the following regex:
     * <p>\A\d{3}
     * <p>This one is identical to the atBeginning regex (^), it matches the expression only if it occurs at the start of the
     * String. As a result we can see that it obtains "901" as a result and not "333" since it's not at the beginning of
     * the String.
     */
    @Test
    public void testAtStringBeginningRegex(){
        String toMatch = "901-333-";
        Regex regex = new Regex(expr -> expr.matchRegex().atStringBeginning().anyDigit().matchPreviousNTimes().attrN(3));
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals("901", result.get(0));
    }

    /**
     * <p>The atStringEndOrNewLine regex:
     * <p>\Z
     * <p>In this case we execute the following regex:
     * <p>-\d{3}\Z
     * <p>This should match any '-' character followed by three digits either before the end of the String or before a new line character
     * , i.e. '\n'. As a result we can see that with the two distinct variants, a String ending or the use of the new line
     * character the result is the same with the same regex.
     */
    @Test
    public void testAtStringEndOrNewlineRegex(){
        String toMatch1 = "-901-333";
        String toMatch2 = "-901-333\n";

        Regex regex = new Regex(expr -> expr.matchRegex().text("-").anyDigit().matchPreviousNTimes().attrN(3).atStringEndOrNewline());

        List<String> result1 = regex.match(toMatch1);
        List<String> result2 = regex.match(toMatch2);

        Assert.assertEquals(1, result1.size());
        Assert.assertEquals("-333", result1.get(0));

        Assert.assertEquals(1, result2.size());
        Assert.assertEquals("-333", result2.get(0));
    }

    /**
     * <p>The atStringEnd regex:
     * <p>\z
     * <p>In this case we execute the following regex:
     * <p>-\d{3}\z
     * <p>This should match any '-' character followed by three digits before the end of the line. Contrary to the
     * atStringOrNewLine regex, i.e. "\z", it doesn't match results followed by a new line character, i.e. '\n'.
     * This behaviour is confirmed by the usage of two distinct variants of the match String.
     */
    @Test
    public void testAtStringEndRegex(){
        String toMatch1 = "-901-333";
        String toMatch2 = "-901-333\n";

        Regex regex = new Regex(expr -> expr.matchRegex().text("-").anyDigit().matchPreviousNTimes().attrN(3).__().atStringEnd());

        List<String> result1 = regex.match(toMatch1);
        List<String> result2 = regex.match(toMatch2);

        Assert.assertEquals(1, result1.size());
        Assert.assertEquals("-333", result1.get(0));

        Assert.assertEquals(0, result2.size());
    }

    /**
     * <p>The consecutiveMatch regex:
     * <p>\G
     * <p>In this case we execute the following regex:
     * <p>\G\(\d\)
     * <p>This should match any consecutive match of occurrences of the '(' character followed by any digit which is then
     * followed by a ')' character.
     * <p>In the test case we use the String "(1)(3)(5)[7](9)" as input. In this case we have four occurrences of matches
     * to the \(\d\) pattern
     * <p>1: (1)
     * <p>2: (3)
     * <p>3: (5)
     * <p>4: (9)
     * <p>But only the first three, i.e. (1), (3) and (5), happen consecutively, so only those are matched by the used pattern.
     */
    @Test
    public void testConsecutiveMatchRegex(){
        String toMatch = "(1)(3)(5)[7](9)";
        Regex regex = new Regex(expr -> expr.matchRegex().consecutiveMatch().otherChar().attrOneChar("(").anyDigit().otherChar().attrOneChar(")"));
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(3, result.size());
        Assert.assertEquals("(1)", result.get(0));
        Assert.assertEquals("(3)", result.get(1));
        Assert.assertEquals("(5)", result.get(2));
    }

    /**
     * <p>The boundary regex:
     * <p>\b
     * <p>In this case we execute two distinct regex:
     * <p>regex1: \b\d\b
     * <p>regex2: \b\d
     * <p>String used: 5+7=12
     * <p>Boundary definition: The \b anchor specifies that the match must occur on a boundary between a word character
     * (the \w language element) and a non-word character (the \W language element).
     * <p>To understand how boundaries work we choose those two examples. In the first case the digit should be between
     * boundaries on both sides. In this case we have two matches, '5' and '7' because both of them are surrounded by
     * boundaries. On the second case we remove the requirement of the right boundary, resulting in having one more
     * result, '1', due to '2' not being boundary character.
     */
    @Test
    public void testBoundariesRegex(){
        String toMatch = "5+7=12";

        Regex regex1 = new Regex(expr -> expr.matchRegex().boundary().anyDigit().boundary());
        Regex regex2 = new Regex(expr -> expr.matchRegex().boundary().anyDigit());

        List<String> result1 = regex1.match(toMatch);
        List<String> result2 = regex2.match(toMatch);

        Assert.assertEquals(2, result1.size());
        Assert.assertEquals("5", result1.get(0));
        Assert.assertEquals("7", result1.get(1));

        Assert.assertEquals(3, result2.size());
        Assert.assertEquals("5", result2.get(0));
        Assert.assertEquals("7", result2.get(1));
        Assert.assertEquals("1", result2.get(2));
    }

    /**
     * <p>The noBoundary regex:
     * <p>\B
     * <p>In this case we have two distinct regex:
     * <p>regex1: \B\d\B
     * <p>regex2: \b\d
     * <p>String used: 5+7=12
     * <p>The noBoundary regex is the opposite of the boundary regex. In the first case we have no results since
     * all digits have at least one boundary next to them. In the second case we removed the noBoundary premise to the
     * right of the digit and obtained a match, '2', since '2' already had a noBoundary character to the left, '1' but
     * was still limited by the boundary that had to the right, the end of the String.
     */
    @Test
    public void testNonBoundariesRegex(){
        String toMatch = "5+7=12";

        Regex regex1 = new Regex(expr -> expr.matchRegex().noBoundary().anyDigit().noBoundary());
        Regex regex2 = new Regex(expr -> expr.matchRegex().noBoundary().anyDigit());

        List<String> result1 = regex1.match(toMatch);
        List<String> result2 = regex2.match(toMatch);

        Assert.assertEquals(0, result1.size());

        Assert.assertEquals(1, result2.size());
        Assert.assertEquals("2", result2.get(0));
    }
}
