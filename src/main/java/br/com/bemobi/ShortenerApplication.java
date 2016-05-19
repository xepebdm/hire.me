package br.com.bemobi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@EnableCaching
@SpringBootApplication
public class ShortenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShortenerApplication.class, args);
	}

	@Bean
	public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder(){
		Jackson2ObjectMapperBuilder b = new Jackson2ObjectMapperBuilder();
		return b
				.featuresToEnable(
						DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,
						DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
				.serializationInclusion(JsonInclude.Include.NON_EMPTY)
				.failOnUnknownProperties(false);
	}


}
