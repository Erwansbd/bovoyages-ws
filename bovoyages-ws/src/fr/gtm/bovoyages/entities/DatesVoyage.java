package fr.gtm.bovoyages.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="dates_voyages")
@NamedQueries({
	//@NamedQuery(name = "DatesDeVoyage.getAllDatesVoyage",query = "SELECT v FROM DateDeVoyage v")
})

public class DatesVoyage implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pk_date_voyage")
	private long id;
	@Column(name="date_depart")
	private Date dateAller;
	@Column(name="date_retour")
	private Date dateRetour;
	private float prixHT;
	@Column(name="nb_places")
	private int nbrePlaces;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDateAller() {
		return dateAller;
	}
	public void setDateAller(Date dateAller) {
		this.dateAller = dateAller;
	}
	public Date getDateRetour() {
		return dateRetour;
	}
	public void setDateRetour(Date dateRetour) {
		this.dateRetour = dateRetour;
	}
	public float getPrixHT() {
		return prixHT;
	}
	public void setPrixHT(float prixHT) {
		this.prixHT = prixHT;
	}
	public int getNbrePlaces() {
		return nbrePlaces;
	}
	public void setNbrePlaces(int nbrePlaces) {
		this.nbrePlaces = nbrePlaces;
	}
	@Override
	public String toString() {
		return "DatesVoyage [dateAller=" + dateAller + ", dateRetour=" + dateRetour + ", prixHT=" + prixHT
				+ ", nbrePlaces=" + nbrePlaces + "]";
	}
	
	
	
}
