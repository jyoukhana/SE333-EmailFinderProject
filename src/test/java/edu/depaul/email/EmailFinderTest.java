package edu.depaul.email;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;



public class EmailFinderTest {

    private String testURL = "src/test/resources/testFile.html";
    private String emailPath = "email.txt";
    private String goodlinksPath = "good-links.txt";
    private String badlinksPath = "badlinks.txt";

    //method to delete txt files during every test
    void deleteFiles() {
        try {
            Files.deleteIfExists(Paths.get(goodlinksPath));
            Files.deleteIfExists(Paths.get(badlinksPath));
            Files.deleteIfExists(Paths.get(emailPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Single argument")
    void testSingleArgs() {
        deleteFiles();

        String[] testArgs = {testURL};
        EmailFinder testFinder = new EmailFinder();
        testFinder.run(testArgs);

        File emailFile = new File(emailPath);
        File goodFile = new File(goodlinksPath);
        File badFile = new File(badlinksPath);

        assertAll(
                () -> assertTrue(emailFile.exists(), emailPath + " is not found"),

                () -> assertTrue(goodFile.exists(), goodlinksPath + " is not found"),

                () -> assertTrue(badFile.exists(), badlinksPath + " is not found")
        );
    }

    @Test
    @DisplayName("Multiple arguments")
    void testMultipleArgs() {
        deleteFiles();

        String[] testArgs = {testURL, "10"};
        EmailFinder testFinder = new EmailFinder();
        testFinder.run(testArgs);

        File emailFile = new File(emailPath);
        File goodFile = new File(goodlinksPath);
        File badFile = new File(badlinksPath);

        assertAll(
                () -> assertTrue(emailFile.exists(), emailPath + " is not found"),

                () -> assertTrue(goodFile.exists(), goodlinksPath + " is not found"),

                () -> assertTrue(badFile.exists(), badlinksPath + " is not found")
        );
    }

    @Test
    @DisplayName("Valid URL argument")
    void urlTest(){
        deleteFiles();

        EmailFinder testFinder = new EmailFinder();
        String[] testArgs = {"http://d2l.depaul.edu"};
        testFinder.run(testArgs);

        File emailFile = new File(emailPath);
        File goodFile = new File(goodlinksPath);
        File badFile = new File(badlinksPath);

        assertAll(
                () -> assertTrue(emailFile.exists(), emailPath + " is not found"),

                () -> assertTrue(goodFile.exists(), goodlinksPath + " is not found"),

                () -> assertTrue(badFile.exists(), badlinksPath + " is not found")
        );
    }

    @Test
    @DisplayName("Output .txt files with valid argument")
    void outputTest(){
        EmailFinder testFinder = new EmailFinder();
        String[] testArgs = {"http://d2l.depaul.edu", "10"};
        testFinder.run(testArgs);
    }

    @Test
    @DisplayName("Invalid URL argument with null args")
    void testNullArgs() {
        deleteFiles();
        EmailFinder testFinder = new EmailFinder();
        String[] testArgs = {};
        testFinder.run(testArgs);

        File emailFile = new File(emailPath);
        File goodFile = new File(goodlinksPath);
        File badFile = new File(badlinksPath);

        assertAll(
                () -> assertFalse(emailFile.exists(), emailPath + " is not found"),

                () -> assertFalse(goodFile.exists(), goodlinksPath + " is not found"),

                () -> assertFalse(badFile.exists(), badlinksPath + " is not found")
        );
    }

    @Test
    @DisplayName("Invalid URL argument with non-empty string")
    void invalidURLNonEmptyString() {
        deleteFiles();
        EmailFinder testFinder = new EmailFinder();
        String[] testArgs = {"i love writing tests"};
        testFinder.run(testArgs);

        try (Stream<String> txtLines = Files.lines(Paths.get(badlinksPath))) {
            String res = txtLines.collect(Collectors.joining(System.lineSeparator()));

            assertEquals(res, "i love writing tests");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Valid URL with valid email argument")
    void validURLValidEmailTest() {
        EmailFinder testFinder = new EmailFinder();
        String[] testArgs = {"mbadel98@gmail.com"};
        testFinder.run(testArgs);

        File emailFile = new File(emailPath);
        File goodFile = new File(goodlinksPath);
        File badFile = new File(badlinksPath);

        assertAll(
                () -> assertTrue(emailFile.exists(), emailPath + " is not found"),

                () -> assertTrue(goodFile.exists(), goodlinksPath + " is not found"),

                () -> assertTrue(badFile.exists(), badlinksPath + " is not found")
        );
    }

}