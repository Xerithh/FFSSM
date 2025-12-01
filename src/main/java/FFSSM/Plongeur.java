package FFSSM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class Plongeur extends Personne {

    @Getter @Setter
    private int niveau;

    @Getter @Setter
    private GroupeSanguin groupeSanguin;

    private List<Licence> licences = new ArrayList<>();

    public Plongeur(String nom, int niveau) {
        super(nom);
        this.niveau = niveau;
    }

    public void ajouterLicence(String numero, LocalDate delivrance, Club club) {
        Licence licence = new Licence(this, numero, delivrance, club);
        licences.add(licence);
    }

    public Licence derniereLicence() {
        if (licences.isEmpty()) {
            return null;
        }
        return licences.get(licences.size() - 1);
    }

    public List<Licence> getLicences() {
        return new ArrayList<>(licences);
    }
}
