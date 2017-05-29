package pl.edu.agh.toik.ec.properties.observation;

import pl.edu.agh.toik.ec.properties.ObservationType;

public class LimitedObservationType implements ObservationType {

    private final int min;
    private final int max;

    public LimitedObservationType(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public static LimitedObservationType getMaxQueryType(int max) {
        return new LimitedObservationType(java.lang.Integer.MIN_VALUE, max);
    }

    public static LimitedObservationType getMinQueryType(int min) {
        return new LimitedObservationType(min, java.lang.Integer.MAX_VALUE);
    }

    @Override
    public boolean check(Object value) {
        try {
            Integer intValue = (Integer) value;
            return !(intValue >= min && intValue <= max);
        } catch (ClassCastException e) {
            return false;
        }
    }
}
