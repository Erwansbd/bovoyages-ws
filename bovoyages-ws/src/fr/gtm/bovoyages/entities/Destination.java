package fr.gtm.bovoyages.entities;
//
import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "destinations")
@NamedQueries({
		@NamedQuery(name = "Destination.getDestinationByDatesVoyage", query = "SELECT d.datesVoyage FROM Destination d WHERE d.datesVoyage IS NOT EMPTY"),
		@NamedQuery(name = "Destination.getAllDestinations", query = "SELECT d FROM Destination d"),
		@NamedQuery(name = "Destination.getAllValidDestinations", query = "SELECT d FROM Destination d WHERE d.deleted=0")})
public class Destination implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_destination")
	private long id;
	private String region;
	private String description;
	private int deleted;
	// private String nomImages;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_destination")
	private Set<DatesVoyage> datesVoyage = new HashSet<>();

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "images", joinColumns = @JoinColumn(name = "fk_destination"))
	@Column(name = "image")
	private List<String> images;

	public Destination() {

	}

	public Destination(String region, String description) {
		this.region = region;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public Set<DatesVoyage> getDatesVoyage() {
		return datesVoyage;
	}

	public void setDatesVoyage(Set<DatesVoyage> datesVoyage) {
		this.datesVoyage = datesVoyage;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Destination [id=" + id + ", region=" + region + ", description=" + description + ", nomImages=" + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datesVoyage == null) ? 0 : datesVoyage.hashCode());
		result = prime * result + deleted;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((images == null) ? 0 : images.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
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
		Destination other = (Destination) obj;
		if (datesVoyage == null) {
			if (other.datesVoyage != null)
				return false;
		} else if (!datesVoyage.equals(other.datesVoyage))
			return false;
		if (deleted != other.deleted)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (images == null) {
			if (other.images != null)
				return false;
		} else if (!images.equals(other.images))
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		return true;
	}
}
