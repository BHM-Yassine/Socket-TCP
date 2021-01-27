package tp1.socket.ex3;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Personne implements Serializable {
	private String nom;
	private String pr�nom;
	private Date date_naissance;

	Personne(String nom, String pr�nom, Date date_naissance) {
		this.nom = nom;
		this.pr�nom = pr�nom;
		this.date_naissance = date_naissance;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPr�nom() {
		return pr�nom;
	}

	public void setPr�nom(String pr�nom) {
		this.pr�nom = pr�nom;
	}

	public Date getDate_naissance() {
		return date_naissance;
	}

	public void setDate_naissance(Date date_naissance) {
		this.date_naissance = date_naissance;
	}

	public int calcul_age() {
		Calendar curr = Calendar.getInstance();
		Calendar birth = Calendar.getInstance();
		birth.setTime(this.date_naissance);

		int yeardiff = curr.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
		curr.add(Calendar.YEAR, -yeardiff);
		if (birth.after(curr)) {
			yeardiff = yeardiff - 1;
		}

		return yeardiff;
	}
}
