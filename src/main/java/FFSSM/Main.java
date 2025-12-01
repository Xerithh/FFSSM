package FFSSM;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("--------- Test ---------\n");

        // Création des plongeurs
        Plongeur moniteur1 = new Plongeur("Dupont", 4);
        moniteur1.setPrenom("Jean");
        moniteur1.setGroupeSanguin(GroupeSanguin.APLUS);

        Plongeur plongeur1 = new Plongeur("Martin", 2);
        plongeur1.setPrenom("Pierre");

        Plongeur plongeur2 = new Plongeur("Durand", 1);
        plongeur2.setPrenom("Marie");

        System.out.println("Plongeurs créés:");
        System.out.println("- " + moniteur1.getPrenom() + " " + moniteur1.getNom() + " (niveau " + moniteur1.getNiveau() + ")");
        System.out.println("- " + plongeur1.getPrenom() + " " + plongeur1.getNom() + " (niveau " + plongeur1.getNiveau() + ")");
        System.out.println("- " + plongeur2.getPrenom() + " " + plongeur2.getNom() + " (niveau " + plongeur2.getNiveau() + ")");

        // Création d'un diplôme de moniteur
        DiplomeDeMoniteur diplome = new DiplomeDeMoniteur(moniteur1, 12345);
        System.out.println("\nDiplôme de moniteur créé: n°" + diplome.getNumeroDiplome());

        // Création d'un club
        Club club = new Club(diplome, "Club Subaquatique de Toulouse");
        club.setAdresse("15 rue de la Plongée, 31000 Toulouse");
        club.setTelephone("05 61 00 00 00");
        System.out.println("\nClub créé: " + club.getNom());

        // Création d'un nouveau plongeur qui obtient le diplôme de moniteur et devient président
        Plongeur futurPresident = new Plongeur("Leroy", 5);
        futurPresident.setPrenom("Alice");
        DiplomeDeMoniteur diplomePresident = new DiplomeDeMoniteur(futurPresident, 67890);
        club.setPresident(diplomePresident);
        System.out.println("Nouveau président: " + club.getPresident().getPossesseur().getPrenom() + " " + club.getPresident().getPossesseur().getNom());

        // Embauche du moniteur
        diplome.nouvelleEmbauche(club, LocalDate.of(2024, 1, 1));
        System.out.println("Moniteur embauché par: " + diplome.employeurActuel().getNom());

        // Ajout de licences aux plongeurs
        plongeur1.ajouterLicence("LIC-001", LocalDate.of(2024, 6, 1), club);
        plongeur2.ajouterLicence("LIC-002", LocalDate.of(2023, 1, 1), club); // Licence expirée

        System.out.println("\nLicences:");
        System.out.println("- " + plongeur1.getNom() + ": " + plongeur1.derniereLicence().numero);
        System.out.println("- " + plongeur2.getNom() + ": " + plongeur2.derniereLicence().numero);

        // Création d'un site de plongée
        Site site = new Site("Calanques de Marseille", "Site méditerranéen");
        System.out.println("\nSite créé: " + site.getNom());

        // Création de plongées
        LocalDate datePlongee1 = LocalDate.of(2024, 8, 15);
        Plongee plongee1 = new Plongee(site, diplome, datePlongee1, 20, 45);
        plongee1.ajouteParticipant(plongeur1.derniereLicence());
        club.organisePlongee(plongee1);

        LocalDate datePlongee2 = LocalDate.of(2024, 8, 20);
        Plongee plongee2 = new Plongee(site, diplome, datePlongee2, 15, 30);
        plongee2.ajouteParticipant(plongeur2.derniereLicence()); // Licence expirée
        club.organisePlongee(plongee2);

        System.out.println("\nPlongées organisées:");
        System.out.println("- Plongée 1 (" + datePlongee1 + "): conforme = " + plongee1.estConforme());
        System.out.println("- Plongée 2 (" + datePlongee2 + "): conforme = " + plongee2.estConforme());

        // Vérification des plongées non conformes
        System.out.println("\nNombre de plongées non conformes: " + club.plongeesNonConformes().size());

        // Test de validité de licence
        Licence licence1 = plongeur1.derniereLicence();
        System.out.println("\nTest de validité de licence:");
        System.out.println("- Licence de " + plongeur1.getNom() + " valide le 2024-08-15: " + licence1.estValide(LocalDate.of(2024, 8, 15)));
        System.out.println("- Licence de " + plongeur1.getNom() + " valide le 2025-08-15: " + licence1.estValide(LocalDate.of(2025, 8, 15)));

        // Fin d'embauche
        diplome.terminerEmbauche(LocalDate.of(2024, 12, 31));
        System.out.println("\nEmbauche terminée. Employeur actuel: " + diplome.employeurActuel());

        System.out.println("\n-------- Fin --------");
    }
}
