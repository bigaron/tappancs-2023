public class Source extends Node{
    @Override
    public void ForwardWater(Element elem){
        IO.funcCalled("pipe1.ForwardWater(this)");
        neighbours.get(0).ForwardWater(this);
        IO.returnCalled("void");
        IO.funcCalled("pipen.ForwardWater(this)");
        neighbours.get(1).ForwardWater(this);
        IO.returnCalled("void");
    }
    @Override
    public void Step(){
        IO.funcCalled("this.ForwardWater(null)");
        this.ForwardWater(null);
        IO.returnCalled("void");
    }
}
