package xyz.valeev.trafiklab.validation;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import xyz.valeev.trafiklab.controller.BusLinesController;
import xyz.valeev.trafiklab.service.BusLinesService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = BusLinesController.class)
@Import({BusLinesController.class, BusLinesService.class})
public class TrafiklabApplicationValidationTest {

    private final int INVALID_LINE_NUMBER = -2;

    @Autowired
    private MockMvc mockMvc;

    RequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/bus/v1/lines/" + INVALID_LINE_NUMBER)
            .accept(MediaType.APPLICATION_JSON);

    @Test
    public void when_line_number_is_invalid_then_return_bad_request() throws Exception {
        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest());
    }
}

