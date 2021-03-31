package xyz.valeev.trafiklab.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.valeev.trafiklab.model.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BusLinesRepositoryTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    String apiKey;

    @Autowired
    BusLinesRepository repository;

    @Test
    public void should_fail_if_key_is_invalid() {
        URI validUri = repository.trafiklabUriResolver("foo", String.valueOf(Models.stop));
        String body = this.restTemplate.getForObject(validUri, String.class);
        assertThat(body).isEqualTo("{\"StatusCode\":1002,\"Message\":\"Key is invalid\"}");
    }

    @Test
    public void should_fetch_data_if_URL_is_valid() {
        URI validUri = repository.trafiklabUriResolver(apiKey, String.valueOf(Models.stop));
        RepositoryResponse response = this.repository.fetchTrafiklabData(validUri);
        assertThat(response.getStatusCode()).isEqualTo(RepositoryCodes.SUCCESS.getCode());
    }

    @Test
    public void should_fetch_filtered_data_if_URL_is_valid() {
        URI validUri = repository.trafiklabUriResolver(apiKey, String.valueOf(Models.stop), Codes.BUS.getDefaultTransportModeCode());
        RepositoryResponse response = this.repository.fetchTrafiklabData(validUri);
        assertThat(response.getStatusCode()).isEqualTo(RepositoryCodes.SUCCESS.getCode());
    }

    @Test
    public void should_fetch_all_bus_lines() {
        List<BusLine> response = this.repository.getAllBusLines();
        assertThat(response.size() > 0);
    }

    @Test
    public void should_fetch_all_stops() {
        List<StopPoint> response = this.repository.getBusStops();
        assertThat(response.size() > 0);
    }

    @Test
    public void should_fetch_journey_paths_map() {
        Map<Integer, List<JourneyPattern>> response = this.repository.getJourneyPatternsMap();
        assertThat(response.size() > 0);
    }
}
