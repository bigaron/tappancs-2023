package Skeleton.src;
import java.util.LinkedList;
import java.util.Deque;

/**<pre>
 * Saját osztály, mely a FILO adatstruktúrát valósítja meg.
 * !!!! Csak long típus tárolására alkalmas. !!!!
 *</pre>
 */
public class FILO{
    /**
     * Belső változó, amelyben az értékeket eltároljuk
     */
    private Deque<Long> _filo;  

    /**
     * Konstruktor mely inicializálja a belső tárolónk
     */
    public FILO(){
        _filo = new LinkedList<Long>();
    }

    /**
     * A tároló elejére beszúrja a paraméterként kapott értéket
     * @param id - a beszúrandó érték
     */
    public void add(long id){ _filo.addFirst(id); }

    /**
     * Visszaadja az első elemet a tárolóból, valamint törli is belőle
     * @return - az első elem a tárolóban
     */
    public long get(){ return _filo.removeFirst(); }
}
