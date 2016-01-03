package org.camunda.bpm.infoh420.interview;

import java.util.Date;

public class Job {
	
	protected String employer;
	protected String title;
	protected Date start;
	protected Date end;
	protected String countryRegion;
	protected String city;
	
	/**
	 * @return the employer
	 */
	public String getEmployer() {
		return employer;
	}
	
	/**
	 * @param employer the employer to set
	 */
	public void setEmployer(String employer) {
		this.employer = employer;
	}
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @return the start
	 */
	public Date getStart() {
		return start;
	}
	
	/**
	 * @param start the start to set
	 */
	public void setStart(Date start) {
		this.start = start;
	}
	
	/**
	 * @return the end
	 */
	public Date getEnd() {
		return end;
	}
	
	/**
	 * @param end the end to set
	 */
	public void setEnd(Date end) {
		this.end = end;
	}
	
	/**
	 * @return the countryRegion
	 */
	public String getCountryRegion() {
		return countryRegion;
	}
	
	/**
	 * @param countryRegion the countryRegion to set
	 */
	public void setCountryRegion(String countryRegion) {
		this.countryRegion = countryRegion;
	}
	
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

}
