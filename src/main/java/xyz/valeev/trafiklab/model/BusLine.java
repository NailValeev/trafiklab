package xyz.valeev.trafiklab.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Objects;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

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
        this.lineNumber = notNull(lineNumber);
        this.lineDesignation = notBlank(lineDesignation);
        this.defaultTransportMode = defaultTransportMode;
        this.lastModifiedUtcDateTime = notBlank(lastModifiedUtcDateTime);
        this.existsFromDate = notBlank(existsFromDate);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusLine busLine = (BusLine) o;
        return Objects.equals(lineNumber, busLine.lineNumber) &&
                Objects.equals(lineDesignation, busLine.lineDesignation) &&
                Objects.equals(defaultTransportMode, busLine.defaultTransportMode) &&
                Objects.equals(lastModifiedUtcDateTime, busLine.lastModifiedUtcDateTime) &&
                Objects.equals(existsFromDate, busLine.existsFromDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lineNumber, lineDesignation, defaultTransportMode, lastModifiedUtcDateTime, existsFromDate);
    }
}
