package it.javaboss.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/topic/javaboss"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "messageSelector",propertyValue = "type = 2")
    })
public class JBTListenerType2 implements MessageListener {

	public void onMessage(Message message) {
		try {
			System.out.println( "Listner del TOPIC di tipo 2 MDB: " + message.getBody( String.class ) );
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	

}
