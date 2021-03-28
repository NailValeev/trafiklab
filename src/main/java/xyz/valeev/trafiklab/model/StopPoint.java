package xyz.valeev.trafiklab.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StopPoint {
    private int stopPointNumber;
    private String stopPointName;
    private int stopAreaNumber;
    private String locationNorthingCoordinate;
    private String locationEastingCoordinate;
    private String zoneShortName;
    private String stopAreaTypeCode;
    private String lastModifiedUtcDateTime;
    private String existsFromDate;

    @JsonCreator
    public StopPoint(
            @JsonProperty("StopPointNumber") final int stopPointNumber,
            @JsonProperty("StopPointName") final String stopPointName,
            @JsonProperty("StopAreaNumber") final int stopAreaNumber,
            @JsonProperty("LocationNorthingCoordinate") final String locationNorthingCoordinate,
            @JsonProperty("LocationEastingCoordinate") final String locationEastingCoordinate,
            @JsonProperty("ZoneShortName") final String zoneShortName,
            @JsonProperty("StopAreaTypeCode") final String stopAreaTypeCode,
            @JsonProperty("LastModifiedUtcDateTime") final String lastModifiedUtcDateTime,
            @JsonProperty("ExistsFromDate") final String existsFromDate) {
        this.stopPointNumber = notNull(stopPointNumber);
        this.stopPointName = notBlank(stopPointName);
        this.stopAreaNumber = notNull(stopAreaNumber);
        this.locationNorthingCoordinate = notBlank(locationNorthingCoordinate);
        this.locationEastingCoordinate = notBlank(locationEastingCoordinate);
        this.zoneShortName = notBlank(zoneShortName);
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
