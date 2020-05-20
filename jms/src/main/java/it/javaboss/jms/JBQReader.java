package it.javaboss.jms;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;

@Singleton
public class JBQReader {

	@Resource(mappedName = "java:/jms/queue/javaboss")
    private Queue jbq;
 
    @Resource(mappedName = "java:/JmsXA")
    private ConnectionFactory cf;
 
    private Connection connection;
    
    public String readMessage() {
    	try {
			connection = cf.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
      
			MessageConsumer consumer = session.createConsumer(jbq);

			connection.start();
			
			Message msg = consumer.receive(5000);  
			if ( msg != null ) {
				return msg.getBody(String.class);
			}
			return null;
    	}
    	catch (Exception e) {
            e.printStackTrace();
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
		return null;
    }
}
