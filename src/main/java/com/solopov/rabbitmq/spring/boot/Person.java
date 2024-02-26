package com.solopov.rabbitmq.spring.boot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Person implements Serializable {
    private UUID id;
    private String name;
}
