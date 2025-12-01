/**
 * @(#) Club.java
 */
package FFSSM;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

public class Club {

    @Getter @Setter
    private DiplomeDeMoniteur president;

    @Getter @Setter
    private String nom;

    @Getter @Setter
    private String adresse;

    @Getter @Setter
    private String telephone;

    private List<Plongee> plongees = new ArrayList<>();

    public Club(DiplomeDeMoniteur president, String nom) {
        this.president = president;
        this.nom = nom;
    }

    public Set<Plongee> plongeesNonConformes() {
        Set<Plongee> nonConformes = new HashSet<>();
        for (Plongee p : plongees) {
            if (!p.estConforme()) {
                nonConformes.add(p);
            }
        }
        return nonConformes;
    }

    public void organisePlongee(Plongee p) {
        plongees.add(p);
    }

    public List<Plongee> getPlongees() {
        return new ArrayList<>(plongees);
    }

    @Override
    public String toString() {
        return "Club{" + "président=" + president + ", nom=" + nom + ", adresse=" + adresse + ", telephone=" + telephone + '}';
    }
}
