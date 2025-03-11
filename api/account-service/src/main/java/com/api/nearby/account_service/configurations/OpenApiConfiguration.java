package com.api.nearby.account_service.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {
        var server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development");

        var myContact = new Contact();
        myContact.setName("Rodrigo Estevão");
        myContact.setEmail("rgestevao@gmail.com");

        var information = new Info()
                .title("Nearby API")
                .version("1.0")
                .description("This API exposes endpoints to manage employees.")
                .contact(myContact);
        return new OpenAPI().info(information).servers(List.of(server));
    }

}
