package com.solopov.rabbitmq.exchange.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DirectPublisher {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

//        String message = "This is mobile";
//        String message = "This is AC";
        String message = "This is TV";

//        channel.basicPublish("Direct-Exchange", "mobile", null, message.getBytes());
//        channel.basicPublish("Direct-Exchange", "ac", null, message.getBytes());
        channel.basicPublish("Direct-Exchange", "tv", null, message.getBytes());
        channel.close();
        connection.close();
    }
}
