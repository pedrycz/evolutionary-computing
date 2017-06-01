package pl.edu.agh.toik.ec.configuration;

import pl.edu.agh.toik.ec.draft.AgentParameter;
import pl.edu.agh.toik.ec.draft.PropertyConfiguration;

import java.util.Map;

/**
 * Created by M on 2017-05-30.
 */
public interface AgentConfiguration {

    Map<AgentParameter, PropertyConfiguration> getParameterConfiguration();

}
