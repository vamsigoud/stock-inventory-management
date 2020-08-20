package com.altimetrik.playground.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket restApi() {
		
		return new Docket(DocumentationType.SWAGGER_2).groupName("stock-inventory-management-api").select().apis(RequestHandlerSelectors
                .basePackage("com.altimetrik.playground")).paths(PathSelectors.regex("/.*"))
				.build().apiInfo(apiEndPointsInfo());
		
	}
	
	
	private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Stock Inventory Management")
            .description("Stock Inventory Management Services")
//            .license("")
//            .licenseUrl("")
            .version("1.0")
            .build();
    }

}
