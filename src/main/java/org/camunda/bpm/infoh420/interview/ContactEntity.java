package org.camunda.bpm.infoh420.interview;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Holds a person's contact information.
 *
 */
public class ContactEntity {

	@Version
	protected long version;
	
	@Id
	protected Long applicationId;
	protected String name;
	protected String address;
	protected String countryRegion;
	protected String city;
	protected String zip;
	protected ArrayList<String> phones;
	
	
	
	public ContactEntity(Long applicationId) {
		super();
		this.applicationId = applicationId;
	}

	/**
	 * @return the applicationId
	 */
	public Long getApplicationId() {
		return applicationId;
	}

	/**
	 * @param applicationId the applicationId to set
	 */
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
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
	
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
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
	
	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}
	
	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	/**
	 * @return the phones
	 */
	public ArrayList<String> getPhones() {
		return phones;
	}
	
	/**
	 * @param phones the phones to set
	 */
	public void setPhones(ArrayList<String> phones) {
		this.phones = phones;
	}
}
