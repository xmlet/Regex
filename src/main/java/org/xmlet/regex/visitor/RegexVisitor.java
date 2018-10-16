package org.xmlet.regex.visitor;

import org.xmlet.regexapi.*;

public class RegexVisitor extends ElementVisitor {

    private StringBuilder stringBuilder = new StringBuilder();

    public void visitElement(Element var1) {

    }

    public void visitAttribute(String var1, String var2) {

    }

    public void visitParent(Element var1) {

    }

    public <R> void visitText(Text<? extends Element, R> var1) {
        stringBuilder.append(var1.getValue());
    }

    public <R> void visitComment(Text<? extends Element, R> var1) {

    }

    public String getRegex() {
        return stringBuilder.toString();
    }

    /* ************************* */
    /* Character Escape Elements */
    /* ************************* */

    public <Z extends Element> void visitElementBell(Bell<Z> var1) {
        stringBuilder.append("\\a");
    }

    public <Z extends Element> void visitElementBackspace(Backspace<Z> var1) {
        stringBuilder.append("\u0008");
    }

    public <Z extends Element> void visitElementTab(Tab<Z> var1) {
        stringBuilder.append('\t');
    }

    public <Z extends Element> void visitElementCarriageReturn(CarriageReturn<Z> var1) {
        stringBuilder.append("\\r");
    }

    public <Z extends Element> void visitElementVerticalTab(VerticalTab<Z> var1) {
        stringBuilder.append("\\v");
    }

    public <Z extends Element> void visitElementFormFeed(FormFeed<Z> var1) {
        stringBuilder.append("\\f");
    }

    public <Z extends Element> void visitElementNewLine(NewLine<Z> var1) {
        stringBuilder.append("\\n");
    }

    public <Z extends Element> void visitElementEscape(Escape<Z> var1) {
        stringBuilder.append("\\e");
    }

    public <Z extends Element> void visitElementOctal(Octal<Z> var1) {
        stringBuilder.append('\\');
    }

    public <Z extends Element> void visitElementHexadecimal(Hexadecimal<Z> var1) {
        stringBuilder.append("\\x");
    }

    public <Z extends Element> void visitElementControlChar(ControlChar<Z> var1) {
        stringBuilder.append("\\c");
    }

    public <Z extends Element> void visitElementUnicode(Unicode<Z> var1) {
        stringBuilder.append("\\u");
    }

    public <Z extends Element> void visitElementOtherChar(OtherChar<Z> var1) {
        stringBuilder.append("\\");
    }

    /* *************************** */
    /* Character Escape Attributes */
    /* *************************** */

    public void visitAttributeOneChar(String oneChar) {
        stringBuilder.append(oneChar);
    }

    public void visitAttributeTwoDigits(String twoDigits) {
        stringBuilder.append(fillStringToNumberOfDigits(twoDigits, 2));
    }

    public void visitAttributeThreeDigits(String threeDigits) {
        stringBuilder.append(fillStringToNumberOfDigits(threeDigits, 3));
    }

    public void visitAttributeFourDigits(String fourDigits) {
        stringBuilder.append(fillStringToNumberOfDigits(fourDigits, 4));
    }

    /* ************************** */
    /* Character Classes Elements */
    /* ************************** */

    public <Z extends Element> void visitElementCharacterGroup(CharacterGroup<Z> var1) {
        stringBuilder.append('[');
    }

    public <Z extends Element> void visitElementCharacterNotGroup(CharacterNotGroup<Z> var1) {
        stringBuilder.append("[^");
    }

    public <Z extends Element> void visitElementFromFirstUntilLast(FromFirstUntilLast<Z> var1) {
        stringBuilder.append('[');
    }

    public <Z extends Element> void visitElementAnyChar(AnyChar<Z> var1) {
        stringBuilder.append('.');
    }

    public <Z extends Element> void visitElementUnicodeBlock(UnicodeBlock<Z> var1) {
        stringBuilder.append("\\p{");
    }

    public <Z extends Element> void visitElementUnicodeNotBlock(UnicodeNotBlock<Z> var1) {
        stringBuilder.append("\\P{");
    }

    public <Z extends Element> void visitElementAnyLetterOrDigit(AnyLetterOrDigit<Z> var1) {
        stringBuilder.append("\\w");
    }

    public <Z extends Element> void visitElementAnyNonLetterOrDigit(AnyNonLetterOrDigit<Z> var1) {
        stringBuilder.append("\\W");
    }

    public <Z extends Element> void visitElementAnyWhiteSpace(AnyWhiteSpace<Z> var1) {
        stringBuilder.append("\\s");
    }

    public <Z extends Element> void visitElementAnyNonWhiteSpace(AnyNonWhiteSpace<Z> var1) {
        stringBuilder.append("\\S");
    }

    public <Z extends Element> void visitElementAnyDigit(AnyDigit<Z> var1) {
        stringBuilder.append("\\d");
    }

    public <Z extends Element> void visitElementAnyNonDigit(AnyNonDigit<Z> var1) {
        stringBuilder.append("\\D");
    }

    /* **************************** */
    /* Character Classes Attributes */
    /* **************************** */

    public void visitAttributeCharGroup(String charGroup){
        stringBuilder.append(charGroup).append(']');
    }

    public void visitAttributeBlock(String block) {
        stringBuilder.append(block).append('}');
    }

    public void visitAttributeFirst(String first) {
        stringBuilder.append(first);
    }

    public void visitAttributeLast(String last) {
        stringBuilder.append('-').append(last).append(']');
    }

    /* **************************** */
    /*            Anchors           */
    /* **************************** */

    public <Z extends Element> void visitElementAtBeginning(AtBeginning<Z> var1) {
        stringBuilder.append('^');
    }

    public <Z extends Element> void visitElementAtEnd(AtEnd<Z> var1) {
        stringBuilder.append('$');
    }

    public <Z extends Element> void visitElementAtStringBeginning(AtStringBeginning<Z> var1) {
        stringBuilder.append("\\A");
    }

    public <Z extends Element> void visitElementAtStringEndOrNewline(AtStringEndOrNewline<Z> var1) {
        stringBuilder.append("\\Z");
    }

    public <Z extends Element> void visitElementAtStringEnd(AtStringEnd<Z> var1) {
        stringBuilder.append("\\z");
    }

    public <Z extends Element> void visitElementConsecutiveMatch(ConsecutiveMatch<Z> var1) {
        stringBuilder.append("\\G");
    }

    public <Z extends Element> void visitElementBoundary(Boundary<Z> var1) {
        stringBuilder.append("\\b");
    }

    public <Z extends Element> void visitElementNoBoundary(NoBoundary<Z> var1) {
        stringBuilder.append("\\B");
    }

    /* **************************** */
    /*     Grouping Constructs      */
    /* **************************** */

    public <Z extends Element> void visitElementSubExpression(SubExpression<Z> expression) {
        stringBuilder.append('(');
    }

    public <Z extends Element> void visitElementNamedSubExpression(NamedSubExpression<Z> var1) {
        stringBuilder.append("(?<");
    }

    public <Z extends Element> void visitElementNonCapturingGroup(NonCapturingGroup<Z> expression) {
        stringBuilder.append("(?:");
    }

    public <Z extends Element> void visitElementPositiveLookAhead(PositiveLookAhead<Z> expression) {
        stringBuilder.append("(?=");
    }

    public <Z extends Element> void visitElementNegativeLookAhead(NegativeLookAhead<Z> expression) {
        stringBuilder.append("(?!");
    }

    public <Z extends Element> void visitElementPositiveLookBehind(PositiveLookBehind<Z> expression) {
        stringBuilder.append("(?<=");
    }

    public <Z extends Element> void visitElementNegativeLookBehind(NegativeLookBehind<Z> expression) {
        stringBuilder.append("(?<!");
    }

    public <Z extends Element> void visitElementNonBackTracking(NonBackTracking<Z> expression) {
        stringBuilder.append("(?>");
    }

    /* ****************************** */
    /* Grouping Constructs Attributes */
    /* ****************************** */

    public void visitAttributeSubExpr(String expression) {
        stringBuilder.append(expression).append(')');
    }

    public void visitAttributeOptionsSubExpr(String expression) {
        stringBuilder.append(':').append(expression).append(')');
    }

    public <Z extends Element> void visitElementExpression(Expression<Z> var1) {
        stringBuilder.append('>');
    }

    public <Z extends Element> void visitParentExpression(Expression<Z> var1) {
        stringBuilder.append(')');
    }

    /* ****************************** */
    /*           Quantifiers          */
    /* ****************************** */

    public <Z extends Element> void visitElementZeroOrMore(ZeroOrMore<Z> var1) {
        stringBuilder.append('*');
    }

    public <Z extends Element> void visitElementOneOrMore(OneOrMore<Z> var1) {
        stringBuilder.append('+');
    }

    public <Z extends Element> void visitElementZeroOrOne(ZeroOrOne<Z> var1) {
        stringBuilder.append('?');
    }

    public <Z extends Element> void visitElementMatchPreviousNTimes(MatchPreviousNTimes<Z> var1) {
        stringBuilder.append('{');
    }

    public <Z extends Element> void visitElementMatchPreviousAtLeast(MatchPreviousAtLeast<Z> var1) {
        stringBuilder.append('{');
    }

    public <Z extends Element> void visitElementMatchPreviousBetween(MatchPreviousBetween<Z> var1) {
        stringBuilder.append('{');
    }

    public <Z extends Element> void visitElementMinMatchZeroOrMore(MinMatchZeroOrMore<Z> var1) {
        stringBuilder.append("*?");
    }

    public <Z extends Element> void visitElementMinMatchOneOrMore(MinMatchOneOrMore<Z> var1) {
        stringBuilder.append("+?");
    }

    public <Z extends Element> void visitElementMinMatchZeroOrOne(MinMatchZeroOrOne<Z> var1) {
        stringBuilder.append("??");
    }

    public <Z extends Element> void visitElementMinMatchPreviousNTimes(MinMatchPreviousNTimes<Z> var1) {
        stringBuilder.append('{');
    }

    public <Z extends Element> void visitElementMinMatchPreviousAtLeast(MinMatchPreviousAtLeast<Z> var1) {
        stringBuilder.append('{');
    }

    public <Z extends Element> void visitElementMinMatchPreviousBetween(MinMatchPreviousBetween<Z> var1) {
        stringBuilder.append('{');
    }

    /* ****************************** */
    /*     Quantifiers Attributes     */
    /* ****************************** */

    public void visitAttributeN(String n) {
        stringBuilder.append(n).append('}');
    }

    public void visitAttributeNTimes(String expression) {
        stringBuilder.append(expression).append(",}");
    }

    public <Z extends Element> void visitParentN(N<Z> var1) {
        stringBuilder.append(',');
    }

    public <Z extends Element> void visitParentM(M<Z> var1) {
        stringBuilder.append('}');
    }

    public void visitAttributeMinN(String expression) {
        stringBuilder.append(expression).append("}?");
    }

    public void visitAttributeMinNTimes(String expression) {
        stringBuilder.append(expression).append(",}?");
    }

    public <Z extends Element> void visitParentMinM(MinM<Z> var1) {
        stringBuilder.append("}?");
    }

    /* ****************************** */
    /*    BackReference Constructs    */
    /* ****************************** */

    public <Z extends Element> void visitElementBackReference(BackReference<Z> var1) {
        stringBuilder.append('\\');
    }

    public <Z extends Element> void visitElementNamedBackReference(NamedBackReference<Z> var1) {
        stringBuilder.append("\\k<");
    }

    /* **************************************** */
    /*    BackReference Constructs Attributes   */
    /* **************************************** */

    public void visitAttributeName(String name) {
        stringBuilder.append(name).append('>');
    }

    public void visitAttributeNumber(String number) {
        stringBuilder.append(number);
    }

    /* *************************** */
    /*    Alternation Constructs   */
    /* *************************** */

    public <Z extends Element> void visitElementAnd(And<Z> var1) {
        stringBuilder.append("&&");
    }

    public <Z extends Element> void visitElementOr(Or<Z> var1) {
        stringBuilder.append('|');
    }

    public <Z extends Element> void visitElementIfMatch(IfMatch<Z> var1) {
        stringBuilder.append("(?(?=");
    }

    public <Z extends Element> void visitElementConditionalNamedExpression(ConditionalNamedExpression<Z> expression) {
        stringBuilder.append('(');
    }

    public <Z extends Element> void visitParentConditionalNamedExpressionComplete(ConditionalNamedExpressionComplete<Z> expression) {
        stringBuilder.append(')');
    }

    public <Z extends Element> void visitElementIfGroupMatch(IfGroupMatch<Z> var1) {
        stringBuilder.append("(?(");
    }

    public <Z extends Element> void visitParentIfGroupMatchComplete(IfGroupMatchComplete<Z> var1) {
        stringBuilder.append(')');
    }

    /* ************************************* */
    /*    Alternation Constructs Attributes  */
    /* ************************************* */

    public <Z extends Element> void visitElementThenExpression(ThenExpression<Z> then) {
        stringBuilder.append(')');
    }

    public <Z extends Element> void visitElementElseExpression(ElseExpression<Z> var1) {
        stringBuilder.append('|');
    }

    public <Z extends Element> void visitParentElseExpression(ElseExpression<Z> var1) {
        stringBuilder.append(')');
    }

    public <Z extends Element> void visitElementCondNameFirst(CondNameFirst<Z> expression) {
        stringBuilder.append('{');
    }

    public <Z extends Element> void visitParentCondNameFirst(CondNameFirst<Z> expression) {
        stringBuilder.append('}');
    }

    /* ****************** */
    /*    Substitutions   */
    /* ****************** */

    public <Z extends Element> void visitElementNumberSubstitution(NumberSubstitution<Z> expression) {
        stringBuilder.append('$');
    }

    public <Z extends Element> void visitElementNameSubstitution(NameSubstitution<Z> expression) {
        stringBuilder.append("${");
    }

    /* **************************** */
    /*    Substitutions Attributes  */
    /* **************************** */

    public void visitAttributeSubstitutionName(String expression) {
        stringBuilder.append(expression).append('}');
    }

    /* ******************** */
    /*    Options Elements  */
    /* ******************** */

    public <Z extends Element> void visitElementCaseInsensitive(CaseInsensitive<Z> var1) {
        stringBuilder.append('i');
    }

    public <Z extends Element> void visitElementMultilineMode(MultilineMode<Z> var1) {
        stringBuilder.append('m');
    }

    public <Z extends Element> void visitElementNoUnnamedModes(NoUnnamedModes<Z> var1) {
        stringBuilder.append('n');
    }

    public <Z extends Element> void visitElementSingleLineMode(SingleLineMode<Z> var1) {
        stringBuilder.append('s');
    }

    public <Z extends Element> void visitElementIgnoreUnescapedWhiteSpaces(IgnoreUnescapedWhiteSpaces<Z> var1) {
        stringBuilder.append('x');
    }

    /* **************************** */
    /*    Miscellaneous Constructs  */
    /* **************************** */

    public <Z extends Element> void visitElementActivateOption(ActivateOption<Z> var1) {
        stringBuilder.append("(?");
    }

    public <Z extends Element> void visitElementRegexComment(RegexComment<Z> var1) {
        stringBuilder.append("(?#");
    }

    public <Z extends Element> void visitParentRegexComment(RegexComment<Z> var1) {
        stringBuilder.append(')');
    }

    public <Z extends Element> void visitElementRegexLineComment(RegexLineComment<Z> var1) {
        stringBuilder.append('#');
    }

    /* *************************************** */
    /*    Miscellaneous Constructs Attributes  */
    /* *************************************** */

    public void visitAttributeComment(String comment) {
        stringBuilder.append(comment);
    }

    public void visitAttributeOption(String option) {
        stringBuilder.append(option);
    }

    /* ******************** */
    /*    Auxiliary Methods  */
    /* ******************** */

    private String fillStringToNumberOfDigits(String string, int numberOfDigits) {
        while (string.length() != numberOfDigits){
            string = "0".concat(string);
        }

        return string;
    }
}
