package Skeleton.Tests;
/**
 * A tesztesetek ősosztálya.
 */
public abstract class Tester {

    public abstract void initializeTest();

    public abstract void executeTest();

    @Override
    public abstract String toString();
}
