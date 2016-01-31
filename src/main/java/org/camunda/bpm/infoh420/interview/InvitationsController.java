package org.camunda.bpm.infoh420.interview;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.cdi.BusinessProcess;
import org.camunda.bpm.infoh420.interview.*;
import org.camunda.bpm.engine.cdi.jsf.TaskForm;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.identity.User;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Named
@ConversationScoped
public class InvitationsController implements Serializable {

	private static  final long serialVersionUID = 3L;

	@Inject
	private BusinessProcess businessProcess;

	@PersistenceContext
	private EntityManager entityManager;


	@Inject
	private InterviewBusinessLogic interviewBusinessLogic;

	private InvitationEntity invitationEntity;

	
}
