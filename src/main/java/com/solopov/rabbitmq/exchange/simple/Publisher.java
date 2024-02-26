package com.solopov.rabbitmq.exchange.simple;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Publisher {

    public static void main(String[] args) throws IOException, TimeoutException {
        publishMessage();
    }

    private static void publishMessage() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        try (Connection connection = factory.newConnection()) {
            try (Channel channel = connection.createChannel()) {

                String exchange = "";
                AMQP.BasicProperties props = null;
                String[] messages = {"First message from RabbitMq", "Second message", "Third message", "Fourth message"};
                String routingKey = "Queue-1";

                for (String message : messages) {
                    channel.basicPublish(exchange, routingKey, props, message.getBytes());
                }
            }// channel.close()
        }//connection.close()
    }
}
