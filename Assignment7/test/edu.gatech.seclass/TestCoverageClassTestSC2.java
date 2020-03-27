package edu.gatech.seclass;
import org.junit.Test;
import java.lang.ArithmeticException;

public class TestCoverageClassTestSC2 {

    // This method achieves 100% statement coverage not reveal fault
    @Test
    public void TestCoverageClassTestSC2() {
        TestCoverageClass run = new TestCoverageClass();
        run.testCoverageMethod2(1,1); //cover test spec 1
        run.testCoverageMethod2(0,2); //cover test spec 2
    }

}