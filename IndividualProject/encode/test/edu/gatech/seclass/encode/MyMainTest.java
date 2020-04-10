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
    private static final String FILE2 = "Howdy Billy, are you going to take cs6300!!!";
    private static final String FILE3 = "abcXYZ123ABCxyz";
    private static final String FILE4 = "abc123ABC#@!?";
    private static final String FILE5 = "abcABC";

    // test cases
    //1
    // Purpose: input filename is invalid  : encode
    // Frame #: 1
    @Test
    public void encodeTest1(){
        String args[] = null; //invalid argument
        Main.main(args);
        assertEquals("Usage: encode [-w [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    //2
    // Purpose: <Throw an error if there is no string after -r>
    // Frame #: 3
    @Test
    public void encodeTest2() throws Exception {
        File inputFile = createInputFile(FILE1);

        String args[] = {"-r", inputFile.getPath()};
        Main.main(args);

        assertEquals("Usage: encode [-w [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    //3
    // Purpose: <Throw an error if there is no string after -k>
    // Frame #: 4
    @Test
    public void encodeTest3() throws Exception {
        File inputFile = createInputFile(FILE1);

        String args[] = {"-k", inputFile.getPath()};
        Main.main(args);

        assertEquals("Usage: encode [-w [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    //4
    //Purpose: test invalid opts 1
    //Frame #: 2
    @Test
    public void encodeTest5() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"    ", "             ", inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-w [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    //5
    //Purpose: test invalid opts 2
    //Frame #: 2
    @Test
    public void encodeTest6() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-r","-w","      ","-r", inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-w [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());

    }

    //6
    //Purpose: test invalid opts 3
    //Frame #: 2
    @Test
    public void encodeTest7() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-k","-w","-c","     ", inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-w [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    //7
    //Purpose: test invalid opts 4
    //Frame #: 2
    @Test
    public void encodeTest8() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-r","-w","      ","-r", inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-w [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    //8
    //Purpose: test invalid opts 5
    //Frame #: 3
    @Test
    public void encodeTest9() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-k","-w","-c","     ", inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-w [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    //9
    // Purpose: test the input file size that is 0
    // Frame #: 5
    @Test
    public void encodeTest10() throws Exception {
        File inputFile = createInputFile(FILE0);
        String args[] = {inputFile.getPath()};
        Main.main(args);
        String expected = "";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    //10
    // Purpose: test without OPT (default to -w and use all non-alphabetic characters) : encode file1.txt
    // Frame #: 46
    @Test
    public void encodeTest11() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {inputFile.getPath()};
        Main.main(args);
        String expected = "ydwoH ylliB, era uoy gniog ot ekat sc6300!!!";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    //11
    // Purpose: test -w without delimiter : encode -w file1.txt
    // Frame #: 6
    @Test
    public void encodeTest12() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-w", inputFile.getPath()};
        Main.main(args);
        String expected = "ydwoH ylliB, era uoy gniog ot ekat !!!0036sc";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    //12
    // Purpose: test "-w" with a single character delimiter : encode -w 'o' file1.txt
    // Frame #: 13
    @Test
    public void encodeTest13() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-w","o", inputFile.getPath()};
        Main.main(args);
        String expected = "Hoydw Billy, are you gogni to take cs6300!!!";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    //13
    // Purpose: test single argument and option with "-w" but many character delimiters : encode -w 'il' file1.txt
    // Frame #: 13
    @Test
    public void encodeTest14() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-w","il", inputFile.getPath()};
        Main.main(args);
        String expected = "Howdy Bilyl, are you going to take cs6300!!!";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    //14
    // Purpose: test single argument and option with "-r" with string : encode -r 'a' file1.txt
    // Frame #: 21
    @Test
    public void encodeTest15() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-r","a", inputFile.getPath()};
        Main.main(args);
        String expected = "Howdy Billy, re you going to tke cs6300!!!";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    //15
    // Purpose: test single argument and option with "-r" with multiple string : encode -r 'Ve' file1.txt
    // Frame #: 21
    @Test
    public void encodeTest16() throws Exception {
        File inputFile = createInputFile(FILE3);
        String args[] = {"-r","aY", inputFile.getPath()};
        Main.main(args);
        String expected = "bcXZ123BCxz";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    //16
    // Purpose: test single argument and option with "-r" but with string only contains both digits and letters : encode -m "9XY8" file1.txt
    // Frame #: 21
    @Test
    public void encodeTest17() throws Exception {
        File inputFile = createInputFile(FILE3);
        String args[] = {"-r","1X2", inputFile.getPath()};
        Main.main(args);
        String expected = "abcYZ123ABCyz";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    //17
    // Purpose: test single argument and option with "-r" but with string  contains other characters : encode -r "$LAN1991" file1.txt
    // Frame #: 21
    @Test
    public void encodeTest18() throws Exception {
        File inputFile1 = createInputFile(FILE4);
        String args[] = {"-r","@Ab12", inputFile1.getPath()};
        Main.main(args);
        String expected = "c123C#@!?";
        String actual = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    //18
    // Purpose: test single argument and option with "-k" with string : encode -k 'a' file1.txt
    // Frame #: 29
    @Test
    public void encodeTest19() throws Exception {
        File inputFile = createInputFile(FILE4);
        String args[] = {"-k","a", inputFile.getPath()};
        Main.main(args);
        String expected = "a123#@!?";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }



    //19
    // Purpose: test single argument and option with "-k" with multiple string : encode -k 'Ve' file1.txt
    // Frame #: 29
    @Test
    public void encodeTest20() throws Exception {
        File inputFile = createInputFile(FILE3);
        String args[] = {"-k","aX", inputFile.getPath()};
        Main.main(args);
        String expected1 = "aX123Ax";
        String actual1 = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    //20
    // Purpose: test single argument and option with "-k" but with string only contains both digits and letters : encode -k "9XY8" file1.txt
    // Frame #: 29
    @Test
    public void encodeTest21() throws Exception {
        File inputFile = createInputFile(FILE3);
        String args[] = {"-k","1XY3", inputFile.getPath()};
        Main.main(args);
        String expected = "XY123xy";
        String actual1 = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual1);
    }

    //21
    // Purpose: test single argument and option with "-k" but with string  contains other characters : encode -k "$LAN1991" file1.txt
    // Frame #: 29
    @Test
    public void encodeTest22() throws Exception {
        File inputFile = createInputFile(FILE4);
        String args[] = {"-k", "?A", inputFile.getPath()};
        Main.main(args);
        String expected = "a123A#@!?";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    //22
    // Purpose: test single argument and option : encode -c file1.txt
    // Frame #: 39
    @Test
    public void encodeTest23() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-c", inputFile.getPath()};
        Main.main(args);
        String expected1 = "hOWDY bILLY, ARE YOU GOING TO TAKE CS6300!!!";
        String actual1 = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    //23
    // Purpose: test two argument and option : encode -w -c file1.txt
    // Frame #: 44
    @Test
    public void encodeTest24() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-w","c", inputFile.getPath()};
        Main.main(args);
        String expected1 = "YDWOh YLLIb, ERA UOY GNIOG OT EKAT SC6300!!!";
        String actual1 = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    //25
    // Purpose: test single argument and option with "-k" with string : encode -k 'a' file1.txt
    // Frame #: 29
    @Test
    public void encodeTest25() throws Exception {
        File inputFile = createInputFile(FILE5);
        String args[] = {"-k","a", inputFile.getPath()};
        Main.main(args);
        String expected = "aA";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }
}