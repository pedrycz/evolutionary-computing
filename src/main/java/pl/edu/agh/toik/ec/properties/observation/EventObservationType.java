package pl.edu.agh.toik.ec.properties.observation;

import pl.edu.agh.toik.ec.properties.ObservationType;

public class EventObservationType implements ObservationType {

    @Override
    public boolean check(Object value) {
        return true;
    }
}
