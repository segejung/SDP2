package edu.gatech.seclass;
import org.junit.Test;
import java.lang.ArithmeticException;

public class TestCoverageClassTestBC3 {

    // This method achieves 100% branch coverage with fault revealed at the end
    //100% branch coverage is Necessary to reveal the fault.
    @Test
    public void TestCoverageClassTestBC3() {
        TestCoverageClass test = new TestCoverageClass();
        test.testCoverageMethod3(1,0); //if true branch without fault
        test.testCoverageMethod3(0,0); //if false branch and elseif true branch without fault
        test.testCoverageMethod3(-1,0); //elseif false branch with fault
    }

}