package FFSSM;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Embauche {

    private final LocalDate debut;

    @Setter
    private LocalDate fin;

    private final DiplomeDeMoniteur employe;

    private final Club employeur;

    public Embauche(LocalDate debut, DiplomeDeMoniteur employe, Club employeur) {
        this.debut = debut;
        this.employe = employe;
        this.employeur = employeur;
    }

    public void terminer(LocalDate dateFin) {
        this.fin = dateFin;
    }

    public boolean estTerminee() {
        return fin != null;
    }
}
