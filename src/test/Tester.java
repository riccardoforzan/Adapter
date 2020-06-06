package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import java.util.Vector;

public class Tester {

    public static void main(String[] args) {

        Vector tt = new Vector();
        tt.add(ListAdapterTester.class);
        //tt.add(SetAdapterTester.class);
        //tt.add(MapAdapterTester.class);

        for(int i=0;i<tt.size();i++){
            Class cls = (Class) tt.get(i);
            System.out.println("TEST DI: " + cls.getCanonicalName());
            Result result = JUnitCore.runClasses(cls);

            Object[] failures = result.getFailures().toArray();
            for (int j=0;j<failures.length;j++)
                System.out.println(failures[j].toString());

            System.out.println("TEST EFFETTUATI: " + result.getRunCount());
            System.out.println("TEST CORRETTI:" + (result.getRunCount() - result.getFailures().size()) );
            System.out.println("Test successful == " + result.wasSuccessful());
            System.out.println();
        }

    }

}