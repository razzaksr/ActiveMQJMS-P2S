package org.example;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Scanner;

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

        MessageProducer publisher=session.createProducer(destination);

        Scanner scanner=new Scanner(System.in);
        String info;
        do{
            System.out.println("Send to the subscribers ");
            info= scanner.nextLine();
            TextMessage textMessage= session.createTextMessage(info);
            publisher.send(textMessage);
        }while (!info.equals("bye"));
        scanner.close();
        connection.close();
    }
}
