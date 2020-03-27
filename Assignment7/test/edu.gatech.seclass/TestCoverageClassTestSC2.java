package edu.gatech.seclass;
import org.junit.Test;
import java.lang.ArithmeticException;

public class TestCoverageClassTestSC2 {

    // 100% statement coverage without fault
    @Test
    public void TestCoverageClassTestSC2() {
        TestCoverageClass run = new TestCoverageClass();
        run.testCoverageMethod2(1,1); //test specification
        run.testCoverageMethod2(0,2); //test specification
    }

}