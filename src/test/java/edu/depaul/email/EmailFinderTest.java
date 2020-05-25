package edu.depaul.email;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class EmailFinderTest {

    @Test
    @DisplayName("Test EmailFinder with no URL argument")
    void noURLTest(){
        EmailFinder testFinder = new EmailFinder();
        String[] testArgs = {};
        testFinder.run(testArgs);
        String path = "badlinks.txt";

        try (Stream<String> txtLines = Files.lines(Paths.get(path))) {
            String res = txtLines.collect(Collectors.joining(System.lineSeparator()));
            assertEquals(res, "");
        } catch (IOException e) {
            throw new EmailFinderException("error", e);
        }
    }

    @Test
    @DisplayName("Tests if URL argument is valid")
    void urlTest(){
        EmailFinder testFinder = new EmailFinder();
        String[] testArgs = {"validURL.txt"};
        testFinder.run(testArgs);
        String path = "good-links.txt";

        try(Stream<String> txtLines = Files.lines(Paths.get(path))){
            String res = txtLines.collect(Collectors.joining(System.lineSeparator()));
            assertEquals(res, "validURL.txt");
        } catch(IOException e){
            throw new EmailFinderException("Invalid URL", e);
        }
    }

    @Test
    @DisplayName("Test EmailFinder output txt files with valid argument")
    void outputTest(){
        EmailFinder testFinder = new EmailFinder();
        String[] testArgs = {"http://d2l.depaul.edu", "10"};
        testFinder.run(testArgs);
    }

}