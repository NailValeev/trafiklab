package xyz.valeev.trafiklab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.valeev.trafiklab.model.Line;
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
    public List<Line> getBusLinesV1() {
        return service.getLines();
    }

    @GetMapping("/v1/lines/{lineNumber}")
    public List<Line> getBusLine(@PathVariable("lineNumber") @Min(1) int lineNumber) {
        return service.getLines();
    }

    @GetMapping("/v1/lines/top/")
    public List<Line> getTopBusLinesV1() {
        return service.getLines();
    }

}
