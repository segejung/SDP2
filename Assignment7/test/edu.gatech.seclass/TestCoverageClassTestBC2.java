package edu.gatech.seclass;
import org.junit.Test;
import java.lang.ArithmeticException;

public class TestCoverageClassTestBC2 {

    // 100% branch coverage with fault revealed in every test cases
    //100% branch coverage is Sufficient to reveal the fault, but not necessary.
    @Test
    public void TestCoverageClassTestBC2() {
        TestCoverageClass run = new TestCoverageClass();
        run.testCoverageMethod2(1,0); //branch1 with fault
        run.testCoverageMethod2(0,0); //branch2 with fault
        run.testCoverageMethod2(-1,0); //branch3 with fault
    }

}