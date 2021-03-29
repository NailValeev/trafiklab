package xyz.valeev.trafiklab.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import xyz.valeev.trafiklab.model.Line;
import xyz.valeev.trafiklab.model.StopPoint;

import java.util.List;

public interface IBusLinesService {
    public String getBusLinesV1() throws JsonProcessingException;
    public List<StopPoint> getBusLineStopsV1();
    public List<Line> getTopBusLinesV1();
}
