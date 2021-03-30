package xyz.valeev.trafiklab.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@Configuration
@SpringBootConfiguration
@EnableWebMvc
public class TrafiklabApplicationConfig  {
    @Bean
    String apiKey(@Value("${api.key:not set}") String key){
        return key;
    }

    @Bean
    public RestTemplate commonRestTemplate(RestTemplateBuilder builder){
        // I don't want to customize, let it be default
        return builder.build();
    }

}

