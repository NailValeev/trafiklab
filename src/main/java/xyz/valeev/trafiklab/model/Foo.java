package xyz.valeev.trafiklab.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Foo {
    @JsonProperty("LineNumber")
    public String lineNumber;
}
