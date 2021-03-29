package xyz.valeev.trafiklab.repository;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;
import xyz.valeev.trafiklab.controller.BusLinesController;
import xyz.valeev.trafiklab.service.BusLinesService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = BusLinesRepository.class)
@Import({BusLinesController.class, BusLinesService.class})
public class TrafiklabApplicationRepositoryTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BusLinesRepository repository;

}

