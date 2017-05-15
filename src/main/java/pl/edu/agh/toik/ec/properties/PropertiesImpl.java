package pl.edu.agh.toik.ec.properties;

import pl.edu.agh.toik.ec.properties.observation.ObservationType;

import java.util.Map;

/**
 * Created by baran on 09.05.17.
 */
public class PropertiesImpl implements Properties {
    private Map<String, ObservationType> parameters;

    public PropertiesImpl(Map<String, ObservationType> parameters) {
        this.parameters = parameters;
    }

    public Map<String, ObservationType> getParameters() {
        return parameters;
    }
}
