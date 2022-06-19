package com.trecapps.falsehoods.falsehoodSearch.models;

import javax.persistence.*;


@Table
@Entity
public class Institution //implements Comparable<Institution>
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
		Institution other = (Institution) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
	 * 
	 */
	public Institution() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param name
	 * @param approved
	 */
	public Institution(Long id, String name, byte approved) {
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

	//@Override
	public int compareTo(Institution o) {
		return id.compareTo(o.getId());
	}
}
