package edu.gatech.seclass;
import org.junit.Test;
import java.lang.ArithmeticException;

public class TestCoverageClassTestSC3 {

    // This method achieves 100% statement coverage not reveal fault
    @Test
    public void TestCoverageClassTestSC3() {
        TestCoverageClass test = new TestCoverageClass();
        test.testCoverageMethod2(1,1); //cover test spec 1
        test.testCoverageMethod2(0,2); //cover test spec 2
        test.testCoverageMethod2(-1,2); //cover test spec 2
    }

}