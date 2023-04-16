public class Source extends Node{
    @Override
    public void ForwardWater(Element elem){
        IO.funcCalled("Pipe.ForwardWater(this)");
        neighbours.get(0).ForwardWater(this);
        IO.returnCalled("void");
        IO.funcCalled("Pipe.ForwardWater(this)");
        neighbours.get(1).ForwardWater(this);
        IO.returnCalled("void");
    }
    @Override
    public void Step(){
        IO.funcCalled("Source.ForwardWater(null)");
        this.ForwardWater(null);
        IO.returnCalled("void");
    }
}
