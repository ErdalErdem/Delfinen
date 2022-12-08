import Database.Database;
import Medlem.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
    Database database = new Database();

    @BeforeEach
    void set() {
        Database database = new Database();

    }

    @Test
    void tilFoejelseAfMedlemmer() {

        // Arrange
        Database database = new Database();

        // Act

        Medlem m = new Medlem("Per", "03/02/2002", "PerAndersen@Gmail.com", true);
        //database.tilfoejMedlem("Per", 20, "mand", Medlem.typeMedlem.aktiv);
        database.tilfoejMedlem(m);

        // Assert
        int forventetDBStørrelse = 1;
        int faktiskStørrelse = database.getMedlemDB().size();
        assertEquals(forventetDBStørrelse, faktiskStørrelse);
    }

    @Test
    void findMedlem() {

        // Arrange
        /*String navn = "Hans";
        int alder = 25;
        String køn = "Mand";*/
        boolean aktivitet = true;
        Medlem m = new Medlem("Hans", "03/02/1997", "Hans@Gmail.com", true);

        //database.tilfoejMedlem(navn, alder, køn, Medlem.typeMedlem.aktiv); // Type medlem skal ændres til boolean når vi merger
        database.tilfoejMedlem(m);

        // Act
        Medlem resultat = database.findMedlem(m.getID());


        // Assert
        assertEquals(resultat.getID(), m.getID());
    }

    @Test
    void sletMedlem() {

        // Arrange
        int forventetStørrelse = 0;
        Medlem m = new Medlem("Hans", "03/02/1997", "Hans@Gmail.com", true);


        //database.tilfoejMedlem("Hans", 25, "Mand", Medlem.typeMedlem.aktiv);
        database.tilfoejMedlem(m);
        // Act

        //Medlem faktiskStørrelse = database.findMedlem("Hans");
        database.sletMedlem(m.getID());

        // Assert

        int aktuelStørrelse = database.getMedlemDB().size();

        //assertEquals(forventetStørrelse, faktiskStørrelse);
        assertEquals(forventetStørrelse, aktuelStørrelse);

    }

    @Test
    void beregnKontigent() {
        Medlem m0 = new Medlem("Per", "03/02/2009", "PerAndersen@Gmail.com", true);
        Medlem m1 = new Medlem("Hans", "03/02/1997", "Hans@Gmail.com", true);
        Medlem m2 = new Medlem("Lars", "08/09/1986","LarsKristensen@outlook.dk", false);

        database.tilfoejMedlem(m0);
        database.tilfoejMedlem(m1);
        database.tilfoejMedlem(m2);

        int aktuelKontigent = database.beregnSamletBalance();
        int forventetKontigent = 3100;

        assertEquals(forventetKontigent, aktuelKontigent);
    }

    @Test
    void beregnBalance() {
        Medlem m0 = new Medlem("Per", "03/02/2009", "PerAndersen@Gmail.com", true);
        Medlem m1 = new Medlem("Hans", "03/02/1997", "Hans@Gmail.com", true);
        Medlem m2 = new Medlem("Lars", "08/09/1986","LarsKristensen@outlook.dk", false);
        m1.setHarGæld(true);

        database.tilfoejMedlem(m0);
        database.tilfoejMedlem(m1);
        database.tilfoejMedlem(m2);

        int aktuelKontigent = database.beregnSamletBalance();
        int forventetKontigent = 1500;

        assertEquals(forventetKontigent, aktuelKontigent);
    }

}
