package edu.depaul.email;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import java.util.List;
import java.util.ArrayList;

public class PageCrawlerTest {

    private String urlPath = "src/test/resources/testLinksFile.html";
    private String badURLPath = "src/test/resources/testBadLinks.html";
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
    @DisplayName("Tests PageCrawler constructor")
    void testCrawlerConstructor() {
        StorageService testStorage = mock(StorageService.class);
        PageCrawler testCrawler = new PageCrawler(testStorage);

        assertNotNull(testCrawler);
    }

    @Test
    @DisplayName("Tests PageCrawler constructor with specified maximum")
    void testCrawlerConstructorMax() {
        StorageService testStorage = mock(StorageService.class);
        PageCrawler testCrawler = new PageCrawler(testStorage, 30);

        assertNotNull(testCrawler);
    }

    @Test
    @DisplayName("Crawl() find num of good links.")
    void testCrawlGoodLinks() {
        StorageService testStorage = mock(StorageService.class);
        PageCrawler testCrawler = new PageCrawler(testStorage);
        testCrawler.crawl(urlPath);
        assertEquals(1, testCrawler.getGoodLinks().size());
    }

    @Test
    @DisplayName("Crawl() find num of bad links")
    void testCrawlBadURL() {
        StorageService testStorage = mock(StorageService.class);
        PageCrawler testCrawler = new PageCrawler(testStorage);
        testCrawler.crawl(badURLPath);
        assertEquals(3, testCrawler.getBadLinks().size());
    }
}
