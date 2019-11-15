package fr.gtm.bovoyages.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "voyages")
@NamedQueries({
	@NamedQuery(name = "Voyage.getAllVoyages", query = "SELECT v FROM Voyage v"),
})
public class Voyage implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_voyage")
	private long id;
	private String region;
	private String descriptif;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_client")
	private Client client;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "fk_dates_voyage")
	private DatesVoyage datesVoyage;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "voyages_voyageurs", joinColumns = @JoinColumn(name = "fk_voyage"), inverseJoinColumns = @JoinColumn(name = "fk_voyageur"))
	private List<Voyageur> voyageurs = new ArrayList<>();


	public Voyage() {
	}

	public Voyage(String region, String descriptif, Client client, List<Voyageur> voyageurs) {
		this.region = region;
		this.descriptif = descriptif;
		this.client = client;
		this.voyageurs = voyageurs;
	}

	public Voyage(long id, String region, String descriptif, Client client, List<Voyageur> voyageurs,
			DatesVoyage datesVoyage) {
		super();
		this.id = id;
		this.region = region;
		this.descriptif = descriptif;
		this.client = client;
		this.voyageurs = voyageurs;
		this.datesVoyage = datesVoyage;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDescriptif() {
		return descriptif;
	}

	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Voyageur> getVoyageurs() {
		return voyageurs;
	}

	public void setVoyageurs(List<Voyageur> voyageurs) {
		this.voyageurs = voyageurs;
	}

	@Override
	public String toString() {
		return "Voyage [region=" + region + ", descriptif=" + descriptif + ", client=" + client + ", voyageurs="
				+ voyageurs + "]";
	}

	public DatesVoyage getDatesVoyage() {
		return datesVoyage;
	}

	public void setDatesVoyage(DatesVoyage datesVoyage) {
		this.datesVoyage = datesVoyage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((datesVoyage == null) ? 0 : datesVoyage.hashCode());
		result = prime * result + ((descriptif == null) ? 0 : descriptif.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((region == null) ? 0 : region.hashCode());
		result = prime * result + ((voyageurs == null) ? 0 : voyageurs.hashCode());
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
		Voyage other = (Voyage) obj;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (datesVoyage == null) {
			if (other.datesVoyage != null)
				return false;
		} else if (!datesVoyage.equals(other.datesVoyage))
			return false;
		if (descriptif == null) {
			if (other.descriptif != null)
				return false;
		} else if (!descriptif.equals(other.descriptif))
			return false;
		if (id != other.id)
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		if (voyageurs == null) {
			if (other.voyageurs != null)
				return false;
		} else if (!voyageurs.equals(other.voyageurs))
			return false;
		return true;
	}

}
