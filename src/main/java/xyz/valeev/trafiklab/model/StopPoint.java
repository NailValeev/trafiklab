package xyz.valeev.trafiklab.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Objects;

import static org.apache.commons.lang3.Validate.notBlank;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StopPoint {
    @JsonProperty("StopPointNumber")
    private int stopPointNumber;
    @JsonProperty("StopPointName")
    private String stopPointName;
    @JsonProperty("StopAreaNumber")
    private int stopAreaNumber;
    @JsonProperty("LocationNorthingCoordinate")
    private double locationNorthingCoordinate;
    @JsonProperty("LocationEastingCoordinate")
    private double locationEastingCoordinate;
    @JsonProperty("ZoneShortName")
    private String zoneShortName;
    @JsonProperty("StopAreaTypeCode")
    private String stopAreaTypeCode;
    @JsonProperty("LastModifiedUtcDateTime")
    private String lastModifiedUtcDateTime;
    @JsonProperty("ExistsFromDate")
    private String existsFromDate;

    @JsonCreator
    public StopPoint(
            @JsonProperty("StopPointNumber") final int stopPointNumber,
            @JsonProperty("StopPointName") final String stopPointName,
            @JsonProperty("StopAreaNumber") final int stopAreaNumber,
            @JsonProperty("LocationNorthingCoordinate") final double locationNorthingCoordinate,
            @JsonProperty("LocationEastingCoordinate") final double locationEastingCoordinate,
            @JsonProperty("ZoneShortName") final String zoneShortName,
            @JsonProperty("StopAreaTypeCode") final String stopAreaTypeCode,
            @JsonProperty("LastModifiedUtcDateTime") final String lastModifiedUtcDateTime,
            @JsonProperty("ExistsFromDate") final String existsFromDate) {
        this.stopPointNumber = stopPointNumber;
        this.stopPointName = notBlank(stopPointName);
        this.stopAreaNumber = stopAreaNumber;
        this.locationNorthingCoordinate = locationNorthingCoordinate;
        this.locationEastingCoordinate = locationEastingCoordinate;
        this.zoneShortName = zoneShortName;
        this.stopAreaTypeCode = notBlank(stopAreaTypeCode);
        this.lastModifiedUtcDateTime = notBlank(lastModifiedUtcDateTime);
        this.existsFromDate = notBlank(existsFromDate);
    }
    // No equals and hashcode because added Lombok
}
