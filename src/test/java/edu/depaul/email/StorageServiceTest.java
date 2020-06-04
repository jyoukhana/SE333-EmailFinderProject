package edu.depaul.email;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;

class StorageServiceTest {

    @Test
    @DisplayName("Tests StorageService constructor.")
    void testConstructor() {
        StorageService testStorage = new StorageService();

        assertNotNull(testStorage);
    }

    @Test
    @DisplayName("Writing in email.txt")
    void testWriteEmail() {
        String emailPath = "src/test/resources/expectedEmails.txt";

        try {
            Files.deleteIfExists(Paths.get(emailPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] emailArr = {"mbadel98@gmail.com", "jyoukhana1765@gmail.com", "jyoukha6@gmail.com"};

        Collection<String> emailColl = new ArrayList<String>();
        emailColl.add(emailArr[0]);
        emailColl.add(emailArr[1]);
        emailColl.add(emailArr[2]);

        StorageService testStorage = new StorageService();

        testStorage.addLocation(StorageService.StorageType.EMAIL, emailPath);
        testStorage.storeList(StorageService.StorageType.EMAIL, emailColl);

        try {
            List<String> res = Files.readAllLines(Paths.get(emailPath));
            assertEquals(emailColl, res);
        } catch (IOException e) {
            fail("Directory not found");
        }

    }

    @Test
    @DisplayName("Writing in badlinks.txt")
    void testWriteBadLinks() {
        String badlinkPath = "src/test/resources/badlinksTest.txt";

        try {
            Files.deleteIfExists(Paths.get(badlinkPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] badLinkArr = {"http://asjdhakjdssdfnkjsdf.kfd", "http://jjjjjjjjjj.com", "http://thisisnotarealwebsiteitsforatest.com"};

        Collection<String> badLinkColl = new ArrayList<String>();
        badLinkColl.add(badLinkArr[0]);
        badLinkColl.add(badLinkArr[1]);
        badLinkColl.add(badLinkArr[2]);

        StorageService testStorage = new StorageService();

        testStorage.addLocation(StorageService.StorageType.EMAIL, badlinkPath);
        testStorage.storeList(StorageService.StorageType.EMAIL, badLinkColl);

        try {
            List<String> res = Files.readAllLines(Paths.get(badlinkPath));
            assertEquals(badLinkColl, res);
        } catch (IOException e) {
            fail("Directory not found");
        }

    }

    @Test
    @DisplayName("Writing in badlinks.txt")
    void testWriteGoodLinks() {
        String goodlinkPath = "src/test/resources/goodlinksTest.txt";

        try {
            Files.deleteIfExists(Paths.get(goodlinkPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] goodLinkArr = {"http://google.com", "http://depaul.edu", "http://markbadel.com"};

        Collection<String> goodLinkColl = new ArrayList<String>();
        goodLinkColl.add(goodLinkArr[0]);
        goodLinkColl.add(goodLinkArr[1]);
        goodLinkColl.add(goodLinkArr[2]);

        StorageService testStorage = new StorageService();

        testStorage.addLocation(StorageService.StorageType.EMAIL, goodlinkPath);
        testStorage.storeList(StorageService.StorageType.EMAIL, goodLinkColl);

        try {
            List<String> res = Files.readAllLines(Paths.get(goodlinkPath), StandardCharsets.US_ASCII);
            assertEquals(goodLinkColl, res);
        } catch (IOException e) {
            fail("Directory not found");
        }

    }
}