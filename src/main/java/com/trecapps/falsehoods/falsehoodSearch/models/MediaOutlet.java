package com.trecapps.falsehoods.falsehoodSearch.models;

import javax.persistence.*;


@Entity
@Table
public class MediaOutlet implements Comparable<MediaOutlet>{


	@Override
	public int compareTo(MediaOutlet o) {
		return outletId.compareTo(o.getOutletId());
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer outletId;
	
	@Column
	short foundationYear;
	
	@Column
	String name;
	
	@Column(nullable=false, columnDefinition = "tinyint default 0")
	byte approved;


	public MediaOutlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MediaOutlet(Integer outletId, short foundationYear, String name, byte approved) {
		super();
		this.outletId = outletId;
		this.foundationYear = foundationYear;
		this.name = name;
		this.approved = approved;
	}

	/**
	 * @return the approved
	 */
	public byte getApproved() {
		return approved;
	}

	/**
	 * @param approved the approved to set
	 */
	public void setApproved(byte approved) {
		this.approved = approved;
	}

	public Integer getOutletId() {
		return outletId;
	}

	public void setOutletId(Integer outletId) {
		this.outletId = outletId;
	}

	public short getFoundationYear() {
		return foundationYear;
	}

	public void setFoundationYear(short foundationYear) {
		this.foundationYear = foundationYear;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((outletId == null) ? 0 : outletId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof MediaOutlet))
			return false;
		MediaOutlet other = (MediaOutlet) obj;
		if (outletId == null) {
			if (other.outletId != null)
				return false;
		} else if (!outletId.equals(other.outletId))
			return false;
		return true;
	}
	
	
}
