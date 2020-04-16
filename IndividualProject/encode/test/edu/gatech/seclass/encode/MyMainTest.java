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
        File inputFile = createInputFile(FILE2);
        String args[] = {"-w", inputFile.getPath()};
        Main.main(args);
        String expected = "ydwoH ,ylliB era uoy gniog ot ekat retupmoc ";
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
        String args[] = {"-w","o", inputFile.getPath()};
        Main.main(args);
        String expected = "H y era ,ylliB ydw g u t gni c ekat  retupm ";
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
        String expected1 = "OhIbUOIOOUO";
        String actual1 = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
}