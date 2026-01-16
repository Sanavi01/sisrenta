package com.jirehcompanyit.sisrenta.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "SISRENTA APÍ",
                version = "1.0",
                description = "Sistema de gestion de alquileres para tiendas de ropa, disfraces y vestuario"
        )
)
public class OpenApiConfig {
}
