package edu.gatech.seclass.encode;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * This is a Georgia Tech provided code example for use in assigned private GT repositories. Students and other users of this template
 * code are advised not to share it with other students or to make it available on publicly viewable websites including
 * repositories such as github and gitlab.  Such sharing may be investigated as a GT honor code violation. Created for CS6300.
 *
 * DO NOT ALTER THIS CLASS.  Use it as an example for MyMainTest.java
 */

public class MyMainTest {

    private ByteArrayOutputStream outStream;
    private ByteArrayOutputStream errStream;
    private PrintStream outOrig;
    private PrintStream errOrig;
    private Charset charset = StandardCharsets.UTF_8;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        outStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outStream);
        errStream = new ByteArrayOutputStream();
        PrintStream err = new PrintStream(errStream);
        outOrig = System.out;
        errOrig = System.err;
        System.setOut(out);
        System.setErr(err);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(outOrig);
        System.setErr(errOrig);
    }

    /*
     *  TEST UTILITIES
     */

    // Create File Utility
    private File createTmpFile() throws Exception {
        File tmpfile = temporaryFolder.newFile();
        tmpfile.deleteOnExit();
        return tmpfile;
    }

    // Write File Utility
    private File createInputFile(String input) throws Exception {
        File file =  createTmpFile();

        OutputStreamWriter fileWriter =
                new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);

        fileWriter.write(input);

        fileWriter.close();
        return file;
    }





    //Read File Utility
    private String getFileContent(String filename) {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    /*
     * TEST FILE CONTENT
     */
    private static final String FILE1 = "abc  tuvw.XYZ";
    private static final String FILE2 = "Howdy Billy, are you going to take cs6300 and cs6400 next semester?";
    private static final String FILE3 = "abcXYZ123ABCxyz";
    private static final String FILE4 = "AbcXYZ\nabc\nXYZcba";
    private static final String FILE5 = " ";
    private static final String FILE6 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String FILE7 = "0123456789";
    private static final String FILE8 = "Let's try some **special**  %!(characters)!% ###\r" +
            "and line breaks^$@ \r" +
            "in \\1/ file;\r" +
            ":-)";
    private static final String FILE9 = "Up with the white and gold\r" +
            "Down with the red and black\r" +
            "Georgia Tech is out for a victory\r" +
            "We'll drop a battle axe on georgia's head\r" +
            "When we meet her our team is sure to beat her\r" +
            "Down on the old farm there will be no sound\r" +
            "'Till our bow wows rips through the air\r" +
            "When the battle is over georgia's team will be found\r" +
            "With the Yellow Jacket's swarming 'round! Hey!";
    private static final String FILE10 = "Robert'); DROP TABLE students;--";
    private static final String FILE11 = ".*";
    private static final String FILE12 = " I\u2019ve got a really good UDP joke to tell you, but I don\u2019t know if you\u2019ll get it. ";
    private static final String FILE13 = "3 Database Admins walked\n" +
            "into a NoSQL bar.\n" +
            "A little later, they walked\n" +
            "out because they couldn\u2019t find a table.";
    private static final String FILE14 = "\naAbB\ncCdD eE\nfF";
    private static final String FILE15 = "";
    private static final String FILE16 = "abcabc123123abcabc123123 abcabc123123abcabc123123  abcabc123123abcabc123123";
    private static final String FILE17 = "Up with the white and gold\r" +
            "Down with the red and black\r" +
            "Georgia Tech is out for a victory\r";

    private static final String USAGE = "Usage: encode [-d string] [-w] [-x char] [-r string | -k string] [-c] <filename>";


    // test cases

    /*
     *   TEST CASES
     */

    // Purpose: To provide an example of a test case format
    // Frame #: Instructor example 1 from assignment directions
    @Test
    public void mainTest1() throws Exception {
        File inputFile = createInputFile(FILE1);

        String args[] = {inputFile.getPath()};
        Main.main(args);

        String expected = "cba  wvut.ZYX";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: To provide an example of a test case format
    // Frame #: Instructor example 2 from assignment directions
    @Test
    public void mainTest2() throws Exception {
        File inputFile = createInputFile(FILE3);

        String args[] = {"-r", "aZ", inputFile.getPath()};
        Main.main(args);

        String expected = "bcXY123BCxy";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: To provide an example of a test case format
    // Frame #: Instructor example 3 from assignment directions
    @Test
    public void mainTest3() throws Exception {
        File inputFile = createInputFile(FILE2);

        String args[] = {"-w", "-d", ":,", "-k", "aeiouxyz", inputFile.getPath()};
        Main.main(args);

        String expected = "yi yo,?eee xe  a  ea o io uoy ea ";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: To provide an example of a test case format
    // Frame #: Instructor example 4 from assignment directions
    @Test
    public void mainTest4() throws Exception {
        File inputFile = createInputFile(FILE3);

        String args[] = {"-w", "-c", inputFile.getPath()};
        Main.main(args);

        String expected = "ZYXcba321zyxCBA";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }


    // Purpose: To provide an example of a test case format (no arguments passed)
    // Frame #: Instructor error example
    @Test
    public void mainTest5() {
        String args[] = new String[0]; //no arguments
        Main.main(args);
        assertEquals(USAGE, errStream.toString().trim());
    }

    // Purpose: To provide an example of a test case format
    // Frame #: Instructor example 5 from assignment directions
    @Test
    public void mainTest6() throws Exception {
        File inputFile = createInputFile(FILE1);

        String args[] = {"-d", " .", "-x", "!", inputFile.getPath()};
        Main.main(args);

        String expected = "abc!tuvw!XYZ";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertTrue(errStream.toString().isBlank());
        assertTrue(outStream.toString().isBlank());
    }

}