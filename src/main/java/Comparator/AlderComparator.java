package Comparator;

import Medlem.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AlderComparator implements Comparator<Medlem> {

    public int compare(Medlem m1, Medlem m2){
        return Integer.compare(m1.getAlder(), m2.getAlder());
    }


    /*private ArrayList<Medlem> sortAlder = new ArrayList<Medlem>();

    public ArrayList<Medlem> sortAlders(){
        return sortAlders();
    }

    public void comparatorSort(String type) {
       ArrayList<Medlem> SortAlder;

        Comparator<Medlem> comparator = comparatorSelection(type);

        Collections.sort(Medlem, comparator);
    }

    private Comparator<Medlem> comparatorSelection(String type) {
    }


    public void comparatorSort(String type, String sType) {

        Comparator<Medlem> comparator = comparatorSelection(type);
        Comparator<Medlem> comparatorS = comparatorSelection(sType);

        Collections.sort(database.getHeroDatabase(), comparator.thenComparing(comparatorS));
    }*/

}
