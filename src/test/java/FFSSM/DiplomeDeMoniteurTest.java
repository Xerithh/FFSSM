package FFSSM;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class DiplomeDeMoniteurTest {

    private Plongeur moniteur;
    private DiplomeDeMoniteur diplome;
    private Club club;

    @BeforeEach
    void setUp() {
        moniteur = new Plongeur("Moniteur", 4);
        diplome = new DiplomeDeMoniteur(moniteur, 12345);
        club = new Club(diplome, "Club Test");
    }

    @Test
    void testCreationDiplome() {
        assertEquals(12345, diplome.getNumeroDiplome());
        assertEquals(moniteur, diplome.getPossesseur());
    }

    @Test
    void testEmployeurActuelSansEmbauche() {
        assertNull(diplome.employeurActuel());
    }

    @Test
    void testNouvelleEmbauche() {
        diplome.nouvelleEmbauche(club, LocalDate.of(2024, 1, 1));
        assertEquals(club, diplome.employeurActuel());
    }

    @Test
    void testEmployeurActuelApresTerminaison() {
        diplome.nouvelleEmbauche(club, LocalDate.of(2024, 1, 1));
        diplome.terminerEmbauche(LocalDate.of(2024, 6, 1));
        assertNull(diplome.employeurActuel());
    }

    @Test
    void testEmplois() {
        diplome.nouvelleEmbauche(club, LocalDate.of(2024, 1, 1));
        assertEquals(1, diplome.emplois().size());
    }

    @Test
    void testPlusieursEmbauches() {
        Plongeur moniteur2 = new Plongeur("Autre", 4);
        DiplomeDeMoniteur diplome2 = new DiplomeDeMoniteur(moniteur2, 2);
        Club club2 = new Club(diplome2, "Club 2");

        diplome.nouvelleEmbauche(club, LocalDate.of(2023, 1, 1));
        diplome.terminerEmbauche(LocalDate.of(2023, 12, 31));
        diplome.nouvelleEmbauche(club2, LocalDate.of(2024, 1, 1));

        assertEquals(club2, diplome.employeurActuel());
        assertEquals(2, diplome.emplois().size());
    }
}
