package FFSSM;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class LicenceTest {

    private Plongeur plongeur;
    private Club club;
    private Licence licence;
    private LocalDate delivrance;

    @BeforeEach
    void setUp() {
        Plongeur moniteur = new Plongeur("Moniteur", 4);
        DiplomeDeMoniteur diplome = new DiplomeDeMoniteur(moniteur, 1);
        club = new Club(diplome, "Club Test");
        plongeur = new Plongeur("Test", 2);
        delivrance = LocalDate.of(2024, 6, 1);
        licence = new Licence(plongeur, "LIC-001", delivrance, club);
    }

    @Test
    void testLicenceValideLeJourDeDelivrance() {
        assertTrue(licence.estValide(delivrance));
    }

    @Test
    void testLicenceValidePendantLAnnee() {
        assertTrue(licence.estValide(delivrance.plusMonths(6)));
    }

    @Test
    void testLicenceValideLaDerniereJour() {
        assertTrue(licence.estValide(delivrance.plusYears(1).minusDays(1)));
    }

    @Test
    void testLicenceInvalideApresUnAn() {
        assertFalse(licence.estValide(delivrance.plusYears(1)));
    }

    @Test
    void testLicenceInvalideAvantDelivrance() {
        assertFalse(licence.estValide(delivrance.minusDays(1)));
    }

    @Test
    void testGetPossesseur() {
        assertEquals(plongeur, licence.getPossesseur());
    }

    @Test
    void testGetClub() {
        assertEquals(club, licence.getClub());
    }
}
