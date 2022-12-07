package Comparator;

import Medlem.*;
import java.util.Comparator;

public class AlderComparator implements Comparator<Medlem> {

    public int compare(Medlem m1, Medlem m2){
        return Integer.compare(m1.getAlder(), m2.getAlder());
    }

}
