import org.junit.Assert;
import org.junit.Test;
import org.xmlet.regex.Regex;

import java.util.List;

public class CharacterClassesTest {

    /**
     * <p>The characterGroup regex:
     * <p>[characterGroup]
     * <p>In this example we use the following regex:
     * <p>[ae]
     * <p>This will match any 'a' or 'e' characters in the received String.
     */
    @Test
    public void testCharacterGroupRegex(){
        String toMatch = "gray";
        Regex regex = new Regex(expr -> expr.matchRegex().characterGroup().attrCharGroup("ae"));
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals("a", result.get(0));
    }

    /**
     * <p>The characterGroup regex:
     * <p>[^characterGroup]
     * <p>In this example we use the following regex:
     * <p>[^ae]
     * <p>This will match any character other than 'a' or 'e' in the received String.
     */
    @Test
    public void testCharacterNotGroupRegex(){
        String toMatch = "gray";
        Regex regex = new Regex(expr -> expr.matchRegex().characterNotGroup().attrCharGroup("ae"));
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(3, result.size());
        Assert.assertEquals("g", result.get(0));
        Assert.assertEquals("r", result.get(1));
        Assert.assertEquals("y", result.get(2));
    }

    /**
     * <p>The characterGroup regex:
     * <p>[first-last]
     * <p>In this example we use the following regex:
     * <p>[a-d|r-z]
     * <p>This will match any characters between 'a' and 'd' or between 'r' and {@code <} in the receiving String.
     */
    @Test
    public void testFromFirstUntilLastRegex(){
        String toMatch = "abcd rXsXtXz";
        Regex regex = new Regex(expr -> expr.matchRegex().fromFirstUntilLast().attrFirst("a").attrLast("d")
                                                    .or().fromFirstUntilLast().attrFirst("r").attrLast("z"));
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(8, result.size());
        Assert.assertEquals("a", result.get(0));
        Assert.assertEquals("b", result.get(1));
        Assert.assertEquals("c", result.get(2));
        Assert.assertEquals("d", result.get(3));
        Assert.assertEquals("r", result.get(4));
        Assert.assertEquals("s", result.get(5));
        Assert.assertEquals("t", result.get(6));
        Assert.assertEquals("z", result.get(7));
    }

    /**
     * <p>The anyChar regex:
     * <p>.
     * <p>This will match any character, except line terminators.
     */
    @Test
    public void testAnyCharRegex(){
        String toMatch = "Short";
        Regex regex = new Regex(expr -> expr.matchRegex().anyChar());
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(5, result.size());
        Assert.assertEquals("S", result.get(0));
        Assert.assertEquals("h", result.get(1));
        Assert.assertEquals("o", result.get(2));
        Assert.assertEquals("r", result.get(3));
        Assert.assertEquals("t", result.get(4));
    }

    /**
     * <p>The unicodeBlock regex:
     * <p>\p{X}
     * <p>In this example we use the following regex:
     * <p>\p{Lu}
     * <p>This matches all the characters that belong to the received unicode block. In this example it will match all
     * <p>uppercase letters, since the used block is "Lu".
     */
    @Test
    public void testUnicodeBlockRegex(){
        String toMatch = "Luis Duarte";
        Regex regex = new Regex(expr -> expr.matchRegex().unicodeBlock().attrBlock("Lu"));
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(2, result.size());
        Assert.assertEquals("L", result.get(0));
        Assert.assertEquals("D", result.get(1));
    }

    /**
     * <p>The unicodeNotBlock regex:
     * <p>\P{X}
     * <p>In this example we use the following regex:
     * <p>\P{Lu}
     * <p>This matches all the characters that don't belong to the received unicode block. In this example it will match all
     * characters that aren't uppercase letters letters, since the used block is "Lu".
     */
    @Test
    public void testUnicodeNotBlockRegex(){
        String toMatch = "Luis Duarte";
        Regex regex = new Regex(expr -> expr.matchRegex().unicodeNotBlock().attrBlock("Lu"));
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(9, result.size());
        Assert.assertEquals("u", result.get(0));
        Assert.assertEquals("i", result.get(1));
        Assert.assertEquals("s", result.get(2));
        Assert.assertEquals(" ", result.get(3));
        Assert.assertEquals("u", result.get(4));
        Assert.assertEquals("a", result.get(5));
        Assert.assertEquals("r", result.get(6));
        Assert.assertEquals("t", result.get(7));
        Assert.assertEquals("e", result.get(8));
    }

    /**
     * <p>The anyLetterOrDigit regex:
     * <p>\w
     * <p>Matches any letter or digit.
     */
    @Test
    public void testAnyLetterOrDigitRegex(){
        String toMatch = "Id A1.3";
        Regex regex = new Regex(expr -> expr.matchRegex().anyLetterOrDigit());
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(5, result.size());
        Assert.assertEquals("I", result.get(0));
        Assert.assertEquals("d", result.get(1));
        Assert.assertEquals("A", result.get(2));
        Assert.assertEquals("1", result.get(3));
        Assert.assertEquals("3", result.get(4));
    }

    /**
     * <p>The anyNonLetterOrDigit regex:
     * <p>\W
     * <p>Matches anything other than a letter or a digit.
     */
    @Test
    public void testAnyNonLetterOrDigitRegex(){
        String toMatch = "Id A1.3";
        Regex regex = new Regex(expr -> expr.matchRegex().anyNonLetterOrDigit());
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(2, result.size());
        Assert.assertEquals(" ", result.get(0));
        Assert.assertEquals(".", result.get(1));
    }

    /**
     * <p>The anyWhiteSpace regex:
     * <p>\s
     * <p>Matches any white space character.
     */
    @Test
    public void testAnyWhiteSpaceRegex(){
        String toMatch = "Luis Duarte";
        Regex regex = new Regex(expr -> expr.matchRegex().anyLetterOrDigit().anyWhiteSpace());
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals("s ", result.get(0));
    }

    /**
     * <p>The anyNonWhiteSpace regex:
     * <p>\S
     * <p>Matches any non white space character.
     */
    @Test
    public void testAnyNonWhiteSpaceRegex(){
        String toMatch = "Luis Duarte";
        Regex regex = new Regex(expr -> expr.matchRegex().anyWhiteSpace().anyNonWhiteSpace());
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals(" D", result.get(0));
    }

    /**
     * <p>The anyDigit regex:
     * <p>\d
     * <p>Matches any digit.
     */
    @Test
    public void testAnyDigitRegex(){
        String toMatch = "4 = IV";
        Regex regex = new Regex(expr -> expr.matchRegex().anyDigit());
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals("4", result.get(0));
    }

    /**
     * <p>The anyNonDigit regex:
     * <p>\d
     * <p>Matches any non digit.
     */
    @Test
    public void testAnyNonDigitRegex(){
        String toMatch = "4 = IV";
        Regex regex = new Regex(expr -> expr.matchRegex().anyNonDigit());
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(5, result.size());
        Assert.assertEquals(" ", result.get(0));
        Assert.assertEquals("=", result.get(1));
        Assert.assertEquals(" ", result.get(2));
        Assert.assertEquals("I", result.get(3));
        Assert.assertEquals("V", result.get(4));
    }
}
