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

	public void persistApplication(DelegateExecution delegateExecution) {
		
		// Create new application instance
		Application application = new Application();
		ContactEntity contact = new ContactEntity(application.getId());
		ArrayList<DegreeEntity> degrees = new ArrayList<DegreeEntity>();
		ArrayList<JobEntity> experiences = new ArrayList<JobEntity>();
		
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
		DegreeEntity degree = new DegreeEntity(application.getId());
		degree.setTitle("Bachelor in Engineering");
		degree.setStatus("OBTAINED");
		degree.setSchool("Politecnico di Milano");
		degree.setField("Computer Engineering");
		degree.setCountryRegion("Italy");
		degrees.add(degree);
		
		// Set job info
		JobEntity job = new JobEntity(application.getId());
		job.setEmployer("Freedelity");
		job.setTitle("Web Developer");
		job.setStart(new GregorianCalendar(2015, Calendar.JULY, 6).getTime());
		job.setEnd(new GregorianCalendar(2015, Calendar.SEPTEMBER, 25).getTime());
		job.setCountryRegion("Brussels");
		job.setCity("Ixelles");
		experiences.add(job);
		
		application.setContact(contact);
		application.setDegrees(degrees);
		application.setExperiences(experiences);
		
		/*
	     Persist order instance and flush. After the flush the
	     id of the order instance is set.
		 */
		entityManager.persist(contact);
		entityManager.persist(degree);
		entityManager.persist(job);
		entityManager.flush();

		// Add newly created order id as process variable
		delegateExecution.setVariable("applicationId", application.getId());
	}	

}
