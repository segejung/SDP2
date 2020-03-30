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
    private static final String FILE2 = "Howdy Billy, are you going to take cs6300 and cs6400 next semester?";
    private static final String FILE3 = "abcXYZ123ABCxyz";


    // test cases
    // Purpose: input filename is invalid  : encode
    // Frame #: 1
    @Test
    public void encodeTest1(){
        String args[] = null; //invalid argument
        Main.main(args);
        assertEquals("Usage: Capitalize  [-w [string]] [-m string] [-f] [-i|-I] [-o] <filename>", errStream.toString().trim());
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

    //Purpose: test invalid opts 1
    //Frame #: 2
    @Test
    public void encodeTest5() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"    ", "             ", inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-w [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    //Purpose: test invalid opts 2
    //Frame #: 2
    @Test
    public void encodeTest6() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-r","-w","      ","-r", inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-w [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());

    }

    //Purpose: test invalid opts 3
    //Frame #: 2
    @Test
    public void encodeTest7() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-k","-w","-c","     ", inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-w [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    //Purpose: test invalid opts 4
    //Frame #: 2
    @Test
    public void encodeTest8() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-r","-w","      ","-r", inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-w [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    //Purpose: test invalid opts 5
    //Frame #: 3
    @Test
    public void encodeTest9() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-k","-w","-c","     ", inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-w [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    // Purpose: test the input file size that is 0
    // Frame #: 5
    @Test
    public void encodeTest10() throws Exception {
        File inputFile = createInputFile(FILE0);
        String args[] = {inputFile.getPath()};
        Main.main(args);
        String expected1 = "";
        String actual1 = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: test without OPT (it will default to -w and use all non-alphabetic characters as delimiters) : encode file1.txt
    // Frame #: 46
    @Test
    public void encodeTest11() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {inputFile.getPath()};
        Main.main(args);
        String expected1 = "olleh kcaJ, I evol uoy!!!!";
        String actual1 = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: test -w without delimiter : encode -w file1.txt
    // Frame #: 6
    @Test
    public void encodeTest12() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-w", inputFile.getPath()};
        Main.main(args);
        String expected1 = "olleh ,kcaJ I evol !!!!uoy";
        String actual1 = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: test "-w" with a single character delimiter : encode -w 'a' file1.txt
    // Frame #: 13
    @Test
    public void encodeTest13() throws Exception {
        File inputFile = createInputFile();
        String args[] = {"-w","a", inputFile.getPath()};
        Main.main(args);
        String expected1 = "J ollehkca, I love you!!!!";
        String actual1 = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: test single argument and option with "-w" but many character delimiters : encode -w 'ae' file1.txt
    // Frame #: 13
    @Test
    public void encodeTest14() throws Exception {
        File inputFile = createInputFile();
        String args[] = {"-w","ae", inputFile.getPath()};
        Main.main(args);
        String expected1 = "heLlo JaCk, I love you!!!!";
        String actual1 = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: test single argument and option with "-r" with string : encode -r 'a' file1.txt
    // Frame #: 21
    @Test
    public void encodeTest15() throws Exception {
        File inputFile = createInputFile();
        String args[] = {"-r","a", inputFile.getPath()};
        Main.main(args);
        String expected1 = "hello Jck, I love you!!!!";
        String actual1 = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    // Purpose: test single argument and option with "-r" with multiple string : encode -r 'Ve' file1.txt
    // Frame #: 21
    @Test
    public void encodeTest16() throws Exception {
        File inputFile = createInputFile();
        String args[] = {"-r","Ve", inputFile.getPath()};
        Main.main(args);
        String expected1 = "OoO";
        String actual1 = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: test single argument and option with "-r" but with string only contains both digits and letters : encode -m "9XY8" file1.txt
    // Frame #: 21
    @Test
    public void encodeTest17() throws Exception {
        File inputFile = createInputFile();
        String args[] = {"-r","9XY8", inputFile.getPath()};
        Main.main(args);
        String expected1 = "9898!!!";
        String actual1 = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: test single argument and option with "-r" but with string  contains other characters : encode -r "$LAN1991" file1.txt
    // Frame #: 21
    @Test
    public void encodeTest18() throws Exception {
        File inputFile1 = createInputFile();
        String args[] = {"-r","$LAN1991", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "$$$$$1991$1991!!!";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: test single argument and option with "-k" with string : encode -k 'a' file1.txt
    // Frame #: 29
    @Test
    public void encodeTest19() throws Exception {
        File inputFile = createInputFile();
        String args[] = {"-k","a", inputFile.getPath()};
        Main.main(args);
        String expected1 = "a!!!!";
        String actual1 = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    // Purpose: test single argument and option with "-k" with multiple string : encode -k 'Ve' file1.txt
    // Frame #: 29
    @Test
    public void encodeTest20() throws Exception {
        File inputFile = createInputFile();
        String args[] = {"-k","Ve", inputFile.getPath()};
        Main.main(args);
        String expected1 = "veevvvveev";
        String actual1 = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: test single argument and option with "-k" but with string only contains both digits and letters : encode -k "9XY8" file1.txt
    // Frame #: 29
    @Test
    public void encodeTest21() throws Exception {
        File inputFile = createInputFile();
        String args[] = {"-k","9XY8", inputFile.getPath()};
        Main.main(args);
        String expected = "9xy89xY8!!!";
        String actual1 = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual1);
    }

    // Purpose: test single argument and option with "-k" but with string  contains other characters : encode -k "$LAN1991" file1.txt
    // Frame #: 29
    @Test
    public void encodeTest22() throws Exception {
        File inputFile1 = createInputFile();
        String args[] = {"-k","$LAN1991", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "$$$$$lAn1991$lAn1991!!!";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    // Purpose: test single argument and option : encode -c file1.txt
    // Frame #: 39
    @Test
    public void encodeTest23() throws Exception {
        File inputFile = createInputFile();
        String args[] = {"-c", inputFile.getPath()};
        Main.main(args);
        String expected1 = "HELLO jACK, i LOVE YOU!!!!";
        String actual1 = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: test two argument and option : encode -w -c file1.txt
    // Frame #: 44
    @Test
    public void encodeTest24() throws Exception {
        File inputFile = createInputFile();
        String args[] = {"-w","c", inputFile.getPath()};
        Main.main(args);
        String expected1 = "OLLEH KCAj, i EVOL UOY!!!!";
        String actual1 = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

}