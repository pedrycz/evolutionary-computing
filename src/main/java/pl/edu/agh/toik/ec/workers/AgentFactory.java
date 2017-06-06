package pl.edu.agh.toik.ec.workers;

import pl.edu.agh.toik.ec.algorithm.Agent;
import pl.edu.agh.toik.ec.algorithm.AgentImpl;
import pl.edu.agh.toik.ec.algorithm.AlgorithmStep;
import pl.edu.agh.toik.ec.algorithm.Property;
import pl.edu.agh.toik.ec.configuration.AgentConfiguration;
import pl.edu.agh.toik.ec.configuration.Configuration;
import pl.edu.agh.toik.ec.configuration.AgentParameter;
import pl.edu.agh.toik.ec.configuration.PropertyConfiguration;
import pl.edu.agh.toik.ec.namingservice.NamingService;
import pl.edu.agh.toik.ec.properties.AgentProperty;
import pl.edu.agh.toik.ec.properties.ObservationType;
import pl.edu.agh.toik.ec.properties.observation.EventObservationType;
import pl.edu.agh.toik.ec.properties.observation.LimitedObservationType;
import pl.edu.agh.toik.ec.properties.observation.PeriodicalObservationType;
import pl.edu.agh.toik.ec.topology.Topology;

import java.util.HashMap;
import java.util.Map;

public class AgentFactory {


    private Topology topology;
    private SimpleWorker worker;
    private AgentConfiguration agentConfiguration;
    private NamingService namingService;

    public AgentFactory(Configuration configuration, SimpleWorker worker) {
        this.topology = configuration.getTopology();
        this.worker = worker;
        this.agentConfiguration = configuration.getAgentConfiguration();
        this.namingService = configuration.getNamingService();
    }

    private Agent getAgent(String name) {

        AgentImpl agentImpl = new AgentImpl();
        agentImpl.setNeighbours(topology.getNeightbours(name));

        //Agent config
        agentImpl.setPopulationDimension(agentConfiguration.getPopulationDimension());
        agentImpl.setPopulationGenerationStrategy(agentConfiguration.getPopulationGenerationStrategy());
        agentImpl.setPopulationSize(agentConfiguration.getPopulationSize());
        for (AlgorithmStep step : agentConfiguration.getAlgorithmSteps()) {
            agentImpl.addStep(step);
        }
        agentImpl.setWorker(worker);
        agentImpl.setName(name);

        //AgentProperty config
        agentImpl.setBestFitnessProperty(createAgentProperty(agentImpl, AgentParameter.BEST_FITNESS));
        agentImpl.setWorstFitnessProperty(createAgentProperty(agentImpl, AgentParameter.WORST_FITNESS));
        agentImpl.setPopulationDiversityProperty(createAgentProperty(agentImpl, AgentParameter.POPULATION_DIVERSITY));

        agentImpl.setup();

        return agentImpl;
    }

    public HashMap<String, Agent> getAgents(int numOfAgents) {
        HashMap<String, Agent> agents = new HashMap<>();
        for (int i = 0; i < numOfAgents; i++) {
            String name = namingService.getAgentId(worker.getWorkerName(), i);
            agents.put(name, getAgent(name));
        }
        return agents;
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

}
