package xyz.valeev.trafiklab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrafiklabApplication {
    private static final Logger LOGGER=LoggerFactory.getLogger(TrafiklabApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TrafiklabApplication.class, args);
    }
}
