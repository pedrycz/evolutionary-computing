package pl.edu.agh.toik.ec.workers;

import pl.edu.agh.toik.ec.algorithm.Agent;
import pl.edu.agh.toik.ec.algorithm.AgentImpl;
import pl.edu.agh.toik.ec.algorithm.Property;
import pl.edu.agh.toik.ec.draft.AgentConfiguration;
import pl.edu.agh.toik.ec.draft.AgentParameter;
import pl.edu.agh.toik.ec.draft.PropertyConfiguration;
import pl.edu.agh.toik.ec.properties.AgentProperty;
import pl.edu.agh.toik.ec.properties.ObservationType;
import pl.edu.agh.toik.ec.properties.observation.EventObservationType;
import pl.edu.agh.toik.ec.properties.observation.LimitedObservationType;
import pl.edu.agh.toik.ec.properties.observation.PeriodicalObservationType;
import pl.edu.agh.toik.ec.topology.Topology;

import java.util.HashMap;
import java.util.Map;

public class AgentFactory {


    private final AgentConfiguration agentConfiguration;
    private Topology topology;
    private String workerName;

    public AgentFactory(Topology topology, String workerName, AgentConfiguration agentConfiguration) {
        this.topology = topology;
        this.workerName = workerName;
        this.agentConfiguration = agentConfiguration;
    }

    //waiting for topology
    public AgentImpl getAgent(String name) {
        AgentImpl agentImpl = new AgentImpl();

        agentImpl.setBestFitnessProperty(createAgentProperty(agentImpl, AgentParameter.BEST_FITNESS));
        agentImpl.setWorstFitnessProperty(createAgentProperty(agentImpl, AgentParameter.WORST_FITNESS));
        agentImpl.setPopulationDiversityProperty(createAgentProperty(agentImpl, AgentParameter.POPULATION_DIVERSITY));

        return agentImpl;
    }

    private <T> Property<T> createAgentProperty(Agent agent, AgentParameter agentParameter) {
        PropertyConfiguration propertyConfiguration = agentConfiguration.getParameterConfiguration().get(agentParameter);
        if (propertyConfiguration.isObserved()) {
            ObservationType observationType = createObservationType(propertyConfiguration.getObservationTypeName(), propertyConfiguration.getObservationArguments());
            return new AgentProperty<>(agentParameter.name(), agent, observationType);
        } else {
            return new Property<>();
        }
    }

    private ObservationType createObservationType(String type, Map<String, String> arguments) {
        switch (type) {
            case PropertyConfiguration.EVENT_OBSERVATION:
                return new EventObservationType();
            case PropertyConfiguration.LIMITED_OBSERVATION:
                int min = Integer.valueOf(arguments.get("min"));
                int max = Integer.valueOf(arguments.get("max"));
                return new LimitedObservationType(min, max);
            case PropertyConfiguration.PERIODICAL_OBSERVATION:
                int interval = Integer.valueOf(arguments.get("interval"));
                return new PeriodicalObservationType(interval);
            default:
                return null;
        }
    }

    public HashMap<String, AgentImpl> getAgents(int numOfAgents) {
        HashMap<String, AgentImpl> agents = new HashMap<>();
        for (int i = 0; i < numOfAgents; i++) {
            agents.put("agent" + i, new AgentImpl());
        }
        return agents;
    }

}
