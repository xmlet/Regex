import org.junit.Assert;
import org.junit.Test;
import org.xmlet.regexapi.MatchingOperationsAll1;
import org.xmlet.regex.Regex;

import java.util.List;

public class SubstitutionTest {

    /**
     * <p>The numberSubstitution regex:
     * <p>$number
     * <p>In this example we define the following match pattern:
     * <p>\b(\w+)(\s)(\w+)\b
     * <p>And the following substitution pattern:
     * <p>$3$2$1
     * <p>Using the String "one two" as input the following will happen:
     * <p>"one" will be matched as the first group;
     * <p>" " will be matched as the second group;
     * <p>"two" will be matched as the third group;
     * <p>Or:
     * <p>  $1   $2  $3
     * <p>"one" " " "two"
     * <p>Since the substitution pattern is "$3$2$1" the words will be rearranged and will generate the following String:
     * <p>  $3   $2   $1
     * <p> "two" " " "one"
     */
    @Test
    public void testNumberSubstitutionRegex(){
        String toReplace = "one two";
        Regex regex = new Regex(
                expr -> {
                    expr.matchRegex()
                        .boundary()
                        .subExpression().attrSubExpr(Regex.quickExpr(subExpr -> subExpr.anyLetterOrDigit().oneOrMore()))
                        .subExpression().attrSubExpr(Regex.quickExpr(MatchingOperationsAll1::anyWhiteSpace))
                        .subExpression().attrSubExpr(Regex.quickExpr(subExpr -> subExpr.anyLetterOrDigit().oneOrMore()))
                        .boundary();

                    expr.substitutionRegex()
                        .numberSubstitution().attrNumber(3)
                        .numberSubstitution().attrNumber(2)
                        .numberSubstitution().attrNumber(1);
                });

        List<String> result = regex.replace(toReplace);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals("two one", result.get(0));
    }

    /**
     * <p>The nameSubstitution regex:
     * <p>${name}
     * <p>In this example we define the following match pattern:
     * <p>{@code \b(?<word1>\w+)(\s)(?<word2>\w+)\b}
     * <p>And the following substitution pattern:
     * <p>${word2} ${word1}
     * <p>Using the String "one two" as input the following will happen:
     * <p>"one" will be matched as the group word1;
     * <p>" " will be matched as the second group;
     * <p>"two" will be matched as the group word2;
     * <p>Or:
     * <p>  word1   $2  word2
     * <p>  "one"  " "  "two"
     * <p>Since the substitution pattern is "${word2} ${word1}" the words will be rearranged and will generate the following String:
     * <p>  word2 " " word1
     * <p>  "two" " " "one"
     */
    @Test
    public void testNameSubstitutionRegex(){
        String toReplace = "one two";
        Regex regex = new Regex(
                expr -> {
                    expr.matchRegex()
                            .boundary()
                            .namedSubExpression().nameFirst("word1").expression(Regex.quickExpr(subExpr -> subExpr.anyLetterOrDigit().oneOrMore())).__()
                            .subExpression().attrSubExpr(Regex.quickExpr(MatchingOperationsAll1::anyWhiteSpace))
                            .namedSubExpression().nameFirst("word2").expression(Regex.quickExpr(subExpr -> subExpr.anyLetterOrDigit().oneOrMore())).__()
                            .boundary();

                    expr.substitutionRegex()
                            .nameSubstitution().attrSubstitutionName("word2")
                            .text(" ")
                            .nameSubstitution().attrSubstitutionName("word1");
                });

        List<String> result = regex.replace(toReplace);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals("two one", result.get(0));
    }
}
