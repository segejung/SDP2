package edu.gatech.seclass;
import java.lang.ArithmeticException;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestCoverageClassTestSC1 {

    // Less than 100% statement coverage with fault.
    @Test
    public void TestCoverageClassTestSC1() {
        TestCoverageClass run = new TestCoverageClass();
        run.testCoverageMethod1(1,0);
    }
}