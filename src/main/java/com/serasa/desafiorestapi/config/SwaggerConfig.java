package com.serasa.desafiorestapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.serasa.desafiorestapi.controller"))
				.paths(PathSelectors.any())
				.build().apiInfo(apiInfo()); 
	}
	
	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfoBuilder()
				.title("Desafio Técnico - Serasa Experian")
				.description("Rest API desenvolvido para o desafio técnico")
				.license("Apache 2.0")
				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
				.version("2.0 Professional")
				.contact(new Contact("Giulia de Oliveira", "https://github.com/liagiu", "giu.olivers@gmail.com"))
				.build();
		return apiInfo;
	}
}
