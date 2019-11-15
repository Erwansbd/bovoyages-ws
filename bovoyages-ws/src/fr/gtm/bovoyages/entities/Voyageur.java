package fr.gtm.bovoyages.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.*;
@Entity
@Table(name="voyageurs")
public class Voyageur implements Serializable {
	
	
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="pk_voyageur")
private long id;

private String civilite;
private String nom;
private String prenom;
@Column(name="date_naissance")
private Date dateNaissance;



public Voyageur() {}


public Voyageur(long id, String civilite, String nom, String prenom, Date dateNaissance) {
	super();
	this.id = id;
	this.civilite = civilite;
	this.nom = nom;
	this.prenom = prenom;
	this.dateNaissance = dateNaissance;
}


public Voyageur(String civilite, String nom, String prenom, Date dateNaissance) {
	super();
	this.civilite = civilite;
	this.nom = nom;
	this.prenom = prenom;
	this.dateNaissance = dateNaissance;
}


public long getId() {
	return id;
}


public void setId(long id) {
	this.id = id;
}


public String getCivilite() {
	return civilite;
}


public void setCivilite(String civilite) {
	this.civilite = civilite;
}


public String getNom() {
	return nom;
}


public void setNom(String nom) {
	this.nom = nom;
}


public String getPrenom() {
	return prenom;
}


public void setPrenom(String prenom) {
	this.prenom = prenom;
}


public Date getDateNaissance() {
	return dateNaissance;
}


public void setDateNaissance(Date dateNaissance) {
	this.dateNaissance = dateNaissance;
}


@Override
public String toString() {
	return "Voyageurs [civilite=" + civilite + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance="
			+ dateNaissance + "]";
}


@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((civilite == null) ? 0 : civilite.hashCode());
	result = prime * result + ((dateNaissance == null) ? 0 : dateNaissance.hashCode());
	result = prime * result + (int) (id ^ (id >>> 32));
	result = prime * result + ((nom == null) ? 0 : nom.hashCode());
	result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
	return result;
}


@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Voyageur other = (Voyageur) obj;
	if (civilite == null) {
		if (other.civilite != null)
			return false;
	} else if (!civilite.equals(other.civilite))
		return false;
	if (dateNaissance == null) {
		if (other.dateNaissance != null)
			return false;
	} else if (!dateNaissance.equals(other.dateNaissance))
		return false;
	if (id != other.id)
		return false;
	if (nom == null) {
		if (other.nom != null)
			return false;
	} else if (!nom.equals(other.nom))
		return false;
	if (prenom == null) {
		if (other.prenom != null)
			return false;
	} else if (!prenom.equals(other.prenom))
		return false;
	return true;
}


}
