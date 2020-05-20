package it.javaboss.jms;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

@Singleton
public class JBQWriter {

	@Resource(name = "java:/jms/queue/javaboss")
    private Queue jbq;
 
    @Resource(mappedName = "java:/JmsXA")
    private ConnectionFactory cf;
 
    private Connection connection;

	public void sendMessage(String txt) {
        try {         
            connection = cf.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
           
            MessageProducer publisher = session.createProducer(jbq);
 
            connection.start();
 
            TextMessage message = session.createTextMessage(txt);
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
