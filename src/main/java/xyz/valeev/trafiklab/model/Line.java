package xyz.valeev.trafiklab.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Line {
    private int lineNumber;
    private String lineDesignation;
    private String defaultTransportMode;
    private String lastModifiedUtcDateTime;
    private String defaultTransportModeCode;
    private String existsFromDate;

    @JsonCreator
    public Line(
            @JsonProperty("LineNumber") final int lineNumber,
            @JsonProperty("LineDesignation")final String lineDesignation,
            @JsonProperty("DefaultTransportMode") final String defaultTransportMode,
            @JsonProperty("DefaultTransportModeCode") final String defaultTransportModeCode,
            @JsonProperty("LastModifiedUtcDateTime")final String lastModifiedUtcDateTime,
            @JsonProperty("ExistsFromDate")final String existsFromDate) {
        this.lineNumber = notNull(lineNumber);
        this.lineDesignation = notBlank(lineDesignation);
        this.defaultTransportMode = notBlank(defaultTransportMode);
        this.defaultTransportModeCode = notBlank(defaultTransportModeCode);
        this.lastModifiedUtcDateTime = notBlank(lastModifiedUtcDateTime);
        this.existsFromDate = notBlank(existsFromDate);
    }
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line hotel = (Line) o;
        return Objects.equals(lineNumber, hotel.lineNumber) &&
                Objects.equals(lineDesignation, hotel.lineDesignation) &&
                Objects.equals(defaultTransportMode, hotel.defaultTransportMode) &&
                Objects.equals(defaultTransportModeCode, hotel.defaultTransportModeCode) &&
                Objects.equals(lastModifiedUtcDateTime, hotel.lastModifiedUtcDateTime) &&
                Objects.equals(existsFromDate, hotel.existsFromDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lineNumber, lineDesignation, defaultTransportMode, defaultTransportModeCode, lastModifiedUtcDateTime, existsFromDate);
    }
}
