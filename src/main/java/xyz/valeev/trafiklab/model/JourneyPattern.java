package xyz.valeev.trafiklab.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Objects;
import static org.apache.commons.lang3.Validate.notBlank;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class JourneyPattern {
    @JsonProperty("LineNumber")
    private int lineNumber;
    @JsonProperty("DirectionCode")
    private String directionCode;
    @JsonProperty("JourneyPatternPointNumber")
    private int journeyPatternPointNumber;
    @JsonProperty("LastModifiedUtcDateTime")
    private String lastModifiedUtcDateTime;
    @JsonProperty("ExistsFromDate")
    private String existsFromDate;

    @JsonCreator
    public JourneyPattern(
            @JsonProperty("LineNumber") final int lineNumber,
            @JsonProperty("DirectionCode") final String directionCode,
            @JsonProperty("JourneyPatternPointNumber") final int journeyPatternPointNumber,
            @JsonProperty("LastModifiedUtcDateTime")final String lastModifiedUtcDateTime,
            @JsonProperty("ExistsFromDate")final String existsFromDate) {
        this.lineNumber = lineNumber;
        this.directionCode = notBlank(directionCode);
        this.journeyPatternPointNumber = journeyPatternPointNumber;
        this.lastModifiedUtcDateTime = notBlank(lastModifiedUtcDateTime);
        this.existsFromDate = notBlank(existsFromDate);
    }
    // No equals and hashcode because added Lombok
}
