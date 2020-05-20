package it.javaboss.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/topic/javaboss"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "messageSelector",propertyValue = "type = 3")
    })
public class JBTListenerType3 implements MessageListener {

	public void onMessage(Message message) {
		try {
			System.out.println( "Listner del TOPIC di tipo 3 MDB: " + message.getBody( String.class ) );
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	

}