package edu.gatech.seclass;
import org.junit.Test;
import java.lang.ArithmeticException;

import static org.junit.Assert.assertEquals;


public class TestCoverageClassTestPC1 {

    // This method achieves 100% path coverage with no fault
    @Test
    public void TestCoverageClassTestPC1() {
        TestCoverageClass run = new TestCoverageClass();
        run.testCoverageMethod1(1,1); //if true branch without fault
        run.testCoverageMethod1(0,1); //if false branch and elseif true branch without fault
        run.testCoverageMethod1(-1,1); //elseif false branch with fault
    }


}