import org.junit.Assert;
import org.junit.Test;
import org.xmlet.regex.Regex;

import java.util.List;

public class CharacterEscapeTest {

    /**
     * <p>The bell regex:
     * <p>{@code \a}
     * <p>Matches the bell character.
     */
    @Test
    public void testBellRegex(){
        String toMatch = "This is \u0007";
        Regex regex = new Regex(expr -> expr.matchRegex().bell());
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals("\u0007", result.get(0));
    }

    /**
     * <p>The backspace regex:
     * <p>{@code \b}
     * <p>Matches the backspace character.
     */
    @Test
    public void testBackspaceRegex(){
        String toMatch = "This is \b";
        Regex regex = new Regex(expr -> expr.matchRegex().backspace());
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals("\b", result.get(0));
    }

    /**
     * <p>The tab regex:
     * <p>{@code \t}
     * <p>Matches the tab character.
     */
    @Test
    public void testTabRegex(){
        String toMatch = "This is \u0009";
        Regex regex = new Regex(expr -> expr.matchRegex().tab());
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals("\u0009", result.get(0));
    }

    /**
     * <p>The carriageReturn regex:
     * <p>{@code \r}
     * <p>Matches the carriage return character.
     */
    @Test
    public void testCarriageReturnRegex(){
        String toMatch = "\r\nThese are \ntwo lines.";
        Regex regex = new Regex(expr -> expr.matchRegex().carriageReturn());
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals("\r", result.get(0));
    }

    /**
     * <p>The verticalTab regex:
     * <p>{@code \v}
     * <p>Matches the vertical tab character.
     */
    @Test
    public void testVerticalTabRegex(){
        String toMatch = "This is \u000B";
        Regex regex = new Regex(expr -> expr.matchRegex().verticalTab());
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals("\u000B", result.get(0));
    }

    /**
     * <p>The formFeed regex:
     * <p>{@code \f}
     * <p>Matches the form feed character.
     */
    @Test
    public void testFormFeedRegex(){
        String toMatch = "This is \u000C";
        Regex regex = new Regex(expr -> expr.matchRegex().formFeed());
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals("\u000C", result.get(0));
    }

    /**
     * <p>The newLine regex:
     * <p>{@code \n}
     * <p>Matches the new line character.
     */
    @Test
    public void testNewLineRegex(){
        String toMatch = "This is \n";
        Regex regex = new Regex(expr -> expr.matchRegex().newLine());
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals("\n", result.get(0));
    }

    /**
     * <p>The escape regex:
     * <p>{@code \e}
     * <p>Matches the escape character.
     */
    @Test
    public void testEscapeRegex(){
        String toMatch = "This is \u001B";
        Regex regex = new Regex(expr -> expr.matchRegex().escape());
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals("\u001B", result.get(0));
    }

    /**
     * <p>The octal regex:
     * <p>{@code \nnn}
     * <p>Matches an octal character.
     * <p>In this example we use the following regex:
     * <p>{@code \w\40\w}
     * <p>This matches any letter or digit followed by the character defined in the octal representation by 40, which is the
     * space character, which is then followed by another letter or digit.
     * <p>Using the String "a bc d" we obtain two results:
     * <p>1: "a b"
     * <p>2: "c d"
     */
    @Test
    public void testOctalRegex(){
        String toMatch = "a bc d";
        Regex regex = new Regex(expr -> expr.matchRegex().anyLetterOrDigit().octal().attrThreeDigits(40).anyLetterOrDigit());
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(2, result.size());
        Assert.assertEquals("a b", result.get(0));
        Assert.assertEquals("c d", result.get(1));
    }

    /**
     * <p>The hexadecimal regex:
     * <p>{@code \x nn}
     * <p>Matches an hexadecimal character.
     * <p>In this example we use the following regex:
     * <p>{@code \w\x20\w}
     * <p>This matches any letter or digit followed by the character defined in the hexadecimal representation by 20, which is the
     * space character, which is then followed by another letter or digit.
     * <p>Using the String "a bc d" we obtain two results:
     * <p>1: "a b"
     * <p>2: "c d"
     */
    @Test
    public void testHexadecimalRegex(){
        String toMatch = "a bc d";
        Regex regex =  new Regex(expr -> expr.matchRegex().anyLetterOrDigit().hexadecimal().attrTwoDigits(20).anyLetterOrDigit());
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(2, result.size());
        Assert.assertEquals("a b", result.get(0));
        Assert.assertEquals("c d", result.get(1));
    }

    /**
     * <p>The controlCharacter regex:
     * <p>{@code \cX}
     * <p>Matches a control character.
     * <p>In this example we use the regex:
     * <p>{@code \cC}
     * <p>This matches the control character \u0003.
     */
    @Test
    public void testControlCharacterRegex(){
        String toMatch = "\u0003";
        Regex regex = new Regex(expr -> expr.matchRegex().controlChar().attrOneChar("C"));
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals("\u0003", result.get(0));
    }

    /**
     * <p>The unicode regex:
     * <p>&#92;u nnnn
     * <p>Matches an unicode character.
     * <p>In this example we use the following regex:
     * <p>{@code \w&#92;u20\w}
     * <p>This matches any letter or digit followed by the character defined in the unicode representation by 20, which is the
     * space character, which is then followed by another letter or digit.
     * <p>Using the String "a bc d" we obtain two results:
     * <p>1: "a b"
     * <p>2: "c d"
     */
    @Test
    public void testUnicodeRegex(){
        String toMatch = "a bc d";
        Regex regex = new Regex(expr -> expr.matchRegex().anyLetterOrDigit().unicode().attrFourDigits(20).__().anyLetterOrDigit());
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(2, result.size());
        Assert.assertEquals("a b", result.get(0));
        Assert.assertEquals("c d", result.get(1));
    }

    /**
     * <p>The otherChar regex:
     * <p>{@code \X}
     * <p>This regex is used to represent potential special characters.
     * <p>In this example we use the following regex:
     * <p>{@code \d+[\+-quickExpr\*]\d+}
     * <p>This expression should match any sum, subtraction, multiplication of two numbers, for example:
     * <p>With the String:
     * <p>(2+2) * 3*9
     * <p>Results:
     * <p>1: 2+2
     * <p>2: 3*9
     */
    @Test
    public void testOtherRegex(){
        String toMatch = "(2+2) * 3*9";
        Regex regex = new Regex(
                expr -> expr.matchRegex()
                        .anyDigit().oneOrMore()
                        .characterGroup().attrCharGroup(
                            Regex.quickExpr(subExpr -> subExpr.otherChar().attrOneChar("+").text("-quickExpr").otherChar().attrOneChar("*")))
                        .anyDigit().oneOrMore());
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(2, result.size());
        Assert.assertEquals("2+2", result.get(0));
        Assert.assertEquals("3*9", result.get(1));
    }
}
