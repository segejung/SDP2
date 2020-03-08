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
    private static final String FILE1 = "abc tuvw.XYZ";
    private static final String FILE2 = "Howdy Billy, are you going to take cs6300 and cs6400 next semester?";
    private static final String FILE3 = "abcXYZ123ABCxyz";


    // test cases

    /*
     *   TEST CASES
     */

    // Purpose: <Throw an error if there is no string after -r>
    // Frame #: <test case number in the catpart.txt.tsl of Part I>
    @Test
    public void encodeTest1() throws Exception {
        File inputFile = createInputFile(FILE1);

        String args[] = {"-r", inputFile.getPath()};
        Main.main(args);

        String expected = "Error";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("Need to enter string after -r!", expected, actual);
    }

    // Purpose: <Throw an error if there is no string after -k>
    // Frame #: <test case number in the catpart.txt.tsl of Part I>
    @Test
    public void encodeTest2() throws Exception {
        File inputFile = createInputFile(FILE1);

        String args[] = {"-k", inputFile.getPath()};
        Main.main(args);

        String expected = "Error";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("Need to enter string after -k!", expected, actual);
    }

    // Purpose: <concise description of the purpose of the test>
    // Frame #: <test case number in the catpart.txt.tsl of Part I>
    @Test
    public void encodeTest3() throws Exception {
        File inputFile = createInputFile(FILE1);

        String args[] = {inputFile.getPath()};
        Main.main(args);

        String expected = "cba wvut.ZYX";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: <concise description of the purpose of the test>
    // Frame #: <test case number in the catpart.txt.tsl of Part I>
    @Test
    public void encodeTest4() throws Exception {
        File inputFile = createInputFile(FILE1);

        String args[] = {inputFile.getPath()};
        Main.main(args);

        String expected = "cba wvut.ZYX";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: <concise description of the purpose of the test>
    // Frame #: <test case number in the catpart.txt.tsl of Part I>
    @Test
    public void encodeTest5() throws Exception {
        File inputFile = createInputFile(FILE1);

        String args[] = {inputFile.getPath()};
        Main.main(args);

        String expected = "cba wvut.ZYX";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: <concise description of the purpose of the test>
    // Frame #: <test case number in the catpart.txt.tsl of Part I>
    @Test
    public void encodeTest6() throws Exception {
        File inputFile = createInputFile(FILE1);

        String args[] = {inputFile.getPath()};
        Main.main(args);

        String expected = "cba wvut.ZYX";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: <concise description of the purpose of the test>
    // Frame #: <test case number in the catpart.txt.tsl of Part I>
    @Test
    public void encodeTest7() throws Exception {
        File inputFile = createInputFile(FILE1);

        String args[] = {inputFile.getPath()};
        Main.main(args);

        String expected = "cba wvut.ZYX";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: <concise description of the purpose of the test>
    // Frame #: <test case number in the catpart.txt.tsl of Part I>
    @Test
    public void encodeTest8() throws Exception {
        File inputFile = createInputFile(FILE1);

        String args[] = {inputFile.getPath()};
        Main.main(args);

        String expected = "cba wvut.ZYX";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: <concise description of the purpose of the test>
    // Frame #: <test case number in the catpart.txt.tsl of Part I>
    @Test
    public void encodeTest9() throws Exception {
        File inputFile = createInputFile(FILE1);

        String args[] = {inputFile.getPath()};
        Main.main(args);

        String expected = "cba wvut.ZYX";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: <concise description of the purpose of the test>
    // Frame #: <test case number in the catpart.txt.tsl of Part I>
    @Test
    public void encodeTest10() throws Exception {
        File inputFile = createInputFile(FILE1);

        String args[] = {inputFile.getPath()};
        Main.main(args);

        String expected = "cba wvut.ZYX";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: <concise description of the purpose of the test>
    // Frame #: <test case number in the catpart.txt.tsl of Part I>
    @Test
    public void encodeTest11() throws Exception {
        File inputFile = createInputFile(FILE1);

        String args[] = {inputFile.getPath()};
        Main.main(args);

        String expected = "cba wvut.ZYX";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: <concise description of the purpose of the test>
    // Frame #: <test case number in the catpart.txt.tsl of Part I>
    @Test
    public void encodeTest12() throws Exception {
        File inputFile = createInputFile(FILE1);

        String args[] = {inputFile.getPath()};
        Main.main(args);

        String expected = "cba wvut.ZYX";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: <concise description of the purpose of the test>
    // Frame #: <test case number in the catpart.txt.tsl of Part I>
    @Test
    public void encodeTest13() throws Exception {
        File inputFile = createInputFile(FILE1);

        String args[] = {inputFile.getPath()};
        Main.main(args);

        String expected = "cba wvut.ZYX";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: <concise description of the purpose of the test>
    // Frame #: <test case number in the catpart.txt.tsl of Part I>
    @Test
    public void encodeTest14() throws Exception {
        File inputFile = createInputFile(FILE1);

        String args[] = {inputFile.getPath()};
        Main.main(args);

        String expected = "cba wvut.ZYX";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: <concise description of the purpose of the test>
    // Frame #: <test case number in the catpart.txt.tsl of Part I>
    @Test
    public void encodeTest15() throws Exception {
        File inputFile = createInputFile(FILE1);

        String args[] = {inputFile.getPath()};
        Main.main(args);

        String expected = "cba wvut.ZYX";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }
}