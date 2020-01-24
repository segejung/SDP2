package edu.gatech.seclass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This is a Georgia Tech provided code example for use in assigned private GT repositories. Students and other users of this template
 * code are advised not to share it with other students or to make it available on publicly viewable websites including
 * repositories such as github and gitlab.  Such sharing may be investigated as a GT honor code violation. Created for CS6300.
 *
 * This is an test class for a simple class that represents a string, defined
 * as a sequence of characters.
 *
 * You should implement your tests in this class.  Do not change the test names.
 */

public class MyCustomStringTest {

    private MyCustomStringInterface mycustomstring;

    @Before
    public void setUp() {
        mycustomstring = new MyCustomString();
    }

    @After
    public void tearDown() {
        mycustomstring = null;
    }

    //Test Purpose: This is the first instructor example test.
    @Test
    public void testMostCommonChar1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals('t', mycustomstring.mostCommonChar());
    }

    @Test
    public void testMostCommonChar2() {
        fail("Not yet implemented");
    }

    @Test
    public void testMostCommonChar3() {
        fail("Not yet implemented");
    }

    @Test
    public void testMostCommonChar4() {
        fail("Not yet implemented");
    }

    @Test
    public void testMostCommonChar5() {
        fail("Not yet implemented");
    }

    @Test
    public void testMostCommonChar6() {
        fail("Not yet implemented");
    }

    //Test Purpose: This is the second instructor example test.
    @Test
    public void testFilterLetters1() {
        mycustomstring.setString("1234!!! H3y, L3t'5 put 50me d161ts in this 5tr1n6!11!1");
        assertEquals("24Hy,L'pu0med6sinhisrn6", mycustomstring.filterLetters(3, false));
    }

    //Test Purpose: This the third instructor example test.
    @Test
    public void testFilterLetters2() {
        mycustomstring.setString("1234!!! H3y, L3t'5 put 50me d161ts in this 5tr1n6!11!1");
        assertEquals("1!!!  t t  11t  t t1!11!1", mycustomstring.filterLetters(3, true));
    }

    @Test
    public void testFilterLetters3() {
        
        fail("Not yet implemented");
    }

    @Test
    public void testFilterLetters4() {

        fail("Not yet implemented");
    }

    @Test
    public void testFilterLetters5() {

        fail("Not yet implemented");
    }

    @Test
    public void testFilterLetters6() {

        fail("Not yet implemented");
    }

    @Test
    public void testFilterLetters7() {
        
        fail("Not yet implemented");
    }

    @Test
    public void testFilterLetters8() {
        
        fail("Not yet implemented");
    }

    @Test
    public void testFilterLetters9() {
        
        fail("Not yet implemented");
    }

    @Test
    public void testFilterLetters10() {
        
        fail("Not yet implemented");
    }

    @Test
    public void testFilterLetters11() {
        
        fail("Not yet implemented");
    }

    @Test
    public void testFilterLetters12() {
        
        fail("Not yet implemented");
    }


    //Test Purpose: This is the fourth instructor example test.
    @Test
    public void testNumberLengthsInSubstring1() {
        mycustomstring.setString("I'd b3tt3r put 50me 123 d161ts in this 5tr1n6, right?");
        mycustomstring.numberLengthsInSubstring(17, 27);
        assertEquals("I'd b3tt3r put 51me 3 d21ts in this 5tr1n6, right?", mycustomstring.getString());
    }

    //Test Purpose: This is the fifth instructor example test, demonstrating a test for an exception.  Your exceptions should be tested in this format.
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testNumberLengthsInSubstring2() {
        mycustomstring.numberLengthsInSubstring(2, 10);
    }

    @Test
    public void testNumberLengthsInSubstring3() {
        fail("Not yet implemented");
    }

    @Test
    public void testNumberLengthsInSubstring4() {
        fail("Not yet implemented");
    }

    @Test
    public void testNumberLengthsInSubstring5() {
        fail("Not yet implemented");
    }

    @Test
    public void testNumberLengthsInSubstring6() {
        fail("Not yet implemented");
    }

    @Test
    public void testNumberLengthsInSubstring7() {
        fail("Not yet implemented");
    }

    @Test
    public void testNumberLengthsInSubstring8() {
        fail("Not yet implemented");
    }

    @Test
    public void testNumberLengthsInSubstring9() {
        fail("Not yet implemented");
    }

    @Test
    public void testNumberLengthsInSubstring10() {
        fail("Not yet implemented");
    }

}
