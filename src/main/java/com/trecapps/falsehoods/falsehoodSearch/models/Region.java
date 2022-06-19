package com.trecapps.falsehoods.falsehoodSearch.models;


import javax.persistence.*;

@Table
@Entity
public class Region implements Comparable<Region> {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	Long id;
	
	@Column
	String name;
	
	@Column(nullable=false, columnDefinition = "tinyint default 0")
	byte approved;


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Region))
			return false;
		Region other = (Region) obj;
		return (id == null)? false: id.equals(other.getId());
	}

	/**
	 * 
	 */
	public Region() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @param id
	 * @param name
	 * @param approved
	 */
	public Region(Long id, String name, byte approved) {
		super();
		this.id = id;
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

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int compareTo(Region o) {
		return id.compareTo(o.getId());
	}
}
