package FFSSM;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class PlongeurTest {

    private Plongeur plongeur;
    private Club club;

    @BeforeEach
    void setUp() {
        plongeur = new Plongeur("Dupont", 2);
        Plongeur moniteur = new Plongeur("Moniteur", 4);
        DiplomeDeMoniteur diplome = new DiplomeDeMoniteur(moniteur, 1);
        club = new Club(diplome, "Club Test");
    }

    @Test
    void testCreationPlongeur() {
        assertEquals("Dupont", plongeur.getNom());
        assertEquals(2, plongeur.getNiveau());
    }

    @Test
    void testSetNiveau() {
        plongeur.setNiveau(3);
        assertEquals(3, plongeur.getNiveau());
    }

    @Test
    void testSetGroupeSanguin() {
        plongeur.setGroupeSanguin(GroupeSanguin.APLUS);
        assertEquals(GroupeSanguin.APLUS, plongeur.getGroupeSanguin());
    }

    @Test
    void testDerniereLicenceSansLicence() {
        assertNull(plongeur.derniereLicence());
    }

    @Test
    void testAjouterLicence() {
        plongeur.ajouterLicence("LIC-001", LocalDate.of(2024, 1, 1), club);
        assertNotNull(plongeur.derniereLicence());
        assertEquals("LIC-001", plongeur.derniereLicence().numero);
    }

    @Test
    void testDerniereLicenceAvecPlusieursLicences() {
        plongeur.ajouterLicence("LIC-001", LocalDate.of(2023, 1, 1), club);
        plongeur.ajouterLicence("LIC-002", LocalDate.of(2024, 1, 1), club);
        assertEquals("LIC-002", plongeur.derniereLicence().numero);
    }

    @Test
    void testGetLicences() {
        plongeur.ajouterLicence("LIC-001", LocalDate.of(2023, 1, 1), club);
        plongeur.ajouterLicence("LIC-002", LocalDate.of(2024, 1, 1), club);
        assertEquals(2, plongeur.getLicences().size());
    }
}
