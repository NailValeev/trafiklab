package xyz.valeev.trafiklab.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import static org.apache.commons.lang3.Validate.notBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
//Omitted DefaultTransportModeCode because we filtering bus lines by it
public class BusLine {
    @JsonProperty("LineNumber")
    private int lineNumber;
    @JsonProperty("LineDesignation")
    private String lineDesignation;
    @JsonProperty("DefaultTransportMode")
    private String defaultTransportMode;
    @JsonProperty("LastModifiedUtcDateTime")
    private String lastModifiedUtcDateTime;
    @JsonProperty("ExistsFromDate")
    private String existsFromDate;

    @JsonCreator
    public BusLine(
            @JsonProperty("LineNumber") final int lineNumber,
            @JsonProperty("LineDesignation") final String lineDesignation,
            @JsonProperty("DefaultTransportMode") final String defaultTransportMode,
            @JsonProperty("LastModifiedUtcDateTime")final String lastModifiedUtcDateTime,
            @JsonProperty("ExistsFromDate")final String existsFromDate) {
        this.lineNumber = lineNumber;
        this.lineDesignation = notBlank(lineDesignation);
        this.defaultTransportMode = defaultTransportMode;
        this.lastModifiedUtcDateTime = notBlank(lastModifiedUtcDateTime);
        this.existsFromDate = notBlank(existsFromDate);
    }
    // No equals and hashcode because added Lombok
}
