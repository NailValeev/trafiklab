package xyz.valeev.trafiklab.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.valeev.trafiklab.model.BusLine;
import xyz.valeev.trafiklab.model.JourneyPattern;
import xyz.valeev.trafiklab.model.RepositoryResponse;
import xyz.valeev.trafiklab.model.StopPoint;
import xyz.valeev.trafiklab.repository.BusLinesRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BusLinesService implements IBusLinesService{

    private final Comparator<Map.Entry<Integer, List<JourneyPattern>>> byListSize =
            Map.Entry.comparingByValue(Comparator.comparingInt(List::size));

    private final BusLinesRepository repository;

    @Autowired
    public BusLinesService(BusLinesRepository repository) {
        this.repository = repository;
    }

    @Override
    public RepositoryResponse getRepositoryState () {
        return repository.getRepositoryState();
    }

    @Override
    public List<BusLine> getBusLinesV1() throws JsonProcessingException {
        return repository.getAllBusLines();
    }

    @Override
    public List<StopPoint> getBusLineStopsV1(int lineNumber) {
        List<StopPoint> busStops = repository.getBusStops();
        Map<Integer, List<JourneyPattern>> journeyPatternsMap = repository.getJourneyPatternsMap();

        List<JourneyPattern> filteredJourneyPatterns = journeyPatternsMap.getOrDefault(lineNumber, null);
        if (filteredJourneyPatterns == null) throw new IllegalArgumentException("No such bus line");

        List<StopPoint> result =  filteredJourneyPatterns.stream()
                .map(JourneyPattern::getJourneyPatternPointNumber)
                .map((pointNumber) ->
                        busStops.stream()
                                .filter(stop -> stop.getStopPointNumber() == pointNumber).findFirst().orElse(null))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<BusLine> getTopBusLinesV1() {
        List<BusLine> busLines = repository.getAllBusLines();
        Map<Integer, List<JourneyPattern>> journeyPatternsMap = repository.getJourneyPatternsMap();
        // This return statement can be refactored
        // In fact, it is not large, but looks tremendous because indentation with new lines :)
        return journeyPatternsMap.entrySet()
                .stream()
                .sorted(byListSize.reversed())
                .limit(10) // It is possible to create generic method, but I always follow requirements
                .map(Map.Entry::getKey)
                .map((lineNumber) ->
                        busLines.stream()
                                .filter(line -> line.getLineNumber() == lineNumber).findFirst().orElse(null)
                )
                .collect(Collectors.toList());
    }
}
