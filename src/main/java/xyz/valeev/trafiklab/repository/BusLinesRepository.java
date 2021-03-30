package xyz.valeev.trafiklab.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.valeev.trafiklab.model.*;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class BusLinesRepository {
    private static final Logger LOGGER= LoggerFactory.getLogger(BusLinesRepository.class);

    private RepositoryResponse repositoryState =
            new RepositoryResponse(RepositoryCodes.SUCCESS.getCode(), "");

    private List<BusLine> allBusLines;
    private Map<Integer, List<JourneyPattern>> journeyPatternsMap;
    private List<StopPoint> busStops;

    private final String TRAFIKLAB_BASE_URL = "api.sl.se/api2";
    private final String JSON_PATH = "LineData.json";

    private URI trafiklabUri;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestTemplate commonRestTemplate;

    @Autowired
    String apiKey;

    // public because will be used in tests to genrate infalid URIs
    public URI trafiklabUriResolver(String key, String model) {
        return UriComponentsBuilder.newInstance()
                .scheme("https").host(TRAFIKLAB_BASE_URL)
                .path(JSON_PATH)
                .queryParam("key", key)
                .queryParam("model", Models.line)
                .build().toUri();
    }

    // public because will be used in tests to genrate infalid URIs
    public URI trafiklabUriResolver(String key, String model, String mode) {
        return UriComponentsBuilder
                .fromUri(trafiklabUriResolver(key, model))
                .queryParam("DefaultTransportModeCode", mode)
                .build().toUri();
    }

    private void processFetchedReaponse (RepositoryResponse response){
        if (response.getStatusCode() != RepositoryCodes.SUCCESS.getCode()){
            repositoryState.setStatusCode(RepositoryCodes.FAIL.getCode());
            repositoryState.setBody(repositoryState.getBody() + response.getBody());
        }
    }

    @PostConstruct
    private void serveAllBusLines() throws JsonProcessingException {

        // Data fetching
        RepositoryResponse allBusLinesResponse = fetchBusLines();
        RepositoryResponse journeyPatternsResponse = fetchJourneyPatterns();
        RepositoryResponse allStopsResponse = fetchStops();

        // Define repository state
        processFetchedReaponse(allBusLinesResponse);
        processFetchedReaponse(journeyPatternsResponse);
        processFetchedReaponse(allStopsResponse);

        // Data processing
        if (repositoryState.getStatusCode() == RepositoryCodes.SUCCESS.getCode()) {

            try{
                String allBusLinesStr = allBusLinesResponse.getBody();
                String journeyPatternsStr = journeyPatternsResponse.getBody();
                String allStopsStr = allStopsResponse.getBody();

                allBusLines = objectMapper.readValue(allBusLinesStr, new TypeReference<>() { });

                List<JourneyPattern> allPatterns = objectMapper.readValue(journeyPatternsStr, new TypeReference<>() { });
                journeyPatternsMap = allPatterns
                        .stream()
                        .collect(Collectors.groupingBy(JourneyPattern::getLineNumber));

                List<StopPoint> allStops = objectMapper.readValue(allStopsStr, new TypeReference<>() { });
                busStops = allStops
                        .stream()
                        .filter(stop -> stop.getStopAreaTypeCode().equals(Codes.BUS.getStopAreaTypeCode())).
                        collect(Collectors.toList());

                LOGGER.info("Application repository initiated");
            } catch (JsonProcessingException jpe) {
                repositoryState.setStatusCode(RepositoryCodes.FAIL.getCode());
                repositoryState.setBody("Unable to process Trafiklab data");
            }
            LOGGER.info("Repository successfully initiated and populated with fetched data!");
        } else {
            LOGGER.error("Repository was not initiated! Bean created to start application.");
        }
    }

    // Don't throw from here, repository should be initialized even for all the tea in China! :)
    private RepositoryResponse fetchTrafiklabData(URI uri) {
        String body = "";
        int code = RepositoryCodes.SUCCESS.getCode();

        ResponseEntity<String> response = commonRestTemplate.getForEntity(uri, String.class);
        if (response.getStatusCode().is2xxSuccessful()){
            String responseBody = response.getBody();
            try {
                JsonNode jsonNode = objectMapper.readTree(responseBody);
                if (!jsonNode.get("StatusCode").toString().equals("0")) {
                    code = RepositoryCodes.FAIL.getCode();
                    body = "Unable to fetch data from Trafiklab, " + jsonNode.get("Message");
                } else {
                    body = jsonNode.get("ResponseData").get("Result").toPrettyString();
                }
            } catch (NullPointerException npe){
                code = RepositoryCodes.FAIL.getCode();
                body = "Unable to process data from Trafiklab, invalid JSON schema";
            } catch (JsonProcessingException jpe){
                code = RepositoryCodes.FAIL.getCode();
                body = "Unable to process data from Trafiklab";
            }
        } else {
            code = RepositoryCodes.FAIL.getCode();
            body = "Unable to fetch data from Trafiklab, error: " + response.getStatusCode();
        }
        return new RepositoryResponse(code, body);
    }

    private RepositoryResponse fetchBusLines() {
        return fetchTrafiklabData(trafiklabUriResolver(apiKey, String.valueOf(Models.line), Codes.BUS.getDefaultTransportModeCode()));
    }

    private RepositoryResponse fetchJourneyPatterns() {
        return fetchTrafiklabData(trafiklabUriResolver(apiKey, String.valueOf(Models.jour), Codes.BUS.getDefaultTransportModeCode()));
    }

    private RepositoryResponse fetchStops() {
        return fetchTrafiklabData(trafiklabUriResolver(apiKey, String.valueOf(Models.jour)));
    }

    public RepositoryResponse getRepositoryState() {
        return repositoryState;
    }

    public List<BusLine> getAllBusLines() {
        return allBusLines;
    }

    public Map<Integer, List<JourneyPattern>> getJourneyPatternsMap() {
        return journeyPatternsMap;
    }

    public List<StopPoint> getBusStops(){
        return busStops;
    }

}
