package FFSSM;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class EmbaucheTest {

    private Embauche embauche;
    private DiplomeDeMoniteur diplome;
    private Club club;

    @BeforeEach
    void setUp() {
        Plongeur moniteur = new Plongeur("Moniteur", 4);
        diplome = new DiplomeDeMoniteur(moniteur, 1);
        club = new Club(diplome, "Club Test");
        embauche = new Embauche(LocalDate.of(2024, 1, 1), diplome, club);
    }

    @Test
    void testCreationEmbauche() {
        assertEquals(LocalDate.of(2024, 1, 1), embauche.getDebut());
        assertEquals(diplome, embauche.getEmploye());
        assertEquals(club, embauche.getEmployeur());
    }

    @Test
    void testEmbaucheNonTermineeParDefaut() {
        assertFalse(embauche.estTerminee());
        assertNull(embauche.getFin());
    }

    @Test
    void testTerminerEmbauche() {
        embauche.terminer(LocalDate.of(2024, 12, 31));
        assertTrue(embauche.estTerminee());
        assertEquals(LocalDate.of(2024, 12, 31), embauche.getFin());
    }
}
