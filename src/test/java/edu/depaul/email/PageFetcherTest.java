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
import org.jsoup.nodes.Document;

class PageFetcherTest {
    private String testURL = "src/test/resources/testFile.html";

    @Test
    @DisplayName("Constructor test")
    void testConstructor() {
        PageFetcher testFetcher = new PageFetcher();
        assertNotNull(testFetcher);
    }

    @Test
    @DisplayName("Get() test for returning HTTP docs")
    void testGetHTTP() {
        PageFetcher testFetcher = new PageFetcher();
        Document res = testFetcher.get("http://www.google.com");

        assertNotNull(res);
    }

    @Test
    @DisplayName("GetString() test for html string")
    void testGetString() {
        PageFetcher testFetcher = new PageFetcher();
        String res = testFetcher.getString("http://www.google.com");

        assertNotNull(res);
    }
}