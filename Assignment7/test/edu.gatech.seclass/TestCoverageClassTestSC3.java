package edu.gatech.seclass;
import org.junit.Test;
import java.lang.ArithmeticException;

public class TestCoverageClassTestSC3 {

    //100% statement coverage not reveal fault
    @Test
    public void TestCoverageClassTestSC3() {
        TestCoverageClass run = new TestCoverageClass();
        run.testCoverageMethod3(1,1); //test specification1
        run.testCoverageMethod3(0,2); //test specification2
    }

}