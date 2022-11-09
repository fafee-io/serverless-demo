package io.fafee.serverless;

import io.fafee.serverless.dto.FooBar;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/things")
@RequiredArgsConstructor
@Slf4j
public class DemoResource {

    private final DemoService demoService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public FooBar get(@PathParam("id") Long id) {
        return demoService.get(id);
    }
}
