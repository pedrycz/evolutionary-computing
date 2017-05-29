package pl.edu.agh.toik.ec.properties.observation;

import pl.edu.agh.toik.ec.properties.ObservationType;

import java.util.Date;

public class PeriodicalObservationType implements ObservationType {

    private final int interval;
    private long lastCheckTimestamp;

    public PeriodicalObservationType(int interval) {
        this.interval = interval;
    }

    @Override
    public boolean check(Object value) {
        long timestamp = new Date().getTime();
        if (lastCheckTimestamp == 0 || lastCheckTimestamp + interval > timestamp) {
            lastCheckTimestamp = timestamp;
            return true;
        } else return false;
    }
}
