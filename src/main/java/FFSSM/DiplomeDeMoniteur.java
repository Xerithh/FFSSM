/**
 * @(#) Moniteur.java
 */
package FFSSM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class DiplomeDeMoniteur {

    @Getter
    private final int numeroDiplome;
    
    @Getter
    private final Plongeur possesseur;

    private List<Embauche> embauches = new ArrayList<>();

    public DiplomeDeMoniteur(Plongeur possesseur, int numeroDiplome) {
        this.numeroDiplome = numeroDiplome;
        this.possesseur = possesseur;
    }

    public Club employeurActuel() {
        if (embauches.isEmpty()) {
            return null;
        }
        Embauche derniereEmbauche = embauches.get(embauches.size() - 1);
        if (derniereEmbauche.estTerminee()) {
            return null;
        }
        return derniereEmbauche.getEmployeur();
    }
    
    public void nouvelleEmbauche(Club employeur, LocalDate debutNouvelle) {   
        Embauche embauche = new Embauche(debutNouvelle, this, employeur);
        embauches.add(embauche);
    }

    public void terminerEmbauche(LocalDate fin) {
        if (!embauches.isEmpty()) {
            Embauche derniereEmbauche = embauches.get(embauches.size() - 1);
            if (!derniereEmbauche.estTerminee()) {
                derniereEmbauche.terminer(fin);
            }
        }
    }

    public List<Embauche> emplois() {
        return new ArrayList<>(embauches);
    }
}
