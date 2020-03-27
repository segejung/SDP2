package edu.gatech.seclass;
import org.junit.Test;
import java.lang.ArithmeticException;

public class TestCoverageClassTestBC2 {

    // This method achieves 100% branch coverage with fault revealed
    @Test
    public void TestCoverageClassTestBC2() {
        TestCoverageClass test = new TestCoverageClass();
        test.testCoverageMethod2(10,0); //if true branch covered with fault
        test.testCoverageMethod2(0,0); //if false branch covered and elseif true branch covered
        test.testCoverageMethod2(-1,-1); //elseif false branch covered
    }

}