package edu.gatech.seclass;
import org.junit.Test;
import java.lang.ArithmeticException;


public class TestCoverageClassTestPC1 {

    // This method achieves 100% path coverage with no fault
    @Test
    public void TestCoverageClassTestPC1() {
        TestCoverageClass test = new TestCoverageClass();
        test.testCoverageMethod1(1,1); //if true branch without fault
        test.testCoverageMethod1(0,1); //if false branch and elseif true branch without fault
        test.testCoverageMethod1(-1,1); //elseif false branch with fault
    }

}