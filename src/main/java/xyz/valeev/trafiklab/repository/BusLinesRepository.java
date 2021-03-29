package xyz.valeev.trafiklab.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.valeev.trafiklab.model.BusLine;
import xyz.valeev.trafiklab.model.Codes;
import xyz.valeev.trafiklab.model.Models;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.List;

@Repository
public class BusLinesRepository {

    ObjectMapper objectMapper = new ObjectMapper();

    private final RestTemplate restTemplate;
    private final URI trafiklabBaseUri;

    @Autowired
    public BusLinesRepository(RestTemplate restTemplate, URI trafiklabBaseUri) {
        this.restTemplate = restTemplate;
        this.trafiklabBaseUri = trafiklabBaseUri;
    }

    @PostConstruct
    private void serveAllBusLines() throws JsonProcessingException {
        String allBusLinesStr = fetchBusLines();
        System.out.print("allBusLinesStr" + allBusLinesStr);
        List<BusLine> allBusLines = objectMapper.readValue(allBusLinesStr, new TypeReference<>() { });

        System.out.println("allBusLines" + allBusLines.get(0).getLineNumber() + allBusLines.get(0).getExistsFromDate());
    }

    private String fetchBusLines() throws JsonProcessingException {
        URI lineUri = UriComponentsBuilder
                .fromUri(trafiklabBaseUri)
                .queryParam("model", Models.line)
                .queryParam("DefaultTransportModeCode", Codes.BUS.getDefaultTransportModeCode())
                .build()
                .toUri();

                String responseBody = restTemplate.getForEntity(lineUri, String.class).getBody();
                JsonNode jsonNode = objectMapper.readTree(responseBody);
                return  jsonNode.get("ResponseData").get("Result").toPrettyString();
    }

    public String returnBusLines() throws JsonProcessingException {
        return "fooboo";
    }

}
