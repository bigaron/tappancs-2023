package prototype.src.View;

import prototype.src.Elements.Element;
import prototype.src.Elements.Pipe;

import java.awt.*;

public class PipeView extends ElementView {

    public PipeView(Pipe referencedPipe) {
        referencedElement = referencedPipe;
    }

    @Override
    public void draw(Graphics g) {
        /*Pipe pipe = (Pipe)referencedElement;
        if((pipe.getNeighbourSize() ==)*/
        //TODO van baj
    }

    @Override
    public void update() {
        //TODO
    }

    @Override
    public void calculateCoords(int x, int y) {
        if(visited) return;

        visited = true;
        this.x = x;
        this.y = y;
        Pipe pipe = (Pipe) referencedElement;
        int nNeighborSize = pipe.getNeighbourSize();
        if(nNeighborSize == 1) return;
        if(nNeighborSize == 2) {
            Element elem = pipe.GetNeighbor(1);
            ElementView elementView = elem.getView();
            elementView.calculateCoords(x, y);

            elem = pipe.GetNeighbor(0);
            elementView = elem.getView();
            elementView.calculateCoords(x, y);
        }
    }
}
