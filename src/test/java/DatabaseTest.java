import Database.Database;
import Medlem.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
    Database database;

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
        Medlem resultat = database.findMedlem("Hans");


        // Assert
        assertEquals(resultat.getNavn(), m.getNavn());
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
        database.sletMedlem("Hans");

        // Assert

        //assertEquals(forventetStørrelse, faktiskStørrelse);
        assertEquals(forventetStørrelse, database.getMedlemDB().size());

    }

}
