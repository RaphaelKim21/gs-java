package org.example;

import org.example.config.CorsFilter;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class App extends ResourceConfig {

    public App(){
        packages("org.example");
        register(CorsFilter.class);
    }
}
