/**
 * @(#) Plongee.java
 */
package FFSSM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Plongee {

	@Setter
	private Site lieu;

	@Setter
	private DiplomeDeMoniteur chefDePalanquee;

	@Setter
	private LocalDate date;

	@Setter
	private int profondeur;

	@Setter
	private int duree;

	private List<Licence> participants = new ArrayList<>();

	public Plongee(Site lieu, DiplomeDeMoniteur chefDePalanquee, LocalDate date, int profondeur, int duree) {
		this.lieu = lieu;
		this.chefDePalanquee = chefDePalanquee;
		this.date = date;
		this.profondeur = profondeur;
		this.duree = duree;
	}

	public void ajouteParticipant(Licence licence) {
		participants.add(licence);
	}

	public boolean estConforme() {
		for (Licence licence : participants) {
			if (!licence.estValide(date)) {
				return false;
			}
		}
		return true;
	}
}
