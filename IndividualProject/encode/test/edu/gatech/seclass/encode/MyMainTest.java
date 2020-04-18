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
    private static final String FILE0 = "";
    private static final String FILE1 = "abc tuvw.XYZ";
    private static final String FILE2 = "Howdy Billy, are you going to take computer";
    private static final String FILE3 = "abcXYZ123ABCxyz";
    private static final String FILE4 = "abc123ABC#@!?";
    private static final String FILE5 = "abcABC hello? I am,";
    private static final String FILE6 = "adcyu*srtud *bjkk7?^";
    private static final String FILE10 = "abc  tuvw.XYZ";
    private static final String FILE20 = "Howdy Billy, are you going to take cs6300 and cs6400 next semester?";
    private static final String FILE30 = "abcXYZ123ABCxyz";
    private static final String FILE40 = "AbcXYZ\nabc\nXYZcba";
    private static final String FILE50 = " ";
    private static final String FILE60 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String FILE70 = "0123456789";
    private static final String FILE80 = "Let's try some **special**  %!(characters)!% ###\r" +
            "and line breaks^$@ \r" +
            "in \\1/ file;\r" +
            ":-)";
    private static final String FILE90 = "Up with the white and gold\r" +
            "Down with the red and black\r" +
            "Georgia Tech is out for a victory\r" +
            "We'll drop a battle axe on georgia's head\r" +
            "When we meet her our team is sure to beat her\r" +
            "Down on the old farm there will be no sound\r" +
            "'Till our bow wows rips through the air\r" +
            "When the battle is over georgia's team will be found\r" +
            "With the Yellow Jacket's swarming 'round! Hey!";
    private static final String FILE100 = "Robert'); DROP TABLE students;--";
    private static final String FILE110 = ".*";
    private static final String FILE120 = " I’ve got a really good UDP joke to tell you, but I don’t know if you’ll get it. ";
    private static final String FILE130 = "3 Database Admins walked\n" +
            "into a NoSQL bar.\n"  +
            "A little later, they walked\n"  +
            "out because they couldn’t find a table.";
    private static final String FILE140 = "\naAbB\ncCdD eE\nfF";
    private static final String FILE150 = "";
    private static final String FILE160 = "abcabc123123abcabc123123 abcabc123123abcabc123123  abcabc123123abcabc123123";
    private static final String FILE170 = "Up with the white and gold\r" +
            "Down with the red and black\r" +
            "Georgia Tech is out for a victory\r";

    private static final String USAGE = "Usage: encode [-d string] [-w] [-x char] [-r string | -k string] [-c] <filename>";

    // test cases
    // Purpose: input filename is invalid  : encode
    // Frame #: 1
    @Test
    public void encodeTest1(){
        String args[] = null; //invalid argument
        Main.main(args);
        assertEquals("Usage: encode [-w [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    // Purpose: <Throw an error if there is no string after -r>
    // Frame #: 3
    @Test
    public void encodeTest2() throws Exception {
        File inputFile = createInputFile(FILE1);
        String args[] = {"-r", inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-w [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    // Purpose: <Throw an error if there is no string after -k>
    // Frame #: 4
    @Test
    public void encodeTest3() throws Exception {
        File inputFile = createInputFile(FILE1);

        String args[] = {"-k", inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-w [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    // Purpose: <Throw an error if there is a string after -c>
    // Frame #:
    @Test
    public void encodeTest4() throws Exception {
        File inputFile = createInputFile(FILE1);

        String args[] = {"-c", " ", inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-w [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    // Purpose: <Throw an error if there is a string after -c>
    // Frame #:
    @Test
    public void encodeTest5() throws Exception {
        File inputFile = createInputFile(FILE1);

        String args[] = {"-c", "fij", inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-w [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    //Purpose: test invalid opts 1
    //Frame #: 2
    @Test
    public void encodeTest6() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"    ", "             ", inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-w [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    //Purpose: test invalid opts 1
    //Frame #: 2
    @Test
    public void encodeTest7() throws Exception {
        File inputFile = createInputFile(FILE0);
        String args[] = {"", inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-w [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    //Purpose: test invalid opts 2
    //Frame #: 2
    @Test
    public void encodeTest8() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-r","-w","      ", inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-w [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    //Purpose: test invalid opts 4
    //Frame #: 2
    @Test
    public void encodeTest9() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-r","-c", inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-w [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    //Purpose: test invalid opts 4
    //Frame #: 2
    @Test
    public void encodeTest10() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-r","-w", inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-w [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    //Purpose: test invalid opts 4
    //Frame #: 2
    @Test
    public void encodeTest11() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-r","-k", "", inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-w [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    //Purpose: test invalid opts 2
    //Frame #: 2
    @Test
    public void encodeTest12() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-k","-w","      ", inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-w [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    //Purpose: test invalid opts 4
    //Frame #: 2
    @Test
    public void encodeTest13() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-k","-c", inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-w [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    //Purpose: test invalid opts 4
    //Frame #: 2
    @Test
    public void encodeTest14() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-k","-w", inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-w [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    //Purpose: test invalid opts 4
    //Frame #: 2
    @Test
    public void encodeTest15() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-k","-r", "", inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-w [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    // Purpose: test without OPT (default to -w and use all non-alphabetic characters) : encode file1.txt
    // Frame #: 46
    @Test
    public void encodeTest16() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {inputFile.getPath()};
        Main.main(args);
        String expected = "ydwoH ylliB, era uoy gniog ot ekat retupmoc";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: test -w without delimiter : encode -w file1.txt
    // Frame #: 6
    @Test
    public void encodeTest17() throws Exception {
        File inputFile = createInputFile(FILE6);
        String args[] = {"-w", "k", "-c", inputFile.getPath()};
        Main.main(args);
        String expected = "JB* DUTRS*UYCDA  ^?7 ";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    //Purpose: test invalid opts 1
    //Frame #: 2
    @Test
    public void encodeTest18() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"   -w", inputFile.getPath()};
        Main.main(args);
        String expected = "ydwoH ,ylliB era uoy gniog ot ekat retupmoc ";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: test "-w" with a single character delimiter : encode -w 'o' file1.txt
    // Frame #: 13
    @Test
    public void encodeTest19() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-k","go", inputFile.getPath()};
        Main.main(args);
        String expected = "ooggoo";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: test single argument and option with "-w" but many character delimiters : encode -w 'il' file1.txt
    // Frame #: 13
    @Test
    public void encodeTest20() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-w","il", inputFile.getPath()};
        Main.main(args);
        String expected = "B ydwoH retupmoc ekat ot gniog uoy era ,yl ";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: test single argument and option with "-w" but many character delimiters : encode -w 'il' file1.txt
    // Frame #: 13
    @Test
    public void encodeTest21() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-w","il","-c", inputFile.getPath()};
        Main.main(args);
        String expected = "b YDWOh RETUPMOC EKAT OT GNIOG UOY ERA ,YL ";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: test single argument and option with "-r" with string : encode -r 'a' file1.txt
    // Frame #: 21
    @Test
    public void encodeTest22() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-r","a", inputFile.getPath()};
        Main.main(args);
        String expected = "Howdy Billy, re you going to tke computer";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: test single argument and option with "-r" with string : encode -r 'a' file1.txt
    // Frame #: 21
    @Test
    public void encodeTest23() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-r","Ilh", inputFile.getPath()};
        Main.main(args);
        String expected = "owdy By, are you gong to take computer";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: test single argument and option with "-r" with multiple string : encode -r 'Ve' file1.txt
    // Frame #: 21
    @Test
    public void encodeTest24() throws Exception {
        File inputFile = createInputFile(FILE3);
        String args[] = {"-r","aY", inputFile.getPath()};
        Main.main(args);
        String expected = "bcXZ123BCxz";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: test single argument and option with "-r" but with string only contains both digits and letters : encode -m "9XY8" file1.txt
    // Frame #: 21
    @Test
    public void encodeTest25() throws Exception {
        File inputFile = createInputFile(FILE3);
        String args[] = {"-r","abc", inputFile.getPath()};
        Main.main(args);
        String expected = "XYZ123xyz";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: test single argument and option with "-r" but with string  contains other characters : encode -r "$LAN1991" file1.txt
    // Frame #: 21
    @Test
    public void encodeTest26() throws Exception {
        File inputFile1 = createInputFile(FILE4);
        String args[] = {"-r","Ab", inputFile1.getPath()};
        Main.main(args);
        String expected = "c123C#@!?";
        String actual = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: test single argument and option with "-k" with string : encode -k 'a' file1.txt
    // Frame #: 29
    @Test
    public void encodeTest27() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-k","ahb", inputFile.getPath()};
        Main.main(args);
        String expected = "HBaa";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: test single argument and option with "-k" with multiple string : encode -k 'Ve' file1.txt
    // Frame #: 29
    @Test
    public void encodeTest28() throws Exception {
        File inputFile = createInputFile(FILE3);
        String args[] = {"-k","aX", inputFile.getPath()};
        Main.main(args);
        String expected1 = "aXAx";
        String actual1 = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: test single argument and option with "-k" but with string only contains both digits and letters : encode -k "9XY8" file1.txt
    // Frame #: 29
    @Test
    public void encodeTest29() throws Exception {
        File inputFile = createInputFile(FILE3);
        String args[] = {"-k","1XY3", inputFile.getPath()};
        Main.main(args);
        String expected = "XY13xy";
        String actual1 = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual1);
    }

    // Purpose: test single argument and option with "-k" but with string  contains other characters : encode -k "$LAN1991" file1.txt
    // Frame #: 29
    @Test
    public void encodeTest30() throws Exception {
        File inputFile = createInputFile(FILE4);
        String args[] = {"-k", "?A", inputFile.getPath()};
        Main.main(args);
        String expected = "aA?";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: test single argument and option : encode -c file1.txt
    // Frame #: 39
    @Test
    public void encodeTest31() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-c", inputFile.getPath()};
        Main.main(args);
        String expected1 = "hOWDY bILLY, ARE YOU GOING TO TAKE COMPUTER";
        String actual1 = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: test single argument and option : encode -c file1.txt
    // Frame #: 39
    @Test
    public void encodeTest32() throws Exception {
        File inputFile = createInputFile(FILE3);
        String args[] = {"-c", inputFile.getPath()};
        Main.main(args);
        String expected1 = "ABCxyz123abcXYZ";
        String actual1 = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: test single argument and option : encode -c file1.txt
    // Frame #: 39
    @Test
    public void encodeTest33() throws Exception {
        File inputFile = createInputFile(FILE4);
        String args[] = {"-c", inputFile.getPath()};
        Main.main(args);
        String expected1 = "ABC123abc#@!?";
        String actual1 = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: test two argument and option : encode -w -c file1.txt
    // Frame #: 44
    @Test
    public void encodeTest34() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-w","-r","bou", inputFile.getPath()};
        Main.main(args);
        String expected1 = "ydwH ,ylli era y gnig t ekat retpmc ";
        String actual1 = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: test two argument and option : encode -w -c file1.txt
    // Frame #: 44
    @Test
    public void encodeTest35() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-w","-k","Hboiu","-c", inputFile.getPath()};
        Main.main(args);
        String expected1 = "OhIbUOIOOUO";
        String actual1 = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: test two argument and option : encode -w -c file1.txt
    // Frame #: 44
    @Test
    public void encodeTest36() throws Exception {
        File inputFile = createInputFile(FILE5);
        String args[] = {"-k","a", inputFile.getPath()};
        Main.main(args);
        String expected1 = "aAa";
        String actual1 = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: To provide an example of a test case format
    // Frame #: Instructor example 3 from assignment directions
    @Test
    public void mainTest3() throws Exception {
        File inputFile = createInputFile(FILE20);

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
        File inputFile = createInputFile(FILE30);

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
        String args[]  = new String[0]; //no arguments
        Main.main(args);
        assertEquals(USAGE, errStream.toString().trim());
    }

    // Purpose: To provide an example of a test case format
    // Frame #: Instructor example 5 from assignment directions
    @Test
    public void mainTest6() throws Exception {
        File inputFile = createInputFile(FILE1);
        //space and period is a delimiter. replace this with !.
        String args[] = {"-d", " .", "-x", "!", inputFile.getPath()};
        Main.main(args);

        String expected = "abc!tuvw!XYZ";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertTrue(errStream.toString().isBlank());
        assertTrue(outStream.toString().isBlank());
    }

    // Purpose: New Test Case for Refactoring
    // Frame #: Instructor Provided New Test Case
    @Test
    public void mainTest7() throws Exception {
        File inputFile = createInputFile(FILE2);

        String args[] = {"-x", "*", inputFile.getPath()};
        Main.main(args);

        String expected = "Howdy*Billy,*are*you*going*to*take*cs6300*and*cs6400*next*semester?";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: New Test Case for Refactoring
    // Frame #: Instructor Provided New Test Case
    @Test
    public void mainTest8() throws Exception {
        File inputFile = createInputFile(FILE80);

        String args[] = {"-x", "o", inputFile.getPath()};
        Main.main(args);

        String expected = "Let'sotryosomeo**special**o%!(characters)!%o###o" +
                "andolineobreaks^$@o" +
                "ino\\1/ofile;o" +
                ":-)";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: New Test Case for Refactoring
    // Frame #: Instructor Provided New Test Case
    @Test
    public void mainTest9() throws Exception {
        File inputFile = createInputFile(FILE160);

        String args[] = {"-d", "a1", "-x", "z", "-k", "c", inputFile.getPath()};
        Main.main(args);

        String expected = "zczczczcz zczczczcz  zczczczcz";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: New Test Case for Refactoring
    // Frame #: Instructor Provided New Test Case
    @Test
    public void mainTest10() throws Exception {
        File inputFile = createInputFile(FILE90);

        String args[] = {"-d", "a", inputFile.getPath()};
        Main.main(args);

        String expected = FILE90;

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: New Test Case for Refactoring
    // Frame #: Instructor Provided New Test Case
    @Test
    public void mainTest11() throws Exception {
        File inputFile = createInputFile(FILE50);

        String args[] = {"-w", inputFile.getPath()};
        Main.main(args);

        String expected = FILE50;

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: New Test Case for Refactoring
    // Frame #: Instructor Provided New Test Case
    @Test
    public void mainTest12() throws Exception {
        File inputFile = createInputFile(FILE50);

        String args[] = {"-k", "abc", "-d", "xyz123%$", "-w", inputFile.getPath()};
        Main.main(args);

        String expected = FILE50;

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertTrue(outStream.toString().isBlank());
        assertTrue(errStream.toString().isBlank());
    }

    // Purpose: New Test Case for Refactoring
    // Frame #: Instructor Provided New Test Case
    @Test
    public void mainTest13() throws Exception {
        File inputFile = createInputFile(FILE60);

        String args[] = {"-k", "aeiou123", "-r", "xyz", inputFile.getPath()};
        Main.main(args);

        String expected = "FILE60";

        String actual = getFileContent(inputFile.getPath());

        assertEquals(USAGE, errStream.toString().trim());
        assertTrue(outStream.toString().isBlank());
    }

    // Purpose: New Test Case for Refactoring
    // Frame #: Instructor Provided New Test Case
    @Test
    public void mainTest14() throws Exception {
        File inputFile = createInputFile(FILE130);

        String args[] = {"-r", "xyz", "-x", inputFile.getPath()};
        Main.main(args);

        String expected = FILE130;

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertTrue(outStream.toString().isBlank());
        assertEquals(USAGE, errStream.toString().trim());
    }

    // Purpose: New Test Case for Refactoring
    // Frame #: Instructor Provided New Test Case
    @Test
    public void mainTest15() throws Exception {
        File inputFile = createInputFile(FILE130);

        String args[] = {"-w", inputFile.getPath()};
        Main.main(args);

        String expected = "3 esabataD snimdA deklaw\n" +
                "otni a LQSoN .rab\n"  +
                "A elttil ,retal yeht deklaw\n"  +
                "tuo esuaceb yeht t’ndluoc dnif a .elbat";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: New Test Case for Refactoring
    // Frame #: Instructor Provided New Test Case
    @Test
    public void mainTest16() throws Exception {
        File inputFile = createInputFile(FILE20);

        String args[] = {"-x", " ", inputFile.getPath()};
        Main.main(args);

        String expected = " aAbB cCdD eE fF";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: New Test Case for Refactoring
    // Frame #: Instructor Provided New Test Case
    @Test
    public void mainTest17() throws Exception {
        File inputFile = createInputFile(FILE100);

        String args[] = {"-k", "Robert", "-c", inputFile.getPath()};
        Main.main(args);

        String expected = "rOBERT'); ro tbe TET;--";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: New Test Case for Refactoring
    // Frame #: Instructor Provided New Test Case
    @Test
    public void mainTest18() throws Exception {
        File inputFile = createInputFile(FILE80);

        String args[] = {"-k", "ABCDE", inputFile.getPath()};
        Main.main(args);

        String expected = "e'  e **eca**  %!(caace)!% ###\r" +
                "ad e bea^$@ \r" +
                " \\/ e;\r" +
                ":-)";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: New Test Case for Refactoring
    // Frame #: Instructor Provided New Test Case
    @Test
    public void mainTest19() throws Exception {
        File inputFile = createInputFile(FILE120);

        String args[] = {"-c", "-r", "UDP", inputFile.getPath()};
        Main.main(args);

        String expected = " i’VE GOT A REALLY GOO  JOKE TO TELL YO, BT i ON’T KNOW IF YO’LL GET IT. ";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: New Test Case for Refactoring
    // Frame #: Instructor Provided New Test Case
    @Test
    public void mainTest20() throws Exception {
        File inputFile = createInputFile(FILE100);

        String args[] = {inputFile.getPath()};
        Main.main(args);

        String expected = "treboR'); PORD ELBAT stneduts;--";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: New Test Case for Refactoring
    // Frame #: Instructor Provided New Test Case
    @Test
    public void mainTest21() throws Exception {
        File inputFile = createInputFile(FILE170);

        String args[] = {"-d", " ", "-w", "-x", "+", inputFile.getPath()};
        Main.main(args);

        String expected = "pU+htiw+eht+etihw+dna+nwoD\rdlog" +
                "+htiw+eht+der+dna+aigroeG\rkcalb" +
                "+hceT+si+tuo+rof+a+\ryrotciv";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: New Test Case for Refactoring
    // Frame #: Instructor Provided New Test Case
    @Test
    public void mainTest22() throws Exception {
        File inputFile = createInputFile(FILE110);

        String args[] = {"-r", "abcdefghijklmnopqrstuvwxyz", inputFile.getPath()};
        Main.main(args);

        String expected = FILE110;

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertTrue(errStream.toString().trim().isBlank());
    }

    // Purpose: New Test Case for Refactoring
    // Frame #: Instructor Provided New Test Case
    @Test
    public void mainTest23() throws Exception {
        File inputFile = createInputFile(FILE120);

        String args[] = {"-c-k", "abc", inputFile.getPath()};
        Main.main(args);

        String expected = FILE120;

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertEquals(USAGE, errStream.toString().trim());
    }

    // Purpose: New Test Case for Refactoring
    // Frame #: Instructor Provided New Test Case
    @Test
    public void mainTest24() throws Exception {
        File inputFile = createInputFile(FILE90);

        String args[] = {"-k", "abc", "-z", inputFile.getPath()};
        Main.main(args);

        String expected = FILE90;

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertEquals(USAGE, errStream.toString().trim());
    }

    // Purpose: New Test Case for Refactoring
    // Frame #: Instructor Provided New Test Case
    @Test
    public void mainTest25() throws Exception {

        String args[] = {"-a", "filedoesnotexist.txt"};
        Main.main(args);

        assertEquals("File Not Found", errStream.toString().trim());
    }

    // Purpose: New Test Case for Refactoring
    // Frame #: Instructor Provided New Test Case
    @Test
    public void mainTest26() throws Exception {

        File inputFile = createInputFile(FILE150);

        String args[] = {"-k", "abc", inputFile.getPath()};
        Main.main(args);

        String expected = FILE150;

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertTrue(errStream.toString().trim().isBlank());
    }

    // Purpose: New Test Case for Refactoring
    // Frame #: Instructor Provided New Test Case
    @Test
    public void mainTest27() throws Exception {

        File inputFile = createInputFile(FILE160);

        String args[] = {"-d", "abc", "-r", "abc", inputFile.getPath()};
        Main.main(args);

        String expected = FILE160;

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertTrue(errStream.toString().trim().isBlank());
    }

    // Purpose: New Test Case for Refactoring
    // Frame #: Instructor Provided New Test Case
    @Test
    public void mainTest28() throws Exception {

        File inputFile = createInputFile(FILE90);

        String args[] = {"-c", inputFile.getPath()};
        Main.main(args);

        String expected = "uP WITH THE WHITE AND GOLD " +
                "dOWN WITH THE RED AND BLACK " +
                "gEORGIA tECH IS OUT FOR A VICTORY " +
                "wE'LL DROP A BATTLE AXE ON GEORGIA'S HEAD " +
                "wHEN WE MEET HER OUR TEAM IS SURE TO BEAT HER " +
                "dOWN ON THE OLD FARM THERE WILL BE NO SOUND " +
                "'tILL OUR BOW WOWS RIPS THROUGH THE AIR " +
                "wHEN THE BATTLE IS OVER GEORGIA'S TEAM WILL BE FOUND " +
                "wITH THE yELLOW jACKET'S SWARMING 'ROUND! hEY!";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: New Test Case for Refactoring
    // Frame #: Instructor Provided New Test Case
    @Test
    public void mainTest29() throws Exception {

        File inputFile = createInputFile(FILE60);

        String args[] = {"-d", "z", "-k", "aeiou", inputFile.getPath()};
        Main.main(args);

        String expected = "aeiouzAEIOU";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: New Test Case for Refactoring
    // Frame #: Instructor Provided New Test Case
    @Test
    public void mainTest30() throws Exception {

        File inputFile = createInputFile(FILE80);

        String args[] = {"-r", "*s*", inputFile.getPath()};
        Main.main(args);

        String expected = "Let' try ome **pecial**  %!(character)!% ###\r" +
                "and line break^$@ \r" +
                "in \\1/ file;\r" +
                ":-)";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }
}