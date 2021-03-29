package xyz.valeev.trafiklab.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Objects;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

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
        this.lineNumber = notNull(lineNumber);
        this.directionCode = notBlank(directionCode);
        this.journeyPatternPointNumber = notNull(journeyPatternPointNumber);
        this.lastModifiedUtcDateTime = notBlank(lastModifiedUtcDateTime);
        this.existsFromDate = notBlank(existsFromDate);
    }
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JourneyPattern pattern = (JourneyPattern) o;
        return Objects.equals(lineNumber, pattern.lineNumber) &&
                Objects.equals(directionCode, pattern.directionCode) &&
                Objects.equals(journeyPatternPointNumber, pattern.journeyPatternPointNumber) &&
                Objects.equals(lastModifiedUtcDateTime, pattern.lastModifiedUtcDateTime) &&
                Objects.equals(existsFromDate, pattern.existsFromDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lineNumber, directionCode, journeyPatternPointNumber, lastModifiedUtcDateTime, existsFromDate);
    }
}
