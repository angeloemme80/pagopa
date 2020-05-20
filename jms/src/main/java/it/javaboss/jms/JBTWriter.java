package it.javaboss.jms;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

@Singleton
public class JBTWriter {
    @Resource(name = "java:/jms/topic/javaboss")
    private Topic jbt;
 
    @Resource(mappedName = "java:/JmsXA")
    private ConnectionFactory cf;
 
    private Connection connection;

	public void sendMessage(String txt, Integer type) {
        try {         
            connection = cf.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer publisher = session.createProducer(jbt);
            connection.start();
 
            TextMessage message = session.createTextMessage(txt);
            message.setIntProperty( "type", type );
            publisher.send(message);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        finally {         
            if (connection != null)   {
                try {
                    connection.close();
                } catch (JMSException e) {                    
                    e.printStackTrace();
                }
            }
        }
    } 
}
