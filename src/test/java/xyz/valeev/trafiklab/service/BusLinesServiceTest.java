package xyz.valeev.trafiklab.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.valeev.trafiklab.model.BusLine;
import xyz.valeev.trafiklab.model.Codes;
import xyz.valeev.trafiklab.model.JourneyPattern;
import xyz.valeev.trafiklab.model.StopPoint;
import xyz.valeev.trafiklab.repository.BusLinesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BusLinesServiceTest {

    @MockBean
    private BusLinesRepository repository;

    @Autowired
    private BusLinesService service;


    @Test
    public void should_return_all_bus_lines_fetched_from_repository() throws JsonProcessingException {
        List<BusLine> busLineList = new ArrayList<>();
        busLineList.add(new BusLine(1, "foo", Codes.BUS.getDefaultTransportModeCode(), "boo", "foobar"));
        busLineList.add(new BusLine(2, "foo", Codes.BUS.getDefaultTransportModeCode(), "boo", "foobar"));
        busLineList.add(new BusLine(3, "foo", Codes.BUS.getDefaultTransportModeCode(), "boo", "foobar"));

        // This is simple 1-to-1
        given(this.repository.getAllBusLines()).willReturn(busLineList);
        List<BusLine> result = service.getBusLinesV1();
        assertThat(result).isEqualTo(busLineList);
    }

    @Test
    public void should_return_top_bus_lines() throws JsonProcessingException {
        List<BusLine> busLineList = new ArrayList<>();
        busLineList.add(new BusLine(1, "foo", Codes.BUS.getDefaultTransportModeCode(), "boo", "foobar"));
        busLineList.add(new BusLine(2, "foo", Codes.BUS.getDefaultTransportModeCode(), "boo", "foobar"));
        busLineList.add(new BusLine(3, "foo", Codes.BUS.getDefaultTransportModeCode(), "boo", "foobar"));
        busLineList.add(new BusLine(4, "foo", Codes.BUS.getDefaultTransportModeCode(), "boo", "foobar"));
        busLineList.add(new BusLine(5, "foo", Codes.BUS.getDefaultTransportModeCode(), "boo", "foobar"));
        busLineList.add(new BusLine(6, "foo", Codes.BUS.getDefaultTransportModeCode(), "boo", "foobar"));
        busLineList.add(new BusLine(7, "foo", Codes.BUS.getDefaultTransportModeCode(), "boo", "foobar"));
        busLineList.add(new BusLine(8, "foo", Codes.BUS.getDefaultTransportModeCode(), "boo", "foobar"));
        busLineList.add(new BusLine(9, "foo", Codes.BUS.getDefaultTransportModeCode(), "boo", "foobar"));
        busLineList.add(new BusLine(10, "foo", Codes.BUS.getDefaultTransportModeCode(), "boo", "foobar"));
        busLineList.add(new BusLine(11, "foo", Codes.BUS.getDefaultTransportModeCode(), "boo", "foobar"));
        busLineList.add(new BusLine(12, "foo", Codes.BUS.getDefaultTransportModeCode(), "boo", "foobar"));

        // Teh line number 5 should be 1st in list of 10
        List<JourneyPattern> journeyPatternList = new ArrayList<>();
        journeyPatternList.add(new JourneyPattern(1, "foo", 33, "boo", "foobar"));
        journeyPatternList.add(new JourneyPattern(2, "foo", 33, "boo", "foobar"));
        journeyPatternList.add(new JourneyPattern(3, "foo", 33, "boo", "foobar"));
        journeyPatternList.add(new JourneyPattern(4, "foo", 33, "boo", "foobar"));
        journeyPatternList.add(new JourneyPattern(5, "foo", 33, "boo", "foobar"));
        journeyPatternList.add(new JourneyPattern(5, "foo", 44, "boo", "foobar"));
        journeyPatternList.add(new JourneyPattern(5, "foo", 55, "boo", "foobar"));
        journeyPatternList.add(new JourneyPattern(6, "foo", 33, "boo", "foobar"));
        journeyPatternList.add(new JourneyPattern(7, "foo", 33, "boo", "foobar"));
        journeyPatternList.add(new JourneyPattern(8, "foo", 33, "boo", "foobar"));
        journeyPatternList.add(new JourneyPattern(9, "foo", 33, "boo", "foobar"));
        journeyPatternList.add(new JourneyPattern(10, "foo", 33, "boo", "foobar"));
        journeyPatternList.add(new JourneyPattern(11, "foo", 33, "boo", "foobar"));
        journeyPatternList.add(new JourneyPattern(12, "foo", 33, "boo", "foobar"));
        journeyPatternList.add(new JourneyPattern(13, "foo", 33, "boo", "foobar"));
        journeyPatternList.add(new JourneyPattern(14, "foo", 33, "boo", "foobar"));

        Map<Integer, List<JourneyPattern>> journeyPatternsMap;

        journeyPatternsMap = journeyPatternList
                .stream()
                .collect(Collectors.groupingBy(JourneyPattern::getLineNumber));
        // This is simple 1-to-1
        given(this.repository.getAllBusLines()).willReturn(busLineList);
        given(this.repository.getJourneyPatternsMap()).willReturn(journeyPatternsMap);
        List<BusLine> result = service.getTopBusLinesV1();
        assertThat(result.size()).isEqualTo(10);
        assertThat(result.get(0).getLineNumber()).isEqualTo(5);
    }


    @Test
    public void should_return_stops_for_bus_line() throws JsonProcessingException {
        List<StopPoint> busStops = new ArrayList<>();
        busStops.add(new StopPoint(11,"foo", 111, 11.11,22.22, "foo", "boo", "foobar", "boobar"));
        busStops.add(new StopPoint(11,"foo", 111, 11.11,22.22, "foo", "boo", "foobar", "boobar"));
        busStops.add(new StopPoint(22,"foo", 111, 11.11,22.22, "foo", "boo", "foobar", "boobar"));
        busStops.add(new StopPoint(33,"foo", 111, 11.11,22.22, "foo", "boo", "foobar", "boobar"));

        // Teh line number 5 should be 1st in list of 10
        List<JourneyPattern> journeyPatternList = new ArrayList<>();
        journeyPatternList.add(new JourneyPattern(1, "foo", 11, "boo", "foobar"));
        journeyPatternList.add(new JourneyPattern(2, "foo", 22, "boo", "foobar"));

        Map<Integer, List<JourneyPattern>> journeyPatternsMap;

        journeyPatternsMap = journeyPatternList
                .stream()
                .collect(Collectors.groupingBy(JourneyPattern::getLineNumber));
        // This is simple 1-to-1
        given(this.repository.getBusStops()).willReturn(busStops);
        given(this.repository.getJourneyPatternsMap()).willReturn(journeyPatternsMap);

        List<StopPoint> result = service.getBusLineStopsV1(1);
        assertThat(result.size()).isEqualTo(1);
    }
    @Test
    public void should_throw_illegal_argument_exception_if_no_such_bus_line() throws JsonProcessingException {
        List<StopPoint> busStops = new ArrayList<>();
        busStops.add(new StopPoint(11,"foo", 111, 11.11,22.22, "foo", "boo", "foobar", "boobar"));
        busStops.add(new StopPoint(11,"foo", 111, 11.11,22.22, "foo", "boo", "foobar", "boobar"));
        busStops.add(new StopPoint(22,"foo", 111, 11.11,22.22, "foo", "boo", "foobar", "boobar"));
        busStops.add(new StopPoint(33,"foo", 111, 11.11,22.22, "foo", "boo", "foobar", "boobar"));

        // Teh line number 5 should be 1st in list of 10
        List<JourneyPattern> journeyPatternList = new ArrayList<>();
        journeyPatternList.add(new JourneyPattern(1, "foo", 11, "boo", "foobar"));
        journeyPatternList.add(new JourneyPattern(2, "foo", 22, "boo", "foobar"));

        Map<Integer, List<JourneyPattern>> journeyPatternsMap;

        journeyPatternsMap = journeyPatternList
                .stream()
                .collect(Collectors.groupingBy(JourneyPattern::getLineNumber));
        // This is simple 1-to-1
        given(this.repository.getBusStops()).willReturn(busStops);
        given(this.repository.getJourneyPatternsMap()).willReturn(journeyPatternsMap);

        String exceptionMessage = "";
        try {
            service.getBusLineStopsV1(3);
        } catch (IllegalArgumentException iae) {
            exceptionMessage = iae.getMessage();
        }
        assertThat(exceptionMessage).isEqualTo("No such bus line");
    }

}