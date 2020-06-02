package test;

import java.util.ArrayList;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Tester {

    public static void main(String[] args) {

        ArrayList<Class> toTest = new ArrayList<>();
        toTest.add(SetAdapterTester.class);
        //toTest.add(MapAdapterTester.class);
        //toTest.add(ListAdapterTester.class);

        for(Class cls : toTest){
            System.out.println("TEST DI: " + cls.getCanonicalName());
            Result result = JUnitCore.runClasses(cls);

            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }

            System.out.println("TEST EFFETTUATI: " + result.getRunCount());
            System.out.println("TEST CORRETTI:" + (result.getRunCount() - result.getFailures().size()) );
            System.out.println("Test successful == " + result.wasSuccessful());
            System.out.println();
        }

    }

}