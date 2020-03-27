package edu.gatech.seclass;


public class TestCoverageClass {

    public void testCoverageMethod1 () {
        //It is not possible to create such method
        // path coverage includes statement coverage. So if path coverage is 100%, statement coverage is also 100%.
    }

    public void testCoverageMethod2 (int x, int y) {
        int a = x;
        int b = y;
        int result;
        if (a > 0 || b > 0){
            result = a/b;
        }
        else if (a == 0){
            result = a;
        }
    }

    public void testCoverageMethod3 () {

    }

    public void testCoverageMethod4 () {

    }

    public boolean testCoverageMethod5 (boolean a, boolean b) {
        int x = 3;
        int y = 1;
        if(a)
            x += y;
        else
            y = y*x;
        if(b)
            y -= x;
        else
            y -= 1;
        return ((x/y)>= 0);
    }


// ================
//
    // Fill in column “output” with T, F, or E:
    //
// | a | b |output|
// ================
// | T | T |      |
// | T | F |      |
// | F | T |      |
// | F | F |      |
// ================
//
// Fill in the blanks in the following sentences with
// “NEVER”, “SOMETIMES” or “ALWAYS”:
//
// Test suites with 100% statement coverage _____ reveal the fault in this method.
// Test suites with 100% branch coverage ______ reveal the fault in this method.
// Test suites with 100% path coverage ______ reveal the fault in this method.
// ================

}

