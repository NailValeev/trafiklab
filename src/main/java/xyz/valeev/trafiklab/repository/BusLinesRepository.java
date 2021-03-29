package xyz.valeev.trafiklab.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.valeev.trafiklab.model.*;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
        System.out.println("allBusLinesStr" + allBusLinesStr);
        List<BusLine> allBusLines = objectMapper.readValue(allBusLinesStr, new TypeReference<>() { });

        System.out.println("***************************");
        String journeyPatterns = fetchJourneyPatterns();
        List<JourneyPattern> allPatterns = objectMapper.readValue(journeyPatterns, new TypeReference<>() { });
        System.out.println("journeyPatterns" + journeyPatterns);

        System.out.println("***************************");
        String allStopsStr = fetchStops();
        System.out.println("allStopsStr" + allStopsStr);
        List<StopPoint> allStops = objectMapper.readValue(allStopsStr, new TypeReference<>() { });
        System.out.println("allStops size: " + allStops.size());
        System.out.println("Filtering stops...");
        List<StopPoint> busStops = allStops
                .stream()
                .filter(stop -> stop.getStopAreaTypeCode().equals(Codes.BUS.getStopAreaTypeCode())).
                collect(Collectors.toList());
        System.out.println("busStops size:" + busStops.size());

        //Generation of the data sets should be done on start, too
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

    private String fetchJourneyPatterns() throws JsonProcessingException {
        URI lineUri = UriComponentsBuilder
                .fromUri(trafiklabBaseUri)
                .queryParam("model", Models.jour)
                .queryParam("DefaultTransportModeCode", Codes.BUS.getDefaultTransportModeCode())
                .build()
                .toUri();

                String responseBody = restTemplate.getForEntity(lineUri, String.class).getBody();
                JsonNode jsonNode = objectMapper.readTree(responseBody);
                return  jsonNode.get("ResponseData").get("Result").toPrettyString();
    }

    private String fetchStops() throws JsonProcessingException {
        URI lineUri = UriComponentsBuilder
                .fromUri(trafiklabBaseUri)
                .queryParam("model", Models.stop)
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
