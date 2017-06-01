package pl.edu.agh.toik.ec.configuration;

import java.util.Map;

public interface PropertyConfiguration {

    String EVENT_OBSERVATION = "EVENT";
    String LIMITED_OBSERVATION = "LIMITED";
    String PERIODICAL_OBSERVATION = "PERIODICAL";

    boolean isObserved();

    String getObservationTypeName();

    Map<String, String> getObservationArguments();
}
