package xyz.valeev.trafiklab.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.valeev.trafiklab.model.Codes;
import xyz.valeev.trafiklab.model.Models;

import java.net.URI;

@Repository
public class BusLinesRepository {

    ObjectMapper objectMapper = new ObjectMapper();

    private final RestTemplate restTemplate;
    private final URI baseUri;

    @Autowired
    public BusLinesRepository(RestTemplate restTemplate, URI baseUri) {
        this.restTemplate = restTemplate;
        this.baseUri = baseUri;
    }

    public String fetchBusLines() throws JsonProcessingException {
        URI lineUri = UriComponentsBuilder
                .fromUri(baseUri)
                .queryParam("model", Models.line)
                .queryParam("DefaultTransportModeCode", Codes.BUS.getDefaultTransportModeCode())
                .build()
                .toUri();

                String responseBody = restTemplate.getForEntity(lineUri, String.class).getBody();
                JsonNode jsonNode = objectMapper.readTree(responseBody);
                return  jsonNode.get("ResponseData").get("Result").toPrettyString();
    }

}
