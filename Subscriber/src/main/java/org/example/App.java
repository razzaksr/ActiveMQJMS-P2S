package org.example;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.Console;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws JMSException {
        System.out.println( "Hello World!" );
        ActiveMQConnectionFactory factory=new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        Connection connection= factory.createConnection();
        connection.start();

        Session session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        Destination destination=session.createTopic("chatgpt");

        MessageConsumer consumer=session.createConsumer(destination);

        String info;TextMessage message;
        Console console=System.console();
        do{
            message= (TextMessage) consumer.receive();
            info=((TextMessage)message).getText();
            System.out.println(info);
        }while(!info.equals("bye"));
        connection.close();
    }
}
