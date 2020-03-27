package edu.gatech.seclass;
import org.junit.Test;
import java.lang.ArithmeticException;

import static org.junit.Assert.assertEquals;


public class TestCoverageClassTestPC1 {

    // 100% path coverage with no fault
    @Test
    public void TestCoverageClassTestPC1() {
        TestCoverageClass run = new TestCoverageClass();
        run.testCoverageMethod1(1,1); //path 1 with no fault
        run.testCoverageMethod1(0,1); //path 2 without fault
        run.testCoverageMethod1(-1,1); //path 3 with fault
    }


}