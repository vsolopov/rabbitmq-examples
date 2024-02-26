package com.solopov.rabbitmq.exchange.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class FanoutConsumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String callback = new String(delivery.getBody());
            System.out.println("Message received. Consumer tag:" + consumerTag + " callback:" + callback);
        };
        channel.basicConsume("Mobile", true, deliverCallback, consumerTag -> {
        });
        channel.basicConsume("AC", true, deliverCallback, consumerTag -> {
        });
    }
}
