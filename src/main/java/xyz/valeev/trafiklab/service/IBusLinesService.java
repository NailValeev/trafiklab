package xyz.valeev.trafiklab.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import xyz.valeev.trafiklab.model.BusLine;
import xyz.valeev.trafiklab.model.RepositoryResponse;
import xyz.valeev.trafiklab.model.StopPoint;

import java.util.List;
// It is possible to remove this interface, because implementation is only one
// Still keep it to have a nice project structure
public interface IBusLinesService {
    RepositoryResponse getRepositoryState();

    public List<BusLine> getBusLinesV1() throws JsonProcessingException;
    public List<StopPoint> getBusLineStopsV1(int lineNumber);
    public List<BusLine> getTopBusLinesV1();
}
