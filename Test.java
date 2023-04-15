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
        IO.funcCalled("bar(true)");
        foo(true);
        IO.returnCalled("void");
    }
}
