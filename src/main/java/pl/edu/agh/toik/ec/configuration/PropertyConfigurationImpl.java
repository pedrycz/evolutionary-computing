package pl.edu.agh.toik.ec.configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by baran on 02.06.17.
 */
public class PropertyConfigurationImpl implements PropertyConfiguration {

    private boolean isObserved;
    private String observationTypeName;
    private Map<String, String> observationArguments;

    public PropertyConfigurationImpl(boolean isObserved) {
        this(isObserved, null);
    }

    public PropertyConfigurationImpl(boolean isObserved, String observationTypeName) {
        this(isObserved, observationTypeName, new HashMap<>());
    }

    public PropertyConfigurationImpl(boolean isObserved, String observationTypeName, Map<String, String> observationArguments) {
        this.isObserved = isObserved;
        this.observationTypeName = observationTypeName;
        this.observationArguments = observationArguments;
    }

    @Override
    public boolean isObserved() {
        return isObserved;
    }

    @Override
    public String getObservationTypeName() {
        return observationTypeName;
    }

    @Override
    public Map<String, String> getObservationArguments() {
        return observationArguments;
    }
}
