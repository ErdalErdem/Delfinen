package Comparator;

import Medlem.*;
import java.util.Comparator;

public class ResultatComparator implements Comparator<KonkurrenceMedlem> {

    public int compare(KonkurrenceMedlem km1, KonkurrenceMedlem km2){
        return Integer.compare(km1.getTræningsresultat(), km2.getTræningsresultat());
    }
}
