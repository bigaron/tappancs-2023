package prototype.src.Players;

import prototype.src.View.SaboteurView;

import java.io.FileWriter;
import java.io.IOException;

import static prototype.src.Modifier.Slippery;

/**
 * A szabotőrt reprezentáló osztály, a játékos leszármazottja.
 */
public class Saboteur extends Player{
    private static int counter = 0;
    private SaboteurView view;

    public Saboteur() {
        ++counter;
        ID = "saboteur" + counter;
        view = null;
    }
    public static void resetCounter(){counter = 0;}

    public SaboteurView getView() {
        return view;
    }

    public void setView(SaboteurView view) {
        this.view = view;
    }

    public void Save(FileWriter writer, boolean state) {
        try {
            if(state) {
                writer.write("saboteur+" + ID + "\n");
            } else {
                writer.write("saboteur+" + ID + "+" + elem.getID());
                if(pipe != null) {
                    writer.write("+" + pipe.getID() + "\n");
                } else {
                    writer.write("+null\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void MakeSlippery() {
        elem.ChangeSurface(Slippery);
    }

    public void Read(String[] parsed){
        if(parsed[0] != "plumber") return;
        ID = parsed[1];
    }

    @Override
    public void list(){
        System.out.println("\nSaboteur");
        System.out.println("id: " + getID());
        System.out.println("elem: " + elem.getID());
        if(pipe != null)System.out.println("pipe: " + pipe.getID());
        else System.out.println("pipe: null");
    }
}
