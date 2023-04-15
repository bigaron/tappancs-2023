public class Cistern extends Node{
   private Generator generator;
   
   @Override
   public void ForwardWater(Element elem){}
    @Override
    public Pump GetItem(){
        	return new Pump();
    }
}
