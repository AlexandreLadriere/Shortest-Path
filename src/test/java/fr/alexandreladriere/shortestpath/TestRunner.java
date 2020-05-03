package test.java.fr.alexandreladriere.shortestpath;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import test.java.fr.alexandreladriere.shortestpath.bfs.BreadthFirstSearchTest;

/**
 * Test runner
 */
public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(BreadthFirstSearchTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println("Result == " + result.wasSuccessful());
    }
}
