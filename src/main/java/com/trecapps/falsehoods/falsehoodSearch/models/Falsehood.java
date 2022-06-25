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
@Table(name = "falsehood")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Falsehood implements Comparable<Falsehood>{


	public int compareTo(Falsehood f)
	{
		return id.compareTo(f.getId());
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	BigInteger id;
	
	@ManyToOne
	@JoinColumn()
	MediaOutlet outlet;

	
	// Verification Status
	
	
	@Column
	byte status;
	
	// Media Type
	@Transient 
	public static final byte VIDEO = 0;	
	@Transient 
	public static final byte ARTICLE = 1;
	
	@Column(name = "mediaType")
	byte mediaType;
	
	
	@Transient 
	public static final byte LIE = 0;			// Falsehood cites a direct lie
	@Transient 
	public static final byte FABRICATION = 1;	// Falsehood cites a likely lie
	@Transient 
	public static final byte MISLEADING = 2;		// No direct lie but point relies heavily on omission
	@Transient 
	public static final byte DOUBLE_STANDARD = 3;// Exposes a double Standard utilized by the entity
	@Transient 
	public static final byte BIAS = 4;			// Could be truthful, but reveals a bias that entity does not acknowledge
	@Transient 
	public static final byte HONEST_BIAS = 5;		// Could be truthful, based on a bias that entity acknowledges
	
	@Column
    Severity severity;
	
	@ManyToOne
	@JoinColumn(name = "author1_id")
	PublicFigure author1;
	
	@ManyToOne
	@JoinColumn(name = "author2_id")
	PublicFigure author2;
	
	@Column
	String source;
	
	@Column(name ="dateMade")
	Date dateMade;
	
	@Column(name = "contentId")
	String contentId;

	@Column(name = "userId")
	@NotNull
	String userId;
	
	@Transient
	String tags;


	public Falsehood clone()
	{
		return new Falsehood(id, outlet, status, mediaType, severity, author1, author2, source, dateMade, contentId, userId, tags);
	}
	
	public boolean canUpgrade()
	{
		return severity.GetValue() == (byte)1 || severity.GetValue() == (byte)4;
	}
	
	public boolean upgrade()
	{
		if(!canUpgrade())
			return false;
		severity = Severity.values()[severity.GetValue()-1];
		status = FalsehoodStatus.MODIFIED.GetValue();
		return true;
	}

	@Override
	public String toString() {
		return "Falsehood [id=" + id + ", outlet=" + outlet + ", status=" + status
				+ ", mediaType=" + mediaType + ", severity=" + severity + ", author1=" + author1 + ", author2="
				+ author2 + ", source=" + source + ", dateMade=" + dateMade + ", contentId=" + contentId + ", tags=" + tags + "]";
	}
	

}
