package it.javaboss.jms;

import javax.ejb.ActivationConfigProperty;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.ejb.MessageDriven;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/topic/javaboss"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "messageSelector",propertyValue = "type = 1")
    })
public class JBTListenerType1 implements MessageListener {
    public void onMessage(Message message) {
        try {
            System.out.println( "Listner del TOPIC di tipo 1 MDB: " + message.getBody( String.class ) );
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
