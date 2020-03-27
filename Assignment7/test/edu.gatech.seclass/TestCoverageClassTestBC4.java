package edu.gatech.seclass;
import org.junit.Test;
import java.lang.ArithmeticException;

public class TestCoverageClassTestBC4 {

    // This method achieves 100% branch coverage with no fault
    //100% branch coverage is Necessary to reveal the fault.
    @Test
    public void TestCoverageClassTestBC3() {
        TestCoverageClass run = new TestCoverageClass();
        run.testCoverageMethod3(1,1); //if true branch without fault
        run.testCoverageMethod3(0,1); //if false branch and elseif true branch without fault
        run.testCoverageMethod3(-1,1); //elseif false branch without fault
    }

}