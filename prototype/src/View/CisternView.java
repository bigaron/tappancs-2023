package prototype.src.View;

import prototype.src.Elements.Cistern;
import prototype.src.Elements.Pipe;
import prototype.src.Elements.Pump;

import java.awt.*;

public class CisternView extends ElementView {

    public static int counter = 0;

    private int[] xVertices = new int[4];
    private int[] yVertices = new int[4];
    private int cisternPixelSize = 120;

    public int getCisternPixelSize() {
        return cisternPixelSize;
    }

    public int getWidth() {
        return cisternPixelSize;
    }

    public int getHeight() {
        return cisternPixelSize;
    }

    @Override
    public int getX() {
        return x + cisternPixelSize / 2;
    }

    @Override
    public int getY() {
        return y + cisternPixelSize / 2;
    }

    public CisternView(Cistern referencedCistern) {
        ++counter;
        referencedElement = referencedCistern;
        x = 200;
        y = 200;
        xVertices[0] = x + cisternPixelSize / 2;
        xVertices[1] = x + cisternPixelSize;
        xVertices[2] = x + cisternPixelSize / 2;
        xVertices[3] = x;

        yVertices[0] = y;
        yVertices[1] = y + cisternPixelSize / 2;
        yVertices[2] = y + cisternPixelSize;
        yVertices[3] = y + cisternPixelSize / 2;
    }

    @Override
    public void draw(Graphics g) {
        xVertices[0] = x + cisternPixelSize / 2;
        xVertices[1] = x + cisternPixelSize;
        xVertices[2] = x + cisternPixelSize / 2;
        xVertices[3] = x;

        yVertices[0] = y;
        yVertices[1] = y + cisternPixelSize / 2;
        yVertices[2] = y + cisternPixelSize;
        yVertices[3] = y + cisternPixelSize / 2;

        g.setColor(new Color(174, 140, 163));
        g.fillPolygon(xVertices, yVertices, 4);
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
        Cistern cistern = (Cistern) referencedElement;
        int neighborCount = cistern.getNeighborSize();
        for(int i = 0; i < neighborCount; ++i) {
            Pipe pipe = (Pipe)cistern.GetNeighbor(i);
            PipeView pipeView = (PipeView) pipe.getView();
            double fi = 2 * i * Math.PI / neighborCount;
            pipeView.calculateCoords((int)(x + Math.cos(fi) * basicPipeDistance), (int)(y - Math.sin(fi) * basicPipeDistance));
        }
    }

    public int[] AttachCoords(Pipe pipe) {
        int[] attachPointXY = new int[2];
        Cistern cistern = (Cistern) referencedElement;
        int pipeIdx = cistern.neighbours.indexOf(pipe);
        int pumpNeighborSize = cistern.getNeighborSize();
        double fi = 2 * pipeIdx * Math.PI / pumpNeighborSize;
        /*attachPointXY[0] = (int)(x + cisternPixelSize / 2 + (cisternPixelSize / 2) * Math.cos(fi));
        attachPointXY[1] = (int)(y + cisternPixelSize / 2 - (cisternPixelSize / 2) * Math.sin(fi));*/

        attachPointXY[0] = getX();
        attachPointXY[1] = getY();

        return attachPointXY;
    }
}
