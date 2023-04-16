/**
 * A forrást reprezentáló osztály, a Node leszármazottja.
 */
public class Source extends Node{
    /**
     * Továbbítja a vizet a szomszédainak.
     * @param elem
     */
    @Override
    public void ForwardWater(Element elem){
        IO.funcCalled("Pipe.ForwardWater(this)");
        neighbours.get(0).ForwardWater(this);
        IO.returnCalled("void");
        IO.funcCalled("Pipe.ForwardWater(this)");
        neighbours.get(1).ForwardWater(this);
        IO.returnCalled("void");
    }

    /**
     * A Steppable interface függvényét valósítja meg, ez "indítja" a víz folyását.
     */
    @Override
    public void Step(){
        IO.funcCalled("Source.ForwardWater(null)");
        this.ForwardWater(null);
        IO.returnCalled("void");
    }
}
