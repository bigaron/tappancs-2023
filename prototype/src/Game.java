package prototype.src;
import prototype.src.Elements.*;

public class Game {
    public static boolean random = true;
    public static Modifier parseModifier(String line){
        switch(line){
            case "sticky": return Modifier.Sticky;
            case "slippery": return Modifier.Slippery;
            default: return Modifier.Plain;
        }
    }
    public static int actionCounter = 0;
    public static void main(String[] args){
    }   
}
