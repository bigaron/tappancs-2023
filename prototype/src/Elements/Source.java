package prototype.src.Elements;
/**
 * A forrást reprezentáló osztály, a Node leszármazottja.
 */
public class Source extends Node{
    private static long counter = 0;
    public Source(){
        ID = "source" + ++counter;
    }
    /**
     * A Steppable interface függvényét valósítja meg, ez "indítja" a víz folyását.
     */
    @Override
    public void Step(){
        ForwardWater(this);
    }

    /**
     * Továbbítja a vizet a szomszédainak.
     * @param elem
     */
    @Override
    public void ForwardWater(Element elem){
        for(int i = 0; GetNeighbor(i) != null; ++i) GetNeighbor(i).ForwardWater(this);
    }
}
