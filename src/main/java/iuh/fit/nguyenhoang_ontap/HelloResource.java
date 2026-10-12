package iuh.fit.nguyenhoang_ontap;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/hello-world")
public class HelloResource {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

    @GET
    @Path("/ping")
    @Produces("text/plain")
    public String ping() {
        return "pong";
    }
}