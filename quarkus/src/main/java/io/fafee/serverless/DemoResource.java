package io.fafee.serverless;

import io.fafee.serverless.dto.Calculation;
import io.fafee.serverless.dto.Foo;
import io.fafee.serverless.dto.FooBar;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.net.URI;
import java.util.List;

@Path("/things")
@RequiredArgsConstructor
@Slf4j
public class DemoResource {

    private final DemoService demoService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String create(Foo foo) {
        return URI.create("/things/".concat(String.valueOf(demoService.create(foo)))).toString();
    }

    @POST
    @Path("/calculate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Calculation expensive(@QueryParam("id") Long id) {
        return demoService.calculate(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void update(@PathParam("id") Long id, Foo foo) {
        demoService.update(id, foo);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Foo> list() {
        return demoService.list();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public FooBar get(@PathParam("id") Long id) {
        return demoService.get(id);
    }
}
