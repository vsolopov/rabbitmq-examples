package com.solopov.rabbitmq.exchange.headers;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class HeadersPublisher {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String message = "Message for Mobile and TV";

        Map<String, Object> headers = new HashMap<>();
        headers.put("item1", "mobile");
        headers.put("item2", "television");

        AMQP.BasicProperties br = new AMQP.BasicProperties();
        br.builder().headers(headers).build();

        channel.basicPublish("Headers-Exchange", "", br, message.getBytes());

        channel.close();
        connection.close();
    }
}
