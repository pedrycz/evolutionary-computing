package pl.edu.agh.toik.ec.draft;

import java.util.Map;

public interface AgentConfiguration {

    Map<AgentParameter, PropertyConfiguration> getParameterConfiguration();

}
