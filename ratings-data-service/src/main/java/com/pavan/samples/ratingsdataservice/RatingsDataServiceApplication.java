package com.pavan.samples.ratingsdataservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
@EnableEurekaClient
public class RatingsDataServiceApplication {

    @Value("${local.base.package}")
    private String basePackage;

    @Bean
    public Docket ratingsApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage(basePackage)).build();
    }

    public static void main(String[] args) {

        SpringApplication.run(RatingsDataServiceApplication.class, args);
    }

}
