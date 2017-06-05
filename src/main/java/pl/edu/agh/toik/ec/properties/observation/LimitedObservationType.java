package pl.edu.agh.toik.ec.properties.observation;

import pl.edu.agh.toik.ec.properties.ObservationType;

public class LimitedObservationType implements ObservationType {

    private final double min;
    private final double max;

    public LimitedObservationType(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public static LimitedObservationType getMaxQueryType(double max) {
        return new LimitedObservationType(java.lang.Double.MIN_VALUE, max);
    }

    public static LimitedObservationType getMinQueryType(double min) {
        return new LimitedObservationType(min, java.lang.Double.MAX_VALUE);
    }

    @Override
    public boolean check(Object value) {
        try {
            Double doubleValue = (Double) value;
            return !(doubleValue >= min && doubleValue <= max);
        } catch (ClassCastException e) {
            return false;
        }
    }
}
