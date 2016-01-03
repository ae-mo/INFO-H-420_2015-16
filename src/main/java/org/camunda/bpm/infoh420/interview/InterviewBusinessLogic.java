package org.camunda.bpm.infoh420.interview;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Named
public class InterviewBusinessLogic {

	// Inject the entity manager
	@PersistenceContext
	private EntityManager entityManager;

	public void persistOrder(DelegateExecution delegateExecution) {
		
		// Create new application instance
		ApplicationEntity applicationEntity = new ApplicationEntity();
		Contact contact = new Contact();
		ArrayList<Degree> degrees = new ArrayList<Degree>();
		ArrayList<Job> experiences = new ArrayList<Job>();
		
		// Set contact info
		contact.setName("Andrea");
		contact.setAddress("Boulevard du Triomphe, 153");
		contact.setCountryRegion("Brussels");
		contact.setCity("Ixelles");
		contact.setZip("1050");
		ArrayList<String> phones = new ArrayList<String>();
		phones.add("+32484903921");
		phones.add("+393470622606");
		
		// Set degree info
		Degree degree = new Degree();
		degree.setTitle("Bachelor in Engineering");
		degree.setStatus("OBTAINED");
		degree.setSchool("Politecnico di Milano");
		degree.setField("Computer Engineering");
		degree.setCountryRegion("Italy");
		degrees.add(degree);
		
		// Set job info
		Job job = new Job();
		job.setEmployer("Freedelity");
		job.setTitle("Web Developer");
		job.setStart(new GregorianCalendar(2015, Calendar.JULY, 6).getTime());
		job.setEnd(new GregorianCalendar(2015, Calendar.SEPTEMBER, 25).getTime());
		job.setCountryRegion("Brussels");
		job.setCity("Ixelles");
		experiences.add(job);
		
		applicationEntity.setContact(contact);
		applicationEntity.setDegrees(degrees);
		applicationEntity.setExperiences(experiences);
		
		/*
	     Persist order instance and flush. After the flush the
	     id of the order instance is set.
		 */
		entityManager.persist(applicationEntity);
		entityManager.flush();

		// Add newly created order id as process variable
		delegateExecution.setVariable("applicationId", applicationEntity.getId());
	}	

}
