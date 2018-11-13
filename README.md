[![Maven Central](https://img.shields.io/maven-central/v/com.github.xmlet/regex.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.github.xmlet%22%20AND%20a:%22regex%22)

# Regex

<div align="justify"> 
    This library is a Java library which uses a Java DSL, <a href="https://github.com/xmlet/RegexApi">RegexApi</a>, to 
    create regular expressions and use the Java Pattern class to perform matches with the generated regular expression.
    The project itself is very simple, it implements a concrete implementation for the correct usage of the 
    <a href="https://github.com/xmlet/RegexApi">RegexApi</a> to achieve this concrete objective. This project supports
    the whole syntax supported by the 
    <a href="https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html">Java 8 Pattern Class</a>.
    The objective of this project is to make regular expressions easier to use for people who aren't used to work with 
    them, providing a fluent and intuitive way to define regular expressions.
</div>

## Installation

<div align="justify"> 
    First, in order to include it to your Maven project, simply add this dependency:
    <br />
    <br />
</div>

```xml
<dependency>
    <groupId>com.github.xmlet</groupId>
    <artifactId>regex</artifactId>
    <version>1.0.0</version>
</dependency>
``` 

## Usage

<div align="justify"> 
    The regular expression is composed by multiple operations. Each one of the operations supported by Java is depicted in
    the <a href="https://github.com/xmlet/Regex/tree/master/src/test/java">tests</a> of this project. Each test is 
    properly documented with explanations regarding each method, providing a better understanding of the functionalities 
    provided by this library. Let's take a look at one of the tests, 
    <a href="https://github.com/xmlet/Regex/blob/master/src/test/java/AnchorTest.java">testAtBeginningRegex on AnchorTests</a>:
</div>

```java
public class AnchorTest {
    public void testAtBeginningRegex(){
        String toMatch = "901-333-";
        Regex regex = new Regex(expr -> 
                expr.matchRegex().atBeginning().anyDigit().matchPreviousNTimes().attrN(3));
        List<String> result = regex.match(toMatch);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals("901", result.get(0));
    }
}
```

<div align="justify"> 
    In this example we define a regular expression with:
    <br />
    <br />
    <i>new Regex(expr -> expr.matchRegex().atBeginning().anyDigit().matchPreviousNTimes().attrN(3));</i>
    <br />
    <br />
    This regular expression will match any set of three digits placed on the beggining of a <i>String</i>. Defined in the
    regular expression syntax it would be: 
    <br />
    <br />
    <b>^\d{3}</b>
    <br />
    <br />
    In this case we are using a the <i>String 901-333-</i>, therefore the expected result is <i>901</i>. If you want to 
    know more about the possibilities of this library explore the 
    <a href="https://github.com/xmlet/Regex/tree/master/src/test/java">tests</a> made available that best apply to the 
    regular expressions that you want to generate. 
</div>