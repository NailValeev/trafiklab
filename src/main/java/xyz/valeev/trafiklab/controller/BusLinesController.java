package xyz.valeev.trafiklab.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.valeev.trafiklab.model.BusLine;
import xyz.valeev.trafiklab.model.RepositoryCodes;
import xyz.valeev.trafiklab.model.RepositoryResponse;
import xyz.valeev.trafiklab.model.StopPoint;
import xyz.valeev.trafiklab.service.BusLinesService;
import xyz.valeev.trafiklab.service.TrafiklabApiException;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/bus/")
public class BusLinesController {

    private static final Logger LOGGER= LoggerFactory.getLogger(BusLinesController.class);

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("Bad request, validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>("Bad request, validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(TrafiklabApiException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    ResponseEntity<String> handleTrafiklabApiException(TrafiklabApiException e) {
        return new ResponseEntity<>("External API error: " + e.getMessage(), HttpStatus.BAD_GATEWAY);
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseEntity<String> handleAnyException(Exception e) {
        return new ResponseEntity<>("Internal server error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private final BusLinesService service;

    @Autowired
    public BusLinesController(BusLinesService service) {
        this.service = service;
    }

    @Value("${pom.version: not set}")
    private String pomVersion;

    private class HealthCheckInformation {
        private String version;
        private HealthCheckInformation (final String version){
            this.version = version;
        }
        public String getVersion(){
            return version;
        }
    }

    private void checkRepositoryState () throws TrafiklabApiException {
        RepositoryResponse response = service.getRepositoryState();
        if (response.getStatusCode() != RepositoryCodes.SUCCESS.getCode()){
            LOGGER.error("Repository error " + response.getBody());
            throw new TrafiklabApiException(response.getBody());
        }
    }

    // spring-boot-starter-actuator can set up health checks, but it is my common practice to create
    // such endpoints (useful for AWS ECS Task definitions, smoke testing in the pipeline etc)
    @GetMapping("/health")
    public HealthCheckInformation getHealthCheckMsg() throws TrafiklabApiException {
        checkRepositoryState();
        return new HealthCheckInformation(pomVersion);
    }

    @GetMapping("/v1/lines")
    public List<BusLine> getBusLinesV1() throws TrafiklabApiException, JsonProcessingException {
        checkRepositoryState();
        return service.getBusLinesV1();
    }

    @GetMapping("/v1/lines/{lineNumber}")
    public List<StopPoint> getBusLineStopsV1(@PathVariable("lineNumber") @Min(1) int lineNumber) throws TrafiklabApiException {
        checkRepositoryState();
        return service.getBusLineStopsV1(lineNumber);
    }

    @GetMapping("/v1/lines/top/")
    public List<BusLine> getTopBusLinesV1() throws TrafiklabApiException {
        checkRepositoryState();
        return service.getTopBusLinesV1();
    }

}
