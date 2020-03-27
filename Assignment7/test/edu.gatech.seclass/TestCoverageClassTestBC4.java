package edu.gatech.seclass;
import org.junit.Test;
import java.lang.ArithmeticException;

public class TestCoverageClassTestBC4 {

    //100% branch coverage with no fault
    //100% branch coverage is Necessary to reveal the fault.
    @Test
    public void TestCoverageClassTestBC3() {
        TestCoverageClass run = new TestCoverageClass();
        run.testCoverageMethod3(1,1); //branch1 without fault
        run.testCoverageMethod3(0,1); //branch2 without fault
        run.testCoverageMethod3(-1,1); //branch3 without fault
    }

}