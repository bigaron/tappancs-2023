package prototype.src.View;

import prototype.src.Elements.Element;
import prototype.src.Elements.Pipe;

public abstract class ElementView extends ObjectView {
    protected Element referencedElement;

    public ElementView() {
        super();
    }
    public int[] AttachCoords(Pipe pipe) { return new int[2]; }

    public int getWidth() {
        return 0;
    }

    public int getHeight() {
        return 0;
    }
}
