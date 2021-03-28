package xyz.valeev.trafiklab.service;

import org.springframework.stereotype.Service;
import xyz.valeev.trafiklab.model.Line;
import xyz.valeev.trafiklab.model.StopPoint;

import java.util.List;
@Service
public class BusLinesService implements IBusLinesService{
    @Override
    public List<Line> getBusLinesV1() {
        return null;
    }

    @Override
    public List<StopPoint> getBusLineStopsV1() {
        return null;
    }

    @Override
    public List<Line> getTopBusLinesV1() {
        return null;
    }
}
