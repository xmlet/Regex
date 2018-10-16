import org.junit.Assert;
import org.junit.Test;
import org.xmlet.regex.Regex;

import java.util.List;

public class QuantifiersTest {

    /**
     * <p>The zeroOrMore regex:
     * <p>*
     * <p>Matches the previous element zero or more times.
     * <p>In this example we use the following regex:
     * <p>\d*
     * <p>It will match any sequence of one or more digits.
     **/
    @Test
    public void testZeroOrMoreRegex(){
        String toMatch = "5+7=12";
        Regex regex = new Regex(expr -> expr.matchRegex().anyDigit().zeroOrMore());
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(3, result.size());
        Assert.assertEquals("5", result.get(0));
        Assert.assertEquals("7", result.get(1));
        Assert.assertEquals("12", result.get(2));
    }

    /**
     * <p>The oneOrMore regex:
     * <p>+
     * <p>Matches the previous element one or more times.
     * <p>In this example we use the following regex:
     * <p>hey*
     * <p>It will match any "he" String followed by any number of consecutive 'y' character occurrences.
     **/
    @Test
    public void testOneOrMoreRegex(){
        String toMatch = "hey, heyy, heyyy";
        Regex regex = new Regex(expr -> expr.matchRegex().text("hey").oneOrMore());
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(3, result.size());
        Assert.assertEquals("hey", result.get(0));
        Assert.assertEquals("heyy", result.get(1));
        Assert.assertEquals("heyyy", result.get(2));
    }

    /**
     * <p>The zeroOrOne regex:
     * <p>?
     * <p>In this example we use the following regex:
     * <p>rai?n
     * <p>It will match two words:
     * <p>1: "ran", The case where 'i' happens 0 times.
     * <p>2: "rain", The case where 'i' happens 1 time.
     */
    @Test
    public void testZeroOrOneRegex(){
        String toMatch = "ran, rain, raiin";
        Regex regex = new Regex(expr -> expr.matchRegex().text("rai").zeroOrOne().text("n"));
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(2, result.size());
        Assert.assertEquals("ran", result.get(0));
        Assert.assertEquals("rain", result.get(1));
    }

    /**
     * <p>The matchPreviousNTimes regex:
     * <p>{n}
     * <p>In this example we use the following regex:
     * <p>\d{2}
     * <p>This will match two consecutive digits since it matches any digit, i.e. \d, two times.
     */
    @Test
    public void testMatchPreviousNTimesRegex(){
        String toMatch = "1, 20, 300, 4050";
        Regex regex = new Regex(expr -> expr.matchRegex().anyDigit().matchPreviousNTimes().attrN(2));
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(4, result.size());
        Assert.assertEquals("20", result.get(0));
        Assert.assertEquals("30", result.get(1));
        Assert.assertEquals("40", result.get(2));
        Assert.assertEquals("50", result.get(3));
    }

    /**
     * <p>The matchPreviousAtLeast regex:
     * <p>{n,}
     * <p>In this example we use the following regex:
     * <p>\d{2,}
     * <p>This will match at least two consecutive digits since it matches any digit, i.e. \d, at least 2 times.
     */
    @Test
    public void testMatchPreviousAtLeastRegex(){
        String toMatch = "1, 20, 300, 4050";
        Regex regex = new Regex(expr -> expr.matchRegex().anyDigit().matchPreviousAtLeast().attrNTimes(2));
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(3, result.size());
        Assert.assertEquals("20", result.get(0));
        Assert.assertEquals("300", result.get(1));
        Assert.assertEquals("4050", result.get(2));
    }

    /**
     * <p>The matchPreviousBetween regex:
     * <p>{n,m}
     * <p>In this example we use the following regex:
     * <p>\d{2,3}
     * <p>This will match between two or three consecutive digits.
     */
    @Test
    public void testMatchPreviousBetweenRegex(){
        String toMatch = "1, 20, 300, 4050";
        Regex regex = new Regex(expr -> expr.matchRegex().anyDigit().matchPreviousBetween().n(2).m(3));
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(3, result.size());
        Assert.assertEquals("20", result.get(0));
        Assert.assertEquals("300", result.get(1));
        Assert.assertEquals("405", result.get(2));
    }

    /**
     * <p>The minMatchZeroOrMore regex:
     * <p>*?
     * <p>In this example we use the following regex:
     * <p>\d*?\.\d
     * <p>This will match the any digit zero or more times but as few as possible followed by a '.' character then followed
     * by another \d.
     */
    @Test
    public void testMinMatchZeroOrMoreRegex(){
        String toMatch = ".2, 1.2, 199.0";
        Regex regex = new Regex(expr -> expr.matchRegex().anyDigit().minMatchZeroOrMore().otherChar().attrOneChar(".").anyDigit());
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(3, result.size());
        Assert.assertEquals(".2", result.get(0));
        Assert.assertEquals("1.2", result.get(1));
        Assert.assertEquals("199.0", result.get(2));
    }

    /**
     * <p>The minMatchOneOrMore regex:
     * <p>+?
     * <p>In this example we use the following regex:
     * <p>\d+?\.\d
     * <p>This will match the any digit one or more times but as few as possible followed by a '.' character then followed
     * by another \d.
     */
    @Test
    public void testMinMatchOneOrMoreRegex(){
        String toMatch = ".2, 1.2, 199.0";
        Regex regex = new Regex(expr -> expr.matchRegex().anyDigit().minMatchOneOrMore().otherChar().attrOneChar(".").anyDigit());
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(2, result.size());
        Assert.assertEquals("1.2", result.get(0));
        Assert.assertEquals("199.0", result.get(1));
    }

    /**
     * <p>The minMatchZeroOrOne regex:
     * <p>??
     * <p>In this example we use the following regex:
     * <p>\d??\.\d
     * <p>This will match the any digit zero or one time but as few as possible followed by a '.' character then followed
     * by another \d.
     */
    @Test
    public void testMinMatchZeroOrOneRegex(){
        String toMatch = ".2, 1.2, 199.0";
        Regex regex = new Regex(expr -> expr.matchRegex().anyDigit().minMatchZeroOrOne().otherChar().attrOneChar(".").anyDigit());
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(3, result.size());
        Assert.assertEquals(".2", result.get(0));
        Assert.assertEquals("1.2", result.get(1));
        Assert.assertEquals("9.0", result.get(2));
    }

    /**
     * <p>The minMatchPreviousNTimes regex:
     * <p>{n}?
     * <p>In this example we use the following regex:
     * <p>\d{2}?
     * <p>This will match any digit exactly 2 digits.
     */
    @Test
    public void testMinMatchPreviousNTimesRegex(){
        String toMatch = "1, 20, 300, 4050";
        Regex regex = new Regex(expr -> expr.matchRegex().anyDigit().minMatchPreviousNTimes().attrMinN(2));
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(4, result.size());
        Assert.assertEquals("20", result.get(0));
        Assert.assertEquals("30", result.get(1));
        Assert.assertEquals("40", result.get(2));
        Assert.assertEquals("50", result.get(3));
    }

    /**
     * <p>The minMatchPreviousAtLeast regex:
     * <p>{n,}?
     * <p>In this example we use the following regex:
     * <p>\d{2,}?
     * <p>This will match any digit at least 2 digits but as few as possible.
     */
    @Test
    public void testMinMatchPreviousAtLeastRegex(){
        String toMatch = "1, 20, 300, 4050";
        Regex regex = new Regex(expr -> expr.matchRegex().anyDigit().minMatchPreviousAtLeast().attrMinNTimes(2));
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(4, result.size());
        Assert.assertEquals("20", result.get(0));
        Assert.assertEquals("30", result.get(1));
        Assert.assertEquals("40", result.get(2));
        Assert.assertEquals("50", result.get(3));
    }

    /**
     * <p>The minMatchPreviousBetween regex:
     * <p>{n,m}?
     * <p>In this example we use the following regex:
     * <p>\d{2,3}?
     * <p>This will match any digit between 2 and 3 digits but as few as possible.
     */
    @Test
    public void testMinMatchPreviousBetweenRegex(){
        String toMatch = "1, 20, 300, 4050";
        Regex regex = new Regex(expr -> expr.matchRegex().anyDigit().minMatchPreviousBetween().n(2).minM(3));
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(4, result.size());
        Assert.assertEquals("20", result.get(0));
        Assert.assertEquals("30", result.get(1));
        Assert.assertEquals("40", result.get(2));
        Assert.assertEquals("50", result.get(3));
    }
}
