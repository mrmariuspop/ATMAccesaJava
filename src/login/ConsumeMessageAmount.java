package login;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import login.ConsumeMessageAmount.HelloMessageListener;

public class ConsumeMessageAmount {
	
	 static String serverUrl = "tcp://localhost:7222"; // values changed
	 static String mesaj = new String();
	 static List<String> messageList = new ArrayList<String>();
	 
	 public ConsumeMessageAmount() throws JMSException {
	    	ConnectionFactory factory = new com.tibco.tibjms.TibjmsConnectionFactory(
	                serverUrl);

	        Connection connection = factory.createConnection();
	        connection.start();
	        
	        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//	        MessageConsumer consumer = session.createConsumer(session.createQueue("Testqueue"));
	        MessageConsumer consumer = session.createConsumer(session.createQueue("amountresponse"));
	        consumer.setMessageListener(new HelloMessageListener());
	        
	        
	    }
    
    public static class HelloMessageListener implements MessageListener {

        
        public void onMessage(Message message) {
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println("Consumer " + Thread.currentThread().getName() + " received message: " + textMessage.getText());
                messageList.add(textMessage.getText());
                FileWriter fileWriter = new FileWriter("data.txt");
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.print(textMessage.getText());
                printWriter.close();
            } catch (JMSException e) {
                e.printStackTrace();
            } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
}
}