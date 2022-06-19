package com.trecapps.falsehoods.falsehoodSearch.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.sql.Date;


@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PublicFalsehood implements Comparable<PublicFalsehood>{

	@Override
	public int compareTo(PublicFalsehood o) {
		return id.compareTo(o.getId());
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	BigInteger id;
	
	@Column
	byte status;
	// Verification Status
	
	@Transient 
	public static final byte SUBMITTED = 0;	// Has been submitted, but not verified as a false hood
	@Transient 
	public static final byte VERIFIED = 1;	// Has been verified by staff
	@Transient 
	public static final byte CHALLENGED = 2;	// Has been verified, but is now being challenged
	@Transient 
	public static final byte REVERIFIED = 3;	// Has been verified and the challenge has failed
	@Transient 
	public static final byte MODIFIED = 4;	// Has been modified from an earlier version
	@Transient 
	public static final byte OVERTURNED = 5;	// Has previously been verified, but was overturned
	@Transient
	public static final byte REJECTED = 6;
	
	@ManyToOne
	@JoinColumn
	PublicFigure official;
	
	@Column
	byte officialType;
	
	@Transient
	public static final byte POLITICIAN = 0;
	@Transient
	public static final byte LAW_ENFORCEMENT = 1;
	@Transient
	public static final byte INTELLIGENCE = 2;
	@Transient
	public static final byte MILITARY = 3;
	@Transient 
	public static final byte PUBLIC_HEALTH = 4;
	@Transient
	public static final byte ECONOMIST = 5;
	@Transient
	public static final byte ENVIRONMENTALIST = 6;
	@Transient
	public static final byte CORPORATE_EXECUTIVE = 7;
	
	@Transient
	public static final byte OTHER = 15;
	
	@ManyToOne
	@JoinColumn
	Region region;
	
	@ManyToOne
	@JoinColumn
	Institution institution;
	
	@Column
    Severity severity;
	
	@Column
	Date dateMade;


	@Column
	@NotNull
	String userId;
	
	@Transient
	String tags;


	
	public boolean canUpgrade()
	{
		return severity.GetValue() == (byte)1 || severity.GetValue() == (byte)4;
	}
	
	public boolean upgrade()
	{
		if(!canUpgrade())
			return false;
		severity = Severity.values()[severity.GetValue()-1];
		return true;
	}
	
	public PublicFalsehood clone()
	{
		return new PublicFalsehood(id, status, official, officialType, region, institution, severity, dateMade, userId, tags);
	}

	@Override
	public String toString() {
		return "PublicFalsehood{" +
				"id=" + id +
				", status=" + status +
				", official=" + official +
				", officialType=" + officialType +
				", region=" + region +
				", institution=" + institution +
				", severity=" + severity +
				", dateMade=" + dateMade +
				", userId='" + userId + '\'' +
				", tags='" + tags + '\'' +
				'}';
	}
}
