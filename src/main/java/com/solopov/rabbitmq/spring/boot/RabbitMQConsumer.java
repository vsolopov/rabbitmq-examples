package com.solopov.rabbitmq.spring.boot;

import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;

@Service
public class RabbitMQConsumer {

    /*@RabbitListener(queues = "Mobile")
    public void getMessage(*//*byte[] message*//* Person person) {
        System.out.println(person.getName());
    }*/

    @RabbitListener(queues = "Mobile")
    @SneakyThrows
    public void getMessage(byte[] message) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(message);
        ObjectInput input = new ObjectInputStream(byteArrayInputStream);

        if(input.readObject() instanceof Person person){
            System.out.println(person.getName());
        }

        input.close();
        byteArrayInputStream.close();
    }
}
