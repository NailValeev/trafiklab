package xyz.valeev.trafiklab.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import xyz.valeev.trafiklab.model.*;
import xyz.valeev.trafiklab.service.BusLinesService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = BusLinesController.class)
@ActiveProfiles("test")
public class BusLinesControllerTest {
    private static final Logger LOGGER= LoggerFactory.getLogger(BusLinesControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BusLinesService busLinesService;

    private List<BusLine> busLineList;

    @BeforeEach
    void setUp() throws Exception {
    }
    // Non-happy paths testing
    @Test
    public void should_return_bad_gateway_when_repository_state_is_fail() throws Exception {
        // Repository is OK
        when(busLinesService.getRepositoryState())
                .thenReturn(new RepositoryResponse(
                        RepositoryCodes.FAIL.getCode(), "Repository FAIL"
                ));
        when(busLinesService.getBusLinesV1()).thenReturn(null);
        when(busLinesService.getTopBusLinesV1()).thenReturn(null);
        when(busLinesService.getBusLineStopsV1(1)).thenReturn(null);

        mockMvc.perform(get("/api/bus/v1/lines"))
                .andExpect(status().isBadGateway());
        mockMvc.perform(get("/api/bus/v1/lines/top/"))
                .andExpect(status().isBadGateway());
        mockMvc.perform(get("/api/bus/v1/lines/1"))
                .andExpect(status().isBadGateway());

    }

    @Test
    public void should_return_bad_request_when_illegal_argument_exception_thrown() throws Exception {
        // Repository is OK
        when(busLinesService.getRepositoryState())
                .thenReturn(new RepositoryResponse(
                        RepositoryCodes.SUCCESS.getCode(), "Repository OK"
                ));
        when(busLinesService.getBusLinesV1()).thenThrow(new IllegalArgumentException("Controller test"));
        when(busLinesService.getTopBusLinesV1()).thenThrow(new IllegalArgumentException("Controller test"));
        when(busLinesService.getBusLineStopsV1(1)).thenThrow(new IllegalArgumentException("Controller test"));

        mockMvc.perform(get("/api/bus/v1/lines"))
                .andExpect(status().isBadRequest());
        mockMvc.perform(get("/api/bus/v1/lines/top/"))
                .andExpect(status().isBadRequest());
        mockMvc.perform(get("/api/bus/v1/lines/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_return_bus_lines_list_when_repository_state_is_success() throws Exception {

        List<BusLine> busLineList = new ArrayList<>();
        busLineList.add(new BusLine(3, "foo", Codes.BUS.getDefaultTransportModeCode(), "boo", "foobar"));
        busLineList.add(new BusLine(4, "foo", Codes.BUS.getDefaultTransportModeCode(), "boo", "foobar"));
        busLineList.add(new BusLine(5, "foo", Codes.BUS.getDefaultTransportModeCode(), "boo", "foobar"));

        // Repository is OK
        when(busLinesService.getRepositoryState())
                .thenReturn(new RepositoryResponse(
                        RepositoryCodes.SUCCESS.getCode(), "Repository OK"
                ));
        when(busLinesService.getBusLinesV1()).thenReturn(busLineList);

        mockMvc.perform(get("/api/bus/v1/lines"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(busLineList)));

    }

    @Test
    public void should_return_top_bus_lines_list_when_repository_state_is_success() throws Exception {
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

        // Repository is OK
        when(busLinesService.getRepositoryState())
                .thenReturn(new RepositoryResponse(
                        RepositoryCodes.SUCCESS.getCode(), "Repository OK"
                ));
        when(busLinesService.getTopBusLinesV1()).thenReturn(busLineList);

        mockMvc.perform(get("/api/bus/v1/lines/top/"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(busLineList)));

    }
    @Test
    public void should_return_stops_points_for_line_when_repository_state_is_success() throws Exception {
        List<StopPoint> busLineList = new ArrayList<>();
        busLineList.add(new StopPoint(1, "foo", 33, 33.44, 33.55, "boo", "foobar", "NEVER", "FOREVER" ));
        busLineList.add(new StopPoint(2, "foo", 33, 33.44, 33.55, "boo", "foobar", "NEVER", "FOREVER" ));
        busLineList.add(new StopPoint(3, "foo", 33, 33.44, 33.55, "boo", "foobar", "NEVER", "FOREVER" ));

        // Repository is OK
        when(busLinesService.getRepositoryState())
                .thenReturn(new RepositoryResponse(
                        RepositoryCodes.SUCCESS.getCode(), "Repository OK"
                ));
        when(busLinesService.getBusLineStopsV1(1)).thenReturn(busLineList);

        mockMvc.perform(get("/api/bus/v1/lines/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(busLineList)));

    }
}
