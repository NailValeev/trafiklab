package xyz.valeev.trafiklab.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import xyz.valeev.trafiklab.model.BusLine;
import xyz.valeev.trafiklab.model.StopPoint;

import java.util.List;

public interface IBusLinesService {
    public List<BusLine> getBusLinesV1() throws JsonProcessingException;
    public List<StopPoint> getBusLineStopsV1(int lineNumber);
    public List<BusLine> getTopBusLinesV1();
}
