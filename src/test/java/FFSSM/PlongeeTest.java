package FFSSM;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class PlongeeTest {

    private Plongee plongee;
    private Site site;
    private DiplomeDeMoniteur diplome;
    private Club club;
    private LocalDate datePlongee;

    @BeforeEach
    void setUp() {
        Plongeur moniteur = new Plongeur("Moniteur", 4);
        diplome = new DiplomeDeMoniteur(moniteur, 1);
        club = new Club(diplome, "Club Test");
        site = new Site("Site Test", "Description");
        datePlongee = LocalDate.of(2024, 8, 15);
        plongee = new Plongee(site, diplome, datePlongee, 20, 45);
    }

    @Test
    void testCreationPlongee() {
        assertEquals(site, plongee.getLieu());
        assertEquals(diplome, plongee.getChefDePalanquee());
        assertEquals(datePlongee, plongee.getDate());
        assertEquals(20, plongee.getProfondeur());
        assertEquals(45, plongee.getDuree());
    }

    @Test
    void testPlongeeSansParticipantEstConforme() {
        assertTrue(plongee.estConforme());
    }

    @Test
    void testPlongeeAvecLicenceValideEstConforme() {
        Plongeur plongeur = new Plongeur("Plongeur", 2);
        plongeur.ajouterLicence("LIC-001", LocalDate.of(2024, 1, 1), club);
        plongee.ajouteParticipant(plongeur.derniereLicence());
        assertTrue(plongee.estConforme());
    }

    @Test
    void testPlongeeAvecLicenceExpireeNonConforme() {
        Plongeur plongeur = new Plongeur("Plongeur", 2);
        plongeur.ajouterLicence("LIC-001", LocalDate.of(2023, 1, 1), club);
        plongee.ajouteParticipant(plongeur.derniereLicence());
        assertFalse(plongee.estConforme());
    }

    @Test
    void testPlongeeAvecMixteConformite() {
        Plongeur plongeur1 = new Plongeur("Plongeur1", 2);
        plongeur1.ajouterLicence("LIC-001", LocalDate.of(2024, 1, 1), club);
        
        Plongeur plongeur2 = new Plongeur("Plongeur2", 2);
        plongeur2.ajouterLicence("LIC-002", LocalDate.of(2023, 1, 1), club);

        plongee.ajouteParticipant(plongeur1.derniereLicence());
        plongee.ajouteParticipant(plongeur2.derniereLicence());

        assertFalse(plongee.estConforme());
    }

    @Test
    void testGetParticipants() {
        Plongeur plongeur = new Plongeur("Plongeur", 2);
        plongeur.ajouterLicence("LIC-001", LocalDate.of(2024, 1, 1), club);
        plongee.ajouteParticipant(plongeur.derniereLicence());
        assertEquals(1, plongee.getParticipants().size());
    }
}
