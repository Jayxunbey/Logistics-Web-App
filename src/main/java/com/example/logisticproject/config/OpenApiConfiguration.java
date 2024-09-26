package com.example.logisticproject.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8081/");
        server.setDescription("Logistics Web App");

        Info info = new Info();
        info.setTitle("Logistics Web App");
        info.setVersion("1.0.0");

        return new OpenAPI().info(info).servers(List.of(server));
    }
}
