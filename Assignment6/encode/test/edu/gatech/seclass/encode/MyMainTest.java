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

    // created for test cases.
    private File createInputFile0() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("");

        fileWriter.close();
        return file1;
    }

    private File createInputFile1() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("hello Jack, I love you!!!!");

        fileWriter.close();
        return file1;
    }

    private File createInputFile2() throws Exception {
        File file2 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file2);

        fileWriter.write("veevvvOoOveev");

        fileWriter.close();
        return file2;
    }
    private File createInputFile3() throws Exception {
        File file3 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file3);

        fileWriter.write("9xy89xY8!!!");

        fileWriter.close();
        return file3;
    }
    private File createInputFile4() throws Exception {
        File file3 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file3);

        fileWriter.write("$$$$$lAn1991$lAn1991!!!");

        fileWriter.close();
        return file3;
    }
    private File createInputFile5() throws Exception {
        File file3 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file3);

        fileWriter.write("I have a dream to be a Software engineer");
        fileWriter.close();
        return file3;
    }
    private File createInputFile6() throws Exception {
        File file3 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file3);

        fileWriter.write("9999i am the king## nothing");
        fileWriter.close();
        return file3;
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
        File inputFile1 = createInputFile6();
        String args[] = {"    ", "             ", inputFile1.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-w [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    //Purpose: test invalid opts 2
    //Frame #: 2
    @Test
    public void encodeTest6() throws Exception {
        File inputFile1 = createInputFile6();
        String args[] = {"-r","-w","      ","-r", inputFile1.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-w [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());

    }

    //Purpose: test invalid opts 3
    //Frame #: 2
    @Test
    public void encodeTest7() throws Exception {
        File inputFile1 = createInputFile6();
        String args[] = {"-k","-w","-c","     ", inputFile1.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-w [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    //Purpose: test invalid opts 4
    //Frame #: 2
    @Test
    public void encodeTest8() throws Exception {
        File inputFile1 = createInputFile6();
        String args[] = {"-r","-w","      ","-r", inputFile1.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-w [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    //Purpose: test invalid opts 5
    //Frame #: 3
    @Test
    public void encodeTest9() throws Exception {
        File inputFile1 = createInputFile6();
        String args[] = {"-k","-w","-c","     ", inputFile1.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-w [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    // Purpose: test the input file size that is 0
    // Frame #: 5
    @Test
    public void encodeTest10() throws Exception {
        File inputFile0 = createInputFile0();
        String args[] = {inputFile0.getPath()};
        Main.main(args);
        String expected1 = "";
        String actual1 = getFileContent(inputFile0.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: test without OPT (it will default to -w and use all non-alphabetic characters as delimiters) : encode file1.txt
    // Frame #: 46
    @Test
    public void encodeTest11() throws Exception {
        File inputFile1 = createInputFile1();
        String args[] = {inputFile1.getPath()};
        Main.main(args);
        String expected1 = "olleh kcaJ, I evol uoy!!!!";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: test -w without delimiter : encode -w file1.txt
    // Frame #: 6
    @Test
    public void encodeTest12() throws Exception {
        File inputFile1 = createInputFile1();
        String args[] = {"-w", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "olleh ,kcaJ I evol !!!!uoy";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: test "-w" with a single character delimiter : encode -w 'a' file1.txt
    // Frame #: 13
    @Test
    public void encodeTest13() throws Exception {
        File inputFile1 = createInputFile1();
        String args[] = {"-w","a", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "J ollehkca, I love you!!!!";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: test single argument and option with "-w" but many character delimiters : encode -w 'ae' file1.txt
    // Frame #: 13
    @Test
    public void encodeTest14() throws Exception {
        File inputFile1 = createInputFile1();
        String args[] = {"-w","ae", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "heLlo JaCk, I love you!!!!";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: test single argument and option with "-r" with string : encode -r 'a' file1.txt
    // Frame #: 21
    @Test
    public void encodeTest15() throws Exception {
        File inputFile1 = createInputFile1();
        String args[] = {"-r","a", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "hello Jck, I love you!!!!";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    // Purpose: test single argument and option with "-r" with multiple string : encode -r 'Ve' file1.txt
    // Frame #: 21
    @Test
    public void encodeTest16() throws Exception {
        File inputFile1 = createInputFile2();
        String args[] = {"-r","Ve", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "OoO";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: test single argument and option with "-r" but with string only contains both digits and letters : encode -m "9XY8" file1.txt
    // Frame #: 21
    @Test
    public void encodeTest17() throws Exception {
        File inputFile1 = createInputFile3();
        String args[] = {"-r","9XY8", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "9898!!!";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: test single argument and option with "-r" but with string  contains other characters : encode -r "$LAN1991" file1.txt
    // Frame #: 21
    @Test
    public void encodeTest18() throws Exception {
        File inputFile1 = createInputFile4();
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
        File inputFile1 = createInputFile1();
        String args[] = {"-k","a", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "a!!!!";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    // Purpose: test single argument and option with "-k" with multiple string : encode -k 'Ve' file1.txt
    // Frame #: 29
    @Test
    public void encodeTest20() throws Exception {
        File inputFile1 = createInputFile2();
        String args[] = {"-k","Ve", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "veevvvveev";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: test single argument and option with "-k" but with string only contains both digits and letters : encode -k "9XY8" file1.txt
    // Frame #: 29
    @Test
    public void encodeTest21() throws Exception {
        File inputFile1 = createInputFile3();
        String args[] = {"-k","9XY8", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "9xy89xY8!!!";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: test single argument and option with "-k" but with string  contains other characters : encode -k "$LAN1991" file1.txt
    // Frame #: 29
    @Test
    public void encodeTest22() throws Exception {
        File inputFile1 = createInputFile4();
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
        File inputFile1 = createInputFile1();
        String args[] = {"-c", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "HELLO jACK, i LOVE YOU!!!!";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: test two argument and option : encode -w -c file1.txt
    // Frame #: 44
    @Test
    public void encodeTest24() throws Exception {
        File inputFile1 = createInputFile1();
        String args[] = {"-w","c", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "OLLEH KCAj, i EVOL UOY!!!!";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    /*
    // Purpose: test three arguments and options with "-w","-m" and "-f" , -w and -m with no content
    // Frame #: 37
    @Test
    public void encodeTest19() throws Exception {
        File inputFile1 = createInputFile5();
        String args[] = {"-m","-f","-w", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "i hAVE a dREAM tO bE a \nsOFTWARE eNGINEER";

        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: test two arguments and options with "-w" and "-f" , -w <delimiter is many characters> : encode -w "abn" -f file1.txt
    //also test "   -f   " equals "-f", because we use arg = arg.trim()
    // Frame #: 31
    @Test
    public void encodeTest20() throws Exception {
        File inputFile1 = createInputFile5();
        String args[] = {"      -f    ","   -w    ","abn", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "i HAvE A DREAm TO Be A \nsOFTWArE ENgINeER";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: test two arguments and options with "-w" and "-m" , -w <delimiter is many characters> -m <string contains any other characters>.  : encode -w "abn" -m "HAVE" -f file1.txt
    // Frame #: 28
    @Test
    public void encodeTest21() throws Exception {
        File inputFile1 = createInputFile5();
        String args[] = {"     -w","abn","-m","HAVE", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "I HAVE a dreaM to bE a \nSoftwaRe enGinEer";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: test three arguments and options with "-m" and "-w" and "-f" , -m <string contains only letters> -w <delimiter is many characters> .  : encode -m "AVE" -w "ab" -f file1.txt
    // Frame #: 48
    @Test
    public void encodeTest22() throws Exception {
        File inputFile1 = createInputFile5();
        String args[] = {"-m","AVE","   -w    ","ab","-f", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "i Have A DREAm TO Be A \nsOFTWArE ENGINEER";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: test three arguments and options with "-m" and "-w", -m <string contains only letters> -w <delimiter is many characters> .  : encode -m "AVE" -w "ab" file1.txt
    // Frame #: 25
    @Test
    public void encodeTest23() throws Exception {
        File inputFile1 = createInputFile5();
        String args[] = {"-m","AVE","-w","ab", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "I hAVE a dreaM to bE a \nSoftwaRe engineer";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);

    }

    // Purpose: input filename is empty  : encode
    // Frame #: 2

    @Test
    public void encodeTest24(){
        String args[] = null; //invalid argument
        Main.main(args);
        assertEquals("Usage: encode  [-w [string]] [-m string] [-f] [-i|-I] [-o] <filename>", errStream.toString().trim());
    }

    // Purpose: test Arguments and options format WHEN IT IS improper format
    // Frame #: 3
    @Test
    public void encodeTest25() throws Exception {
        File inputFile1 = createInputFile3();
        String args[] = {"-W-X--FA", inputFile1.getPath()};
        Main.main(args);
        assertEquals("Usage: encode  [-w [string]] [-m string] [-f] [-i|-I] [-o] <filename>", errStream.toString().trim());
    }

    // Purpose: test -f is always applied last
    // Frame #: 30
    @Test
    public void encodeTest26() throws Exception {
        File inputFile1 = createInputFile5();
        String args[] = {"-f","-w", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "i hAVE a dREAM tO bE a \nsOFTWARE eNGINEER";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    //Purpose: test -m, -w without any content before this two opts
    //Frame #: 19
    @Test
    public void encodeTest27() throws Exception {
        File inputFile1 = createInputFile6();
        String args[] = {"-m","-w", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "9999i Am The King## Nothing\n \t King Is Here \n \t...";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    //Purpose: test -m, -w. -m with "   xxxx    " content
    //Frame #: 19
    @Test
    public void encodeTest28() throws Exception {
        File inputFile1 = createInputFile6();
        String args[] = {"-m"," xxxx   ","-w", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "9999i Am The King## Nothing\n \t King Is Here \n \t...";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    //Purpose: test -m, -w. -m with "aM" content
    //Frame #: 20
    @Test
    public void encodeTest29() throws Exception {
        File inputFile1 = createInputFile6();
        String args[] = {"-m","aM","-w", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "9999i AM The King## Nothing\n \t King Is Here \n \t...";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    //Purpose: test -m, -w. -m with "aM" content -w with "ioe" content
    //Frame #: 23
    @Test
    public void encodeTest30() throws Exception {
        File inputFile1 = createInputFile6();
        String args[] = {"-m","aM","-w","ioe9", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "9999I aM the kiNg## noThiNg\n \t KiNg iS heRe \n \t...";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    //Purpose: test -m,-w,-f. All opts are with contents
    //Frame #: 40
    @Test
    public void encodeTest31() throws Exception {
        File inputFile1 = createInputFile6();
        String args[] = {"-m","aM","-f","-w","9012ioe", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "9999i Am THE KInG## NOtHInG\n \t kInG Is HErE \n \t...";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    //Purpose: test -m,-w,-f. All opts are with contents with multiple "-f"
    //Frame #: 41
    @Test
    public void encodeTest32() throws Exception {
        File inputFile1 = createInputFile6();
        String args[] = {"-m","aM","-f","-w","###!!!ioe","-f", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "9999i aM the kiNg## noThiNg\n \t KiNg iS heRe \n \t...";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    //Purpose: test -m,-w. Different orders of combination of "-m -w"
    //Frame #: 24
    @Test
    public void encodeTest33() throws Exception {
        File inputFile1 = createInputFile6();
        String args[] = {"-w","-m",inputFile1.getPath()};
        Main.main(args);
        String expected1 = "9999i Am The King## Nothing\n \t King Is Here \n \t...";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    //Purpose: test -w,-m. Test -m with "AM " which contanins space
    //Frame #: 28
    @Test
    public void encodeTest34() throws Exception {
        File inputFile1 = createInputFile6();
        String args[] = {"-w","-m","AM ", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "9999i AM The King## Nothing\n \t King Is Here \n \t...";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    //Purpose: test -w,-m. Test -m with "AM " which contanins space
    //Frame #: 27
    @Test
    public void encodeTest35() throws Exception {
        File inputFile1 = createInputFile6();
        String args[] = {"-w","-m","I", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "9999I Am The KIng## NothIng\n \t KIng Is Here \n \t...";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    //Purpose: test -w,-m. Test mutiple -m
    //Frame #: 28
    @Test
    public void encodeTest36() throws Exception {
        File inputFile1 = createInputFile6();
        String args[] = {"-w","-m","I","-m","aM", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "9999I aM The KIng## NothIng\n \t KIng Is Here \n \t...";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    //Purpose: test -w,-m. Test mutiple -m,-w
    //Frame #: 28
    @Test
    public void encodeTest37() throws Exception {
        File inputFile1 = createInputFile6();
        String args[] = {"-w","-m","I","-m","aM","-w","I", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "9999I aM The KINg## NothINg\n \t KINg IS Here \n \t...";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    //Purpose: test -w,-m,-f. Test mutiple -m,-w,-f
    //Frame #: 51
    @Test
    public void encodeTest38() throws Exception {
        File inputFile1 = createInputFile6();
        String args[] = {"-f","-w","aim","-m","am","-w","-f",inputFile1.getPath()};
        Main.main(args);
        String expected1 = "9999i Am The KiNg## NothiNg\n \t KiNg IS Here \n \t...";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    //test -i -I and -o
    @Test
    public void encodeTest39() throws Exception {
        File inputFile1 = createInputFile2();
        String args[] = {"-m","VeEv","-i","-o", inputFile1.getPath()};
        String actual_before = getFileContent(inputFile1.getPath());
        Main.main(args);

        String expected1 = "VeEvvvVeEv,ooooo,\n\nVeEvve,abd";
        String actual_after = getFileContent(inputFile1.getPath());
        assertEquals("The file was changed!", actual_before, actual_after);
        assertEquals(expected1,outStream.toString().trim());
    }
    //test -i -I and -o
    @Test
    public void encodeTest40() throws Exception {
        File inputFile1 = createInputFile2();
        String args[] = {"-m","VeEv","-i","-I","-o","-f","-f", inputFile1.getPath()};
        String actual_before = getFileContent(inputFile1.getPath());
        Main.main(args);

        String expected1 = "VeEvVVVeEv,OOOOO,\n\nVeEvVE,ABD";
        String actual_after = getFileContent(inputFile1.getPath());
        assertEquals("The file was changed!", actual_before, actual_after);
        assertEquals(expected1,outStream.toString().trim());
    }
*/
}