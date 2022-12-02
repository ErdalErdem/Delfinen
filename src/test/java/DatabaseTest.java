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

        database.tilfoejMedlem("Per", 20, "mand", Medlem.typeMedlem.aktiv);

        // Assert
        int forventetDBStørrelse = 1;
        int faktiskStørrelse = database.getMedlemDB().size();
        assertEquals(forventetDBStørrelse, faktiskStørrelse);
    }

    @Test
    void findMedlem() {

        // Arrange
        String navn = "Hans";
        int alder = 25;
        String køn = "Mand";
        boolean aktivitet = true;

        database.tilfoejMedlem(navn, alder, køn, Medlem.typeMedlem.aktiv); // Type medlem skal ændres til boolean når vi merger

        // Act
        Medlem resultat = database.findMedlem("Hans");


        // Assert
        assertEquals(resultat.getNavn(), navn);
    }

    @Test
    void sletMedlem() {

        // Arrange
        int forventetStørrelse = 0;

        database.tilfoejMedlem("Hans", 25, "Mand", Medlem.typeMedlem.aktiv);
        // Act

        Medlem faktiskStørrelse = database.findMedlem("Hans");

        // Assert

        assertEquals(forventetStørrelse, faktiskStørrelse);


    }

}
