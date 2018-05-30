package login;

import com.sun.corba.se.pept.transport.Connection;
import com.tibco.tibjms.TibjmsConnectionFactory;
import com.tibco.tibjms.TibjmsTopicConnectionFactory;

import java.util.Queue;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.QueueConnection;
import javax.jms.QueueReceiver;
import javax.jms.Session;
import javax.jms.TextMessage;

public class JMS {
	
	 static String serverUrl = "tcp://localhost:7222"; // values changed
	    static String userName = "admin";
	    static String password = "";

	     static QueueConnection connection;
	     static QueueReceiver queueReceiver;
	     static Queue queue;
	     
	    static TextMessage message;

	    public static void sendQueueMessage(String topicName, String messageStr) {

	    	javax.jms.Connection connection = null;
	        Session session = null;
	        MessageProducer msgProducer = null;
	        Destination destination = null;


	        try {
	            TextMessage msg;

	            System.out.println("Publishing to destination '" + topicName
	                    + "'\n");

	            ConnectionFactory factory = new com.tibco.tibjms.TibjmsConnectionFactory(
	                    serverUrl);

	            connection =  factory.createConnection(userName, password);


	            session = connection.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);



	            destination = session.createQueue(topicName);


	            msgProducer = session.createProducer(null);



	            msg = session.createTextMessage();

	            msg.setStringProperty("SourceId", userName);
	            msg.setStringProperty("BusinessEvent", password);


	            msg.setText(messageStr);

	            
	            msgProducer.send(destination, msg);



	            System.out.println("Published message: " + messageStr);


	            connection.close();

	        } catch (JMSException e) {
	            e.printStackTrace();
	        }
	    }

	    
	    
	
}
