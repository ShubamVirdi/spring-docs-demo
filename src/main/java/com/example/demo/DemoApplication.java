package com.example.demo;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
public class DemoApplication {

    private final Map<String, Object> headers = Map.of("Access-Control-Allow-Origin", "*"
            , "Content-Type", "application/json");


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("spring-cloud-function-webmvc OpenAPI Demo").version("1.0")
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")));
    }


    @Bean
    @RouterOperations(
            @RouterOperation(
                    path = "/authenticated/request",
                    method = RequestMethod.POST,
                    operation = @Operation(
                            operationId = "some_operation_id",
                            description = "to create",
                            tags = "API",
                            responses = @ApiResponse(responseCode = "200",
                                    content = @Content(schema = @Schema(implementation = CreateRequest.class))
                            )
                    ))
    )
    public Function<Message<Void>, Message<CreateRequest>> create(){
        return request -> new GenericMessage<>(new CreateRequest(true), headers);
    }

}
