package Skeleton.Tests;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Skeleton.src.IO;

public class Test {
    public static boolean bar(int a){
        return false;
    }
    public static void foo(boolean f){
        IO.funcCalled("foo(1)");
        boolean val = bar(2);
        IO.returnCalled(Boolean.toString(val));
    }

    public static void main(String args[]){

        List<Tester> ts = new ArrayList<>();
        ts.add(new AttachPipeTest());
        ts.add(new ChangePumpTest());
        ts.add(new CisternForwardwaterTest());
        ts.add(new PickupPumpTest());
        ts.add(new PumpForwardwaterTest());
        ts.add(new PlacedownPumpTest());
        ts.add(new PumpBreakTest());
        ts.add(new PipeForwardwaterTest());
        ts.add(new RepairPipeTest());
        ts.add(new SabotagePipeTest());
        ts.add(new SourceForwardwaterTest());
        ts.add(new SteponPumpTest());
        ts.add(new SteponPipeSuccessTest());
        ts.add(new SteponPipeFailTest());
        ts.add(new TakeoffPipeTest());
        ts.add(new GenerateElementTest());

        Scanner scanner = new Scanner(System.in);

        while(true) {
            for(int i = 0; i < ts.size(); ++i) {
                System.out.println((i + 1) + ". test: " + ts.get(i).toString());
            }
            System.out.println((ts.size() + 1) + ". exit");
            String in = scanner.nextLine();
            int testNumber = Integer.parseInt(in) - 1;

            if(testNumber != ts.size()) {
                ts.get(testNumber).initializeTest();
                ts.get(testNumber).executeTest();
                while(true) {
                    System.out.println("");
                    System.out.println("1. menu");
                    in = scanner.nextLine();
                    if(Integer.parseInt(in) == 1)
                        break;
                }

            } else {
                return;
            }
        }
    }
}
