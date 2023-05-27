package prototype.src.View;

import prototype.src.Elements.Element;
import prototype.src.Elements.Pipe;

import java.awt.*;

public class PipeView extends ElementView {
    public static int counter = 0;

    public PipeView(Pipe referencedPipe) {
        ++counter;
        referencedElement = referencedPipe;
    }

    @Override
    public void draw(Graphics g) {
        Pipe pipe = (Pipe)referencedElement;
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        if(pipe.getNeighbourSize() == 2){
            ElementView neighborZeroElementView = pipe.neighbours[0].getView();
            ElementView neighborOneElementView = pipe.neighbours[1].getView();
            int[] zeroXY = neighborZeroElementView.AttachCoords(pipe);
            int[] firstXY = neighborOneElementView.AttachCoords(pipe);
            g.drawLine(zeroXY[0], zeroXY[1], firstXY[0], firstXY[1]);
        } else if (pipe.getNeighbourSize() == 1) {
            if(pipe.neighbours[0] != null) {
                ElementView neighborZeroElementView = pipe.neighbours[0].getView();
                int[] zeroXY = neighborZeroElementView.AttachCoords(pipe);
                g.drawLine(zeroXY[0], zeroXY[1], x, y);
            } else {
                ElementView neighborOneElementView = pipe.neighbours[1].getView();
                int[] firstXY = neighborOneElementView.AttachCoords(pipe);
                g.drawLine(firstXY[0], firstXY[1], x, y);
            }
        }
    }

    @Override
    public void update() {
        Pipe pipe = (Pipe) referencedElement;
        if(pipe.getNeighbourSize() == 2) {
            ElementView neighborZeroElementView = pipe.neighbours[0].getView();
            ElementView neighborOneElementView = pipe.neighbours[1].getView();
            setX((neighborZeroElementView.getX() + neighborOneElementView.getX()) / 2);
            setY((neighborZeroElementView.getY() + neighborOneElementView.getY()) / 2);
        } else if (pipe.getNeighbourSize() == 1) {
            if(pipe.neighbours[0] != null) {
                ElementView neighborZeroElementView = pipe.neighbours[0].getView();

                setX((neighborZeroElementView.getX() + x) / 2);
                setY((neighborZeroElementView.getY() + y) / 2);
            } else {
                ElementView neighborOneElementView = pipe.neighbours[1].getView();
                setX((neighborOneElementView.getX() + x) / 2);
                setY((neighborOneElementView.getY() + y) / 2);
            }
        }
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
            ElementView elementView;
            if(elem != null) {
                elementView = elem.getView();
                elementView.calculateCoords(x, y);
            }
            elem = pipe.GetNeighbor(0);
            if(elem != null) {
                elementView = elem.getView();
                elementView.calculateCoords(x, y);
            }
        }
    }


}
