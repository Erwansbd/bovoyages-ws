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
	@NamedQuery(name="DatesVoyage.getAllValidDatesVoyages", query= "SELECT d from DatesVoyage d WHERE d.deleted =0"),
	@NamedQuery(name="DatesVoyage.getAllDealsDatesVoyages", query= "SELECT d from DatesVoyage d WHERE d.deleted =0 AND d.promotion =1")
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
	private int deleted;
	private int promotion;
	
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
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public int getPromotion() {
		return promotion;
	}
	public void setPromotion(int promotion) {
		this.promotion = promotion;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateAller == null) ? 0 : dateAller.hashCode());
		result = prime * result + ((dateRetour == null) ? 0 : dateRetour.hashCode());
		result = prime * result + deleted;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + nbrePlaces;
		result = prime * result + Float.floatToIntBits(prixHT);
		result = prime * result + promotion;
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
		DatesVoyage other = (DatesVoyage) obj;
		if (dateAller == null) {
			if (other.dateAller != null)
				return false;
		} else if (!dateAller.equals(other.dateAller))
			return false;
		if (dateRetour == null) {
			if (other.dateRetour != null)
				return false;
		} else if (!dateRetour.equals(other.dateRetour))
			return false;
		if (deleted != other.deleted)
			return false;
		if (id != other.id)
			return false;
		if (nbrePlaces != other.nbrePlaces)
			return false;
		if (Float.floatToIntBits(prixHT) != Float.floatToIntBits(other.prixHT))
			return false;
		if (promotion != other.promotion)
			return false;
		return true;
	}
	public DatesVoyage() {
		super();
	}
	public DatesVoyage(Date dateAller, Date dateRetour) {
		this.dateAller = dateAller;
		this.dateRetour = dateRetour;
	}
	
	
	
	
}
