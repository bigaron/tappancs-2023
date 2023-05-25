package prototype.src.View;

import prototype.src.Elements.Cistern;

import java.awt.*;

public class CisternView extends ElementView {

    int xVertices[] = new int[4];
    int yVertices[] = new int[4];
    int cisternPixelSize = 60;
    public CisternView(Cistern referencedCistern) {
        referencedElement = referencedCistern;
        x = 300;
        y = 300;
        xVertices[0] = x + cisternPixelSize;
        xVertices[1] = x + 2 * cisternPixelSize;
        xVertices[2] = x + cisternPixelSize;
        xVertices[3] = x;

        yVertices[0] = y;
        yVertices[1] = y + cisternPixelSize;
        yVertices[2] = y + 2 * cisternPixelSize;
        yVertices[3] = y + cisternPixelSize;
    }

    @Override
    public void draw(Graphics g) {
        xVertices[0] = x + cisternPixelSize;
        xVertices[1] = x + 2 * cisternPixelSize;
        xVertices[2] = x + cisternPixelSize;
        xVertices[3] = x;

        yVertices[0] = y;
        yVertices[1] = y + cisternPixelSize;
        yVertices[2] = y + 2 * cisternPixelSize;
        yVertices[3] = y + cisternPixelSize;

        g.setColor(new Color(174, 140, 163));
        g.fillPolygon(xVertices, yVertices, 4);
    }

    @Override
    public void update() {
        //TODO
    }
}
