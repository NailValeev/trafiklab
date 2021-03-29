package xyz.valeev.trafiklab.health;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import xyz.valeev.trafiklab.controller.BusLinesController;
import xyz.valeev.trafiklab.service.BusLinesService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BusLinesController.class)
@Import({BusLinesController.class, BusLinesService.class})
public class TrafiklabApplicationHealthTest {

    @Value("${pom.version: not set}")
    private String expectedPomVersion;

    @Autowired
    private MockMvc mockMvc;

    RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
            "/api/bus/health").accept(
            MediaType.APPLICATION_JSON);

    @Test
    public void health_check_should_be_successful_and_return_json() throws Exception {
        mockMvc.perform(requestBuilder)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void health_check_should_return_version_number() throws Exception {
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        JSONAssert.assertEquals(
                "{Version: " + expectedPomVersion + "}",
                result.getResponse().getContentAsString(),
                true);
    }
}


