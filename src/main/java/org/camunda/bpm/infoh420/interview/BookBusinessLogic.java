package org.camunda.bpm.infoh420.interview;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.camunda.bpm.engine.cdi.jsf.TaskForm;
import org.camunda.bpm.engine.delegate.DelegateExecution;

@Stateless
@Named
public class BookBusinessLogic {

	  // Inject the entity manager
	  @PersistenceContext
	  private EntityManager entityManager;
	  
	  // Inject task form available through the Camunda cdi artifact
	  @Inject
	  private TaskForm taskForm;
	  private static Logger LOGGER = Logger.getLogger(BookBusinessLogic.class.getName());

	  public void persistBooking(DelegateExecution delegateExecution) {
	    // Create new order instance
	    BookEntity bookEntity = new BookEntity();

	    // Get all process variables
	    Map<String, Object> variables = delegateExecution.getVariables();

	    // Set order attributes
	    bookEntity.setName((String) variables.get("name"));
	    bookEntity.setRoom((String) variables.get("room"));
	    bookEntity.setDay1((String) variables.get("day1"));
	    bookEntity.setMonth1((String) variables.get("month1"));
	    bookEntity.setYear1((String) variables.get("year1"));
	    bookEntity.setFhour1((String) variables.get("fhour1"));
	    bookEntity.setFminute1((String) variables.get("fminute1"));
	    bookEntity.setThour1((String) variables.get("thour1"));
	    bookEntity.setTminute1((String) variables.get("tminute1"));
	    // Persist order instance and flush. After the flush the
	    // id of the order instance is set.
	    entityManager.persist(bookEntity);
	    entityManager.flush();

	    // Remove no longer needed process variables
	    delegateExecution.removeVariables(variables.keySet());

	    // Add newly created order id as process variable
	    delegateExecution.setVariable("bookId", bookEntity.getId());
	  }
	  	  
	  public BookEntity getBooking(Long bookId) {
		    // Load order entity from database
		    return entityManager.find(BookEntity.class, bookId);
	  }

	  /*
	    Merge updated order entity and complete task form in one transaction. This ensures
	    that both changes will rollback if an error occurs during transaction.
	   */
	  public void mergeBookingAndCompleteTask(BookEntity BookEntity) {
	    // Merge detached order entity with current persisted state
	    entityManager.merge(BookEntity);
	    try {
	      // Complete user task from
	      taskForm.completeTask();
	    } catch (IOException e) {
	      // Rollback both transactions on error
	      throw new RuntimeException("Cannot complete task", e);
	    }
	  }
		  
	  public void bookingOrder(DelegateExecution delegateExecution) {
		  	BookEntity booking = getBooking((Long) delegateExecution.getVariable("bookId"));
		    LOGGER.log(Level.INFO, "\n\n\nSending Email:\nDear {0}, your booking {1} of room {2} has been done.\n\n\n", new String[]{booking.getName(), String.valueOf(booking.getId()), booking.getRoom()});
		  }

	
}
