package io.fafee.serverless.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Bar {

    private String identifier;
    private double x;
    private double y;
    private double z;

}
