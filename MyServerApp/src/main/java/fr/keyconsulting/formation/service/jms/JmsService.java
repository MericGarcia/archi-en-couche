package fr.keyconsulting.formation.service.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import fr.keyconsulting.formation.model.User;


@Service
public class JmsService {
	
	@Autowired
	JmsMessageSender jmsMessageSender;
	
	@Autowired
	JmsMessageConsumer jmsMessageConsumer;
	
	public void send(User user){
		jmsMessageSender.send(user);
	}

	public User nextUser(){
		return jmsMessageConsumer.getFollowingUser();
	}
}