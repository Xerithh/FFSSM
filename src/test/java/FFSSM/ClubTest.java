package FFSSM;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ClubTest {

    private Club club;
    private DiplomeDeMoniteur diplome;
    private Site site;

    @BeforeEach
    void setUp() {
        Plongeur moniteur = new Plongeur("Moniteur", 4);
        diplome = new DiplomeDeMoniteur(moniteur, 1);
        club = new Club(diplome, "Club Test");
        site = new Site("Site Test", "Description");
    }

    @Test
    void testCreationClub() {
        assertEquals("Club Test", club.getNom());
        assertEquals(diplome, club.getPresident());
    }

    @Test
    void testSetAdresseEtTelephone() {
        club.setAdresse("123 rue Test");
        club.setTelephone("0123456789");
        assertEquals("123 rue Test", club.getAdresse());
        assertEquals("0123456789", club.getTelephone());
    }

    @Test
    void testOrganisePlongee() {
        Plongee plongee = new Plongee(site, diplome, LocalDate.of(2024, 8, 15), 20, 45);
        club.organisePlongee(plongee);
        assertEquals(1, club.getPlongees().size());
    }

    @Test
    void testPlongeesNonConformesSansPlongee() {
        assertTrue(club.plongeesNonConformes().isEmpty());
    }

    @Test
    void testPlongeesNonConformesAvecPlongeeConforme() {
        Plongeur plongeur = new Plongeur("Plongeur", 2);
        plongeur.ajouterLicence("LIC-001", LocalDate.of(2024, 1, 1), club);

        Plongee plongee = new Plongee(site, diplome, LocalDate.of(2024, 8, 15), 20, 45);
        plongee.ajouteParticipant(plongeur.derniereLicence());
        club.organisePlongee(plongee);

        assertTrue(club.plongeesNonConformes().isEmpty());
    }

    @Test
    void testPlongeesNonConformesAvecPlongeeNonConforme() {
        Plongeur plongeur = new Plongeur("Plongeur", 2);
        plongeur.ajouterLicence("LIC-001", LocalDate.of(2023, 1, 1), club);

        Plongee plongee = new Plongee(site, diplome, LocalDate.of(2024, 8, 15), 20, 45);
        plongee.ajouteParticipant(plongeur.derniereLicence());
        club.organisePlongee(plongee);

        assertEquals(1, club.plongeesNonConformes().size());
    }

    @Test
    void testPlongeesNonConformesMixte() {
        Plongeur plongeur1 = new Plongeur("Plongeur1", 2);
        plongeur1.ajouterLicence("LIC-001", LocalDate.of(2024, 1, 1), club);

        Plongeur plongeur2 = new Plongeur("Plongeur2", 2);
        plongeur2.ajouterLicence("LIC-002", LocalDate.of(2023, 1, 1), club);

        Plongee plongee1 = new Plongee(site, diplome, LocalDate.of(2024, 8, 15), 20, 45);
        plongee1.ajouteParticipant(plongeur1.derniereLicence());
        club.organisePlongee(plongee1);

        Plongee plongee2 = new Plongee(site, diplome, LocalDate.of(2024, 8, 20), 15, 30);
        plongee2.ajouteParticipant(plongeur2.derniereLicence());
        club.organisePlongee(plongee2);

        assertEquals(1, club.plongeesNonConformes().size());
    }

    @Test
    void testToString() {
        assertNotNull(club.toString());
        assertTrue(club.toString().contains("Club Test"));
    }
}
