package prototype.src.View;

import prototype.src.Elements.Cistern;

import java.awt.*;

public class CisternView extends ElementView {

    int xVertices[] = new int[4];
    int yVertices[] = new int[4];
    int cisternPixelSize = 120;
    public CisternView(Cistern referencedCistern) {
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
}
