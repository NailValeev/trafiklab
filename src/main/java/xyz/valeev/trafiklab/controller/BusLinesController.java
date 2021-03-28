package xyz.valeev.trafiklab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BusLinesController {

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
}