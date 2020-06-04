package edu.depaul.email;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class ListWriterTest {

    @Test
    @DisplayName("Test for list creation")
    void testWriter(){
        String[] testArr = new String[] {"zero", "one", "two", "three", "four"};
        String testStr = testArr[0] + "\n" + testArr[1] + "\n" + testArr[2] + "\n" + testArr[3] + "\n" + testArr[4] + "\n";

        ArrayList<String> testList = new ArrayList<String>();
        for(int i = 0; i < testArr.length; i++){
            testList.add(testArr[i]);
        }

        OutputStream testStream = new ByteArrayOutputStream();
        ListWriter testWriter = new ListWriter(testStream);

        try {
            testWriter.writeList(testList);
            assertEquals(testStr, testStream.toString());
        } catch(Exception e){

            throw new EmailFinderException("Invalid list", e);
        }

    }
}
