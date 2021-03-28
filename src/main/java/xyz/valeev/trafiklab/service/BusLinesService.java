package xyz.valeev.trafiklab.service;

import org.springframework.stereotype.Service;
import xyz.valeev.trafiklab.model.Line;

import java.util.List;
@Service
public class BusLinesService implements IBusLinesService{
    @Override
    public List<Line> getLines() {
        return null;
    }
}
