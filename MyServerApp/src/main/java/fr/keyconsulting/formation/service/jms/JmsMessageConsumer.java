package fr.keyconsulting.formation.service.jms;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import fr.keyconsulting.formation.model.User;

@Service
public class JmsMessageConsumer {

	private static final int TIMEOUT_MESSAGE_RECEPTION = 50;
	
	@Autowired
	@Qualifier("jmsTemplate")
	private JmsTemplate jmsTemplate;
	
	@PostConstruct
	private void init(){
		jmsTemplate.setReceiveTimeout(TIMEOUT_MESSAGE_RECEPTION);
	}
	
	public User getFollowingUser() {
		User message = (User) jmsTemplate.receiveAndConvert();
		return message;
	}

}