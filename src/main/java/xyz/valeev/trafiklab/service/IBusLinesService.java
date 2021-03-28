package xyz.valeev.trafiklab.service;

import xyz.valeev.trafiklab.model.Line;
import xyz.valeev.trafiklab.model.StopPoint;

import java.util.List;

public interface IBusLinesService {
    public List<Line> getBusLinesV1();
    public List<StopPoint> getBusLineStopsV1();

    List<Line> getTopBusLinesV1();
}
