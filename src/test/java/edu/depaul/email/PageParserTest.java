package edu.depaul.email;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Set;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

class PageParserTest {
    private String goodTestFile = "src/test/resources/testFile.html";
    private String badTestFile = "src/test/resources/testBadLinks.html";
    private String testEmails = "jyoukha6@depaul.edu mbadel98@gmail.com jyoukhana1765@gmail.com";

    @Test
    @DisplayName("Constructor test")
    void testConstructor() {
        PageParser testParser = new PageParser();

        assertNotNull(testParser);
    }

    @Test
    @DisplayName("No links in html doc")
    void testNoLinks() {
        String testHTML = "<html><body><br>asdasda<br>dsfddddss<br></body></html>";
        Document testDoc = Jsoup.parse(testHTML);

        PageParser testParser = new PageParser();
        Set<String> links = testParser.findLinks(testDoc);

        assertEquals(0, links.size());
    }

    @ParameterizedTest
    @MethodSource("multipleLinkParameters")
    @DisplayName("Multiple links in doc")
    void testMultipleLinks(int links, String testHTML) {
        Document testDoc = Jsoup.parse(testHTML);
        PageParser testParser = new PageParser();

        Set<String> parsedLinks = testParser.findLinks(testDoc);
        assertEquals(links, parsedLinks.size());
    }

    @Test
    @DisplayName("Zero email addresses in doc")
    void testNoEmails() {
        String testHTMLFile = badTestFile;
        Document testDoc = Jsoup.parse(testHTMLFile);

        PageParser testParser = new PageParser();
        Set<String> emails = testParser.findEmails(testDoc);

        assertEquals(0, emails.size());
    }

    @Test
    @DisplayName("Multiple email addresses in doc")
    void testSomeEmails() {
        String testHTMLFile = "<html><body>" + testEmails + "</body></html>";
        Document testDoc = Jsoup.parse(testHTMLFile);

        PageParser testParser = new PageParser();
        Set<String> emails = testParser.findEmails(testDoc);

        assertEquals(3, emails.size());
    }

    private static Stream<Arguments> multipleLinkParameters() {
        String htmlOne = "<html><body> <a href='http://markbadel.com'>friend site</a> <br> <a href='http://google.com'>google</a> </body></html>";
        String htmlTwo = "<html><body> <a href='http://markbadel.com'>friend site</a> <br> <a href='http://google.com'>google</a> <br> <a href='http://depaul.edu'>depaul</a> </body></html>";

        return Stream.of(
                Arguments.of(2, htmlOne),
                Arguments.of(3, htmlTwo)
        );
    }
}