package tp1.socket.ex3;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Personne implements Serializable {
	private String nom;
	private String prénom;
	private Date date_naissance;

	Personne(String nom, String prénom, Date date_naissance) {
		this.nom = nom;
		this.prénom = prénom;
		this.date_naissance = date_naissance;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrénom() {
		return prénom;
	}

	public void setPrénom(String prénom) {
		this.prénom = prénom;
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
