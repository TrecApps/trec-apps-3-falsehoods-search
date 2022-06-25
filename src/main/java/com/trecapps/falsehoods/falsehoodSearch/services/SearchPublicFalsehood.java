package com.trecapps.falsehoods.falsehoodSearch.services;



import com.trecapps.falsehoods.falsehoodSearch.models.PublicFigure;
import com.trecapps.falsehoods.falsehoodSearch.models.Severity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class SearchPublicFalsehood
{
    /**
     * Used to provide Search Terms Google Style
     */
    String terms;

    /**
     * Limit hits via date
     */
    Date to, from;
    


    /**
     * Max Number of Entries to attempt to retrieve
     */
    int numberOfEntries, page;

    /**
     * Minimum Severity vs Maximum severity
     */
    Severity minimum, maximum;
    
    /**
     * 
     */
   
    PublicFigure official;
    
     @Value("20")
    byte officialType;

    public SearchPublicFalsehood() {
    	numberOfEntries = 20;
    }

    

    /**
	 * @return the officialType
	 */
	public byte getOfficialType() {
		return officialType;
	}

	/**
	 * @param officialType the officialType to set
	 */
	public void setOfficialType(byte officialType) {
		this.officialType = officialType;
	}

	/**
	 * @return the official
	 */
	public PublicFigure getOfficial() {
		return official;
	}

	/**
	 * @param official the official to set
	 */
	public void setOfficial(PublicFigure official) {
		this.official = official;
	}


	
	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @param terms
	 * @param to
	 * @param from
	 * @param numberOfEntries
	 * @param page
	 * @param minimum
	 * @param maximum
	 */
	public SearchPublicFalsehood(String terms, Date to, Date from, int numberOfEntries, int page, Severity minimum,
			Severity maximum, PublicFigure official) {
		super();
		this.terms = terms;
		this.to = to;
		this.from = from;
		this.numberOfEntries = numberOfEntries;
		this.page = page;
		this.minimum = minimum;
		this.maximum = maximum;
		this.official = official;
		this.officialType = 20;
	}

	@Override
	public String toString() {
		return "SearchPublicFalsehood{" +
				"terms='" + terms + '\'' +
				", to=" + to +
				", from=" + from +
				", numberOfEntries=" + numberOfEntries +
				", page=" + page +
				", minimum=" + minimum +
				", maximum=" + maximum +
				", official=" + official +
				", officialType=" + officialType +
				'}';
	}

	public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }


    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    public void setNumberOfEntries(int numberOfEntries) {
        this.numberOfEntries = numberOfEntries;
    }

    public Severity getMinimum() {
        return minimum;
    }

    public void setMinimum(Severity minimum) {
        this.minimum = minimum;
    }

    public Severity getMaximum() {
        return maximum;
    }

    public void setMaximum(Severity maximum) {
        this.maximum = maximum;
    }

}
