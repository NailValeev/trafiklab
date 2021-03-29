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
        this.stopPointNumber = notNull(stopPointNumber);
        this.stopPointName = notBlank(stopPointName);
        this.stopAreaNumber = notNull(stopAreaNumber);
        this.locationNorthingCoordinate = notNull(locationNorthingCoordinate);
        this.locationEastingCoordinate = notNull(locationEastingCoordinate);
        this.zoneShortName = zoneShortName;
        this.stopAreaTypeCode = notBlank(stopAreaTypeCode);
        this.lastModifiedUtcDateTime = notBlank(lastModifiedUtcDateTime);
        this.existsFromDate = notBlank(existsFromDate);
    }
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StopPoint stopPoint = (StopPoint) o;
        return Objects.equals(stopPointNumber, stopPoint.stopPointNumber) &&
                Objects.equals(stopPointName, stopPoint.stopPointName) &&
                Objects.equals(stopAreaNumber, stopPoint.stopAreaNumber) &&
                Objects.equals(locationNorthingCoordinate, stopPoint.locationNorthingCoordinate) &&
                Objects.equals(locationEastingCoordinate, stopPoint.locationEastingCoordinate) &&
                Objects.equals(zoneShortName, stopPoint.zoneShortName) &&
                Objects.equals(stopAreaTypeCode, stopPoint.stopAreaTypeCode) &&
                Objects.equals(lastModifiedUtcDateTime, stopPoint.lastModifiedUtcDateTime) &&
                Objects.equals(existsFromDate, stopPoint.existsFromDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                stopPointNumber,
                stopPointName,
                stopAreaNumber,
                locationNorthingCoordinate,
                locationEastingCoordinate,
                zoneShortName,
                stopAreaTypeCode,
                lastModifiedUtcDateTime,
                existsFromDate);
    }
}
