package io.fafee.serverless.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FooBar {

    private Long id;
    private String name;
    private List<Bar> bars;

}
