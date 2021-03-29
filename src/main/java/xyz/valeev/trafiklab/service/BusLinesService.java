package xyz.valeev.trafiklab.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.valeev.trafiklab.model.Line;
import xyz.valeev.trafiklab.model.StopPoint;
import xyz.valeev.trafiklab.repository.BusLinesRepository;

import java.util.List;
@Service
public class BusLinesService implements IBusLinesService{

    private final BusLinesRepository repository;

    @Autowired
    public BusLinesService(BusLinesRepository repository) {
        this.repository = repository;
    }

    @Override
    public String getBusLinesV1() throws JsonProcessingException {
        return repository.fetchBusLines();
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
