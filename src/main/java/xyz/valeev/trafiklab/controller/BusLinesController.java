package xyz.valeev.trafiklab.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.valeev.trafiklab.model.BusLine;
import xyz.valeev.trafiklab.model.StopPoint;
import xyz.valeev.trafiklab.service.BusLinesService;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/bus/")
public class BusLinesController {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("Bad request, validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private final BusLinesService service;

    @Autowired
    public BusLinesController(BusLinesService service) {
        this.service = service;
    }

    @Value("${pom.version: not set}")
    private String pomVersion;

    public class HealthCheckInformation {
        private String version;
        private HealthCheckInformation (final String version){
            this.version = version;
        }
        public String getVersion(){
            return version;
        }
    }

    @GetMapping("/health")
    public HealthCheckInformation getHealthCheckMsg() {
        return new HealthCheckInformation(pomVersion);
    }

    @GetMapping("/v1/lines")
    public String getBusLinesV1() throws JsonProcessingException {
        return service.getBusLinesV1();
    }

    @GetMapping("/v1/lines/{lineNumber}")
    public List<StopPoint> getBusLineStopsV1(@PathVariable("lineNumber") @Min(1) int lineNumber) {
        return service.getBusLineStopsV1();
    }

    @GetMapping("/v1/lines/top/")
    public List<BusLine> getTopBusLinesV1() {
        return service.getTopBusLinesV1();
    }

}
