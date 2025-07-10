package org.jhegner.k8slabs;

import java.net.InetAddress;
import java.util.Map;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/hello")
public class AppController {

    @Value("${app.team.color}")
    protected String teamColor;

    @Get(produces = MediaType.APPLICATION_JSON)
    public Map<String, String> index() {
        try {
            return Map.of(
                    "message", "Hello from Micronaut!",
                    "hostname", InetAddress.getLocalHost().getHostName(),
                    "ipAddress", InetAddress.getLocalHost().getHostAddress(),
                    "teamColor", teamColor);
        } catch (Exception e) {
            return Map.of(
                    "message", "Hello from Micronaut!",
                    "teamColor", teamColor,
                    "error", e.getMessage());
        }
    }

}
