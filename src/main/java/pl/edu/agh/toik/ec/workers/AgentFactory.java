package pl.edu.agh.toik.ec.workers;

import pl.edu.agh.toik.ec.algorithm.Agent;
import pl.edu.agh.toik.ec.algorithm.AgentImpl;
import pl.edu.agh.toik.ec.configuration.AgentConfiguration;
import pl.edu.agh.toik.ec.configuration.Configuration;
import pl.edu.agh.toik.ec.namingservice.NamingService;
import pl.edu.agh.toik.ec.properties.AgentProperty;
import pl.edu.agh.toik.ec.properties.ObservationType;
import pl.edu.agh.toik.ec.properties.observation.EventObservationType;
import pl.edu.agh.toik.ec.properties.observation.LimitedObservationType;
import pl.edu.agh.toik.ec.properties.observation.PeriodicalObservationType;
import pl.edu.agh.toik.ec.topology.Topology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AgentFactory {


    private Topology topology;
    private String workerName;
    private AgentConfiguration agentConfiguration;
    private NamingService namingService;

    public AgentFactory(Configuration configuration, String workerName) {
        this.topology = configuration.getTopology();
        this.workerName = workerName;
        this.agentConfiguration = configuration.getAgentConfiguration();
        this.namingService = configuration.getNamingService();
    }

    private Agent getAgent(String name) {

        AgentImpl agentImpl = new AgentImpl();
        agentImpl.setNeighbours(topology.getNeightbours(name));

        //Agent config


        //AgentProperty config
        ObservationType observationType = getObservationType(agentConfiguration.toString());
        String agentParameter = agentConfiguration.getAgentParameter();
        AgentProperty agentProperty = new AgentProperty(agentParameter, agentImpl, observationType);


        return agentImpl;
    }

    public HashMap<String, Agent> getAgents(int numOfAgents) {
        HashMap<String, Agent> agents = new HashMap<>();
        for(int i = 0; i < numOfAgents; i++) {
            String name = namingService.getAgentId(workerName, i);
            agents.put(name, getAgent(name));
        }
        return agents;
    }

    private ObservationType getObservationType(String type) {
        ObservationType observationType;
        switch (type) {
            case "EventObservationType":
                observationType = new EventObservationType();
                break;
            case "LimitedObservationType":
                observationType = new LimitedObservationType();
                break;
            case "PeriodicalObservationType":
                observationType = new PeriodicalObservationType();
                break;
            default:
                observationType = new ObservationType() {
                    @Override
                    public boolean check(Object value) {
                        return false;
                    }
                };
                break;
        }
        return observationType;
    }

}
