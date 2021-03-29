package xyz.valeev.trafiklab.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@Configuration
@EnableWebMvc
public class TrafiklabApplicationConfig  {


    @Bean
    public URI trafiklabBaseUri(@Value("${api.key:not set}") String key) {
        var JSON_PATH = "LineData.json";
        var TRAFIKLAB_BASE_URL = "api.sl.se/api2";
        return UriComponentsBuilder.newInstance()
                .scheme("https").host(TRAFIKLAB_BASE_URL)
                .path(JSON_PATH).queryParam("key", key).build().toUri();
    }
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

}

