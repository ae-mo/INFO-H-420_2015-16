package org.camunda.bpm.infoh420.interview;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class BookEntity implements Serializable {
	private static  final long serialVersionUID = 1L;

	  @Id
	  @GeneratedValue
	  protected Long id;

	  @Version
	  protected long version;

	  protected String room;
	  protected String name;
	  protected String day1;
	  protected String month1;
	  protected String year1;
	  protected String fhour1;
	  protected String thour1;
	  protected String fminute1;
	  protected String tminute1;
	
	  public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDay1() {
		return day1;
	}
	public void setDay1(String day1) {
		this.day1 = day1;
	}
	public String getMonth1() {
		return month1;
	}
	public void setMonth1(String month1) {
		this.month1 = month1;
	}
	public String getYear1() {
		return year1;
	}
	public void setYear1(String year1) {
		this.year1 = year1;
	}
	public String getFhour1() {
		return fhour1;
	}
	public void setFhour1(String fhour1) {
		this.fhour1 = fhour1;
	}
	public String getThour1() {
		return thour1;
	}
	public void setThour1(String thour1) {
		this.thour1 = thour1;
	}
	public String getFminute1() {
		return fminute1;
	}
	public void setFminute1(String fminute1) {
		this.fminute1 = fminute1;
	}
	public String getTminute1() {
		return tminute1;
	}
	public void setTminute1(String tminute1) {
		this.tminute1 = tminute1;
	}
	  
	  

}
