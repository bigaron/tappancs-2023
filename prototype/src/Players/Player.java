package prototype.src.Players;

import prototype.src.Elements.Element;
import prototype.src.Elements.Pipe;

public class Player {
    private Element elem;
    private Pipe pipe;

    public void Move(int dir){
        Element neighbour = elem.GetNeighbor(dir);
        
    }
}
