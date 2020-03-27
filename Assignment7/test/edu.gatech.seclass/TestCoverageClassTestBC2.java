package edu.gatech.seclass;
import org.junit.Test;
import java.lang.ArithmeticException;

public class TestCoverageClassTestBC2 {

    // This method achieves 100% branch coverage with fault revealed in every test cases
    //100% branch coverage is Sufficient to reveal the fault, but not necessary.
    @Test
    public void TestCoverageClassTestBC2() {
        TestCoverageClass test = new TestCoverageClass();
        test.testCoverageMethod2(1,0); //if true branch with fault
        test.testCoverageMethod2(0,0); //if false branch and elseif true branch with fault
        test.testCoverageMethod2(-1,0); //elseif false branch with fault
    }

}