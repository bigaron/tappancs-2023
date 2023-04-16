public class Cistern extends Node{
   private Generator generator;
   
   @Override
   public void ForwardWater(Element elem){}
    @Override
    public Pump GetPump(){
       if(!IO.input.get(0)) {
           IO.funcCalled("Generator.RequestPump()");
           Pump pump = generator.RequestPump();
           IO.returnCalled("pump");
           return pump;
       } else {
           return null;
       }
    }

    public void SetGenerator(Generator g) {
       generator = g;
    }
}
