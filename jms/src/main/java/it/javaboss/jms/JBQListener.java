package it.javaboss.jms;

import javax.ejb.ActivationConfigProperty;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.ejb.MessageDriven;

@MessageDriven(activationConfig = {
	    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/javaboss"),
	    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class JBQListener implements MessageListener {
    public void onMessage(Message message) {
        try {
            System.out.println( "Dentro JBQListener metodo onMessage: " + message.getBody( String.class ) );
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}