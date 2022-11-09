package io.fafee.serverless.dto;

import io.fafee.serverless.entity.Enumeration;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class Foo {

    private Long id;
    private String name;
    private Enumeration enumeration;

    private ZonedDateTime created;

}
