package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import java.util.Vector;

public class Tester {

    public static void main(String[] args) {

        boolean allPassed = true;

        Vector tt = new Vector();
        tt.add(ListAdapterTester.class);
        tt.add(SubListTester.class);
        tt.add(SetAdapterTester.class);
        tt.add(MapAdapterTester.class);

        for(int i=0;i<tt.size();i++){
            Class cls = (Class) tt.get(i);
            System.out.println("TEST OF: " + cls.getCanonicalName());
            Result result = JUnitCore.runClasses(cls);

            Object[] failures = result.getFailures().toArray();
            for (int j=0;j<failures.length;j++)
                System.out.println(failures[j].toString());

            System.out.println("TEST DONE: " + result.getRunCount());
            System.out.println("TEST PASSED:" + (result.getRunCount() - result.getFailures().size()) );
            boolean testRes = result.wasSuccessful();
            allPassed &= testRes;
            System.out.println("TEST SUCCESSFUL == " + testRes);
            System.out.println();
        }

        System.out.println("ALL TEST PASSED == " + allPassed);

    }

}