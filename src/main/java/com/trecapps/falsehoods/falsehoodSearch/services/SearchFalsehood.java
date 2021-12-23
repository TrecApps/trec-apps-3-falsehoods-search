package com.trecapps.falsehoods.falsehoodSearch.services;

import com.trecapps.base.InfoResource.models.MediaOutlet;
import com.trecapps.base.InfoResource.models.PublicFigure;
import com.trecapps.base.InfoResource.models.MediaOutlet;
import com.trecapps.base.InfoResource.models.PublicFigure;
import com.trecapps.base.FalsehoodModel.models.Severity;
import org.springframework.stereotype.Component;

import java.sql.Date;


@Component
public class SearchFalsehood 
{
	/**
	 * Used to provide Search Terms Google Style
	 */
	String terms;
	
	/**
	 * Limit hits via date
	 */
	Date to, from;
	

	
	MediaOutlet outlet;
	
	/**
	 * Max Number of Entries to attempt to retrieve
	 */
	int numberOfEntries, page;
	
	/**
	 * Minimum Severity vs Maximum severity
	 */
	Severity minimum, maximum;
	
	
	PublicFigure author;
	
	




	/**
	 * @param terms
	 * @param to
	 * @param from
	 * @param outlet
	 * @param numberOfEntries
	 * @param page
	 * @param minimum
	 * @param maximum
	 * @param author
	 */
	public SearchFalsehood(String terms, Date to, Date from, MediaOutlet outlet,
			int numberOfEntries, int page, Severity minimum, Severity maximum, PublicFigure author) {
		super();
		this.terms = terms;
		this.to = to;
		this.from = from;
		this.outlet = outlet;
		this.numberOfEntries = numberOfEntries;
		this.page = page;
		this.minimum = minimum;
		this.maximum = maximum;
		this.author = author;
	}
	
	

	/**
	 * @return the outlet
	 */
	public MediaOutlet getOutlet() {
		return outlet;
	}



	/**
	 * @param outlet the outlet to set
	 */
	public void setOutlet(MediaOutlet outlet) {
		this.outlet = outlet;
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
	 * @return the author
	 */
	public PublicFigure getAuthor() {
		return author;
	}



	/**
	 * @param author the author to set
	 */
	public void setAuthor(PublicFigure author) {
		this.author = author;
	}



	public SearchFalsehood() {
		super();
		numberOfEntries = 20;
	}

	/**
	 * @return the terms
	 */
	public String getTerms() {
		return terms;
	}

	/**
	 * @param terms the terms to set
	 */
	public void setTerms(String terms) {
		this.terms = terms;
	}

	/**
	 * @return the to
	 */
	public Date getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(Date to) {
		this.to = to;
	}

	/**
	 * @return the from
	 */
	public Date getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(Date from) {
		this.from = from;
	}


	/**
	 * @return the numberOfEntries
	 */
	public int getNumberOfEntries() {
		return numberOfEntries;
	}

	/**
	 * @param numberOfEntries the numberOfEntries to set
	 */
	public void setNumberOfEntries(int numberOfEntries) {
		this.numberOfEntries = numberOfEntries;
	}

	/**
	 * @return the minimum
	 */
	public Severity getMinimum() {
		return minimum;
	}

	/**
	 * @param minimum the minimum to set
	 */
	public void setMinimum(Severity minimum) {
		this.minimum = minimum;
	}

	/**
	 * @return the maximum
	 */
	public Severity getMaximum() {
		return maximum;
	}

	/**
	 * @param maximum the maximum to set
	 */
	public void setMaximum(Severity maximum) {
		this.maximum = maximum;
	}


	
	
}
