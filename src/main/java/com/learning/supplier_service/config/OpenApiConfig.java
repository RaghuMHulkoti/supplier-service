package com.learning.supplier_service.config;

import com.fasterxml.jackson.databind.json.JsonMapper;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI/Swagger Configuration for Supplier Service.
 * Provides comprehensive API documentation and enables Swagger UI.
 */
@Configuration
public class OpenApiConfig {

    /**
     * Provides a JsonMapper bean for JSON serialization/deserialization.
     *
     * @return configured JsonMapper instance
     */
    @Bean
    public JsonMapper jsonMapper() {
        return new JsonMapper();
    }

    /**
     * Configures the OpenAPI schema for the Supplier Service.
     *
     * @return configured OpenAPI instance
     */
    @Bean
    public OpenAPI supplierServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Supplier Service API")
                        .description("Comprehensive API for managing suppliers, their products, offers, and warehouses. " +
                                "This service provides functionality for supplier onboarding, product management, " +
                                "offer management, and warehouse operations.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Supplier Service Team")
                                .email("support@suppliersservice.com")
                                .url("https://www.supplyservice.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .addServersItem(new Server()
                        .url("http://localhost:8080")
                        .description("Local Development Server"))
                .addServersItem(new Server()
                        .url("https://api.supplier-service.com")
                        .description("Production Server"));
    }
}

