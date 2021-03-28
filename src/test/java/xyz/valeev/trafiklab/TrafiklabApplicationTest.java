package xyz.valeev.trafiklab;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.valeev.trafiklab.controller.BusLinesController;

@SpringBootTest
public class TrafiklabApplicationTest {

    @Autowired
    BusLinesController busLinesController;

    @Test
    void contextLoads() {
    }
}


