import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

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
}
