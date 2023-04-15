/**
 * Az IO osztály írja ki a függvényhívásokat. 
 * A függvényneveket, a mélységet, a paramétereket, valamint a visszatérési értéket 
 */
public class IO{
    /**
     * A depth a mélységet reprezentálja, azaz, hogy hány függvényt hívtunk meg egymásban..
     */
    private static int depth = -1;
    /**
     * A cntr azt számlálja, hogy hány függvényhívás volt végrehajtva.
     */
    private static long cntr = 0;
    /**
     * A lastCalled azt mutatja, hogy a legutoljára hívott függvénynek mi volt a cntr-e.
     */
    private static long lastCalled;

    /**
     * A paraméterként megkapott Stringet írja ki, ami elé fűzi, hogy a program futása során
     * hányadik függvényhívás volt.
     * @param param - A formátum, ami szerint meg kell adni a függvényt: függvénynév([param1, param2, ...])
     */
    public static void funcCalled(String param){
        lastCalled = ++cntr;
        depth++;
        for(int i = 0; i < depth; ++i) System.out.print("\t");
        System.out.println("Függvényhívás" + cntr + ":" + param);
    }

    /**
     * A paraméterként megadott return értéket kiírja, valamint eléfűzi, hogy a programban hányadik hívott függvényhez 
     * tartozó return hívás
     * @param param - A formátum, melyben meg kell adni a return-t a következő: a return értéke. Void függvény esetén pl.: void.
     */
    public static void returnCalled(String param){
        for(int i = 0; i < depth; ++i) System.out.print("\t");
        depth--;
        System.out.println("return" + lastCalled + ":" + param);
        lastCalled--;
    }
}
