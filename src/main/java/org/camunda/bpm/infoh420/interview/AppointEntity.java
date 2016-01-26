package org.camunda.bpm.infoh420.interview;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class AppointEntity implements Serializable {
	private static  final long serialVersionUID = 1L;

	  @Id
	  @GeneratedValue
	  protected Long id;

	  @Version
	  protected long version;

	  protected String namei;
	  protected String nameie;
	  protected String day2;
	  protected String month2;
	  protected String year2;
	  protected String hour2;
	  protected String minute2;
	  
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
	public String getNamei() {
		return namei;
	}
	public void setNamei(String namei) {
		this.namei = namei;
	}
	public String getNameie() {
		return nameie;
	}
	public void setNameie(String nameie) {
		this.nameie = nameie;
	}
	public String getDay2() {
		return day2;
	}
	public void setDay2(String day2) {
		this.day2 = day2;
	}
	public String getMonth2() {
		return month2;
	}
	public void setMonth2(String month2) {
		this.month2 = month2;
	}
	public String getYear2() {
		return year2;
	}
	public void setYear2(String year2) {
		this.year2 = year2;
	}
	public String getHour2() {
		return hour2;
	}
	public void setHour2(String hour2) {
		this.hour2 = hour2;
	}
	public String getMinute2() {
		return minute2;
	}
	public void setMinute2(String minute2) {
		this.minute2 = minute2;
	}
	  
	  
}
