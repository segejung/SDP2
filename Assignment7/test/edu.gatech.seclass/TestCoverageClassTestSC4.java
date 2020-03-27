package edu.gatech.seclass;
import org.junit.Test;
import java.lang.ArithmeticException;

public class TestCoverageClassTestSC4 {

    // Every test suite 100% statement coverage reveals fault
    @Test
    public void TestCoverageClassTestSC4() {
        TestCoverageClass run = new TestCoverageClass();
        run.testCoverageMethod4(1,0); //test specification1
        run.testCoverageMethod4(0,0); //test specification2

    }

}