package com.solopov.rabbitmq.spring.boot;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TestController {

    private final RabbitMessagingTemplate rabbitMessagingTemplate;

   /* @GetMapping("/test/{name}")
    public String testAPI(@PathVariable String name){
        Person person = new Person(UUID.randomUUID(), name);
        rabbitMessagingTemplate.convertAndSend("Mobile", person);
        rabbitMessagingTemplate.convertAndSend("Direct-Exchange", "mobile", person);
        rabbitMessagingTemplate.convertAndSend("Fanout-Exchange", "", person);
        rabbitMessagingTemplate.convertAndSend("Topic-Exchange", "tv.mobile.ac", person);
        return "Success";
    }*/

    @GetMapping("/test/{name}")
    @SneakyThrows
    public String testAPI(@PathVariable String name) {
        Person person = new Person(UUID.randomUUID(), name);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput objectOutput = new ObjectOutputStream(bos);
        objectOutput.writeObject(person);
        objectOutput.flush();
        objectOutput.close();

        byte[] personBytes = bos.toByteArray();
        bos.close();

        Message message = MessageBuilder.withBody(personBytes)
                .setHeader("item1", "mobile")
                .setHeader("item2", "television").build();

        rabbitMessagingTemplate.convertAndSend("Headers-Exchange", "", message);

        return "Success";
    }

}
