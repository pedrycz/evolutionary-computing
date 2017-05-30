package pl.edu.agh.toik.ec.workers;

import pl.edu.agh.toik.ec.algorithm.AgentImpl;
import pl.edu.agh.toik.ec.configuration.AgentConfiguration;
import pl.edu.agh.toik.ec.namingservice.NamingService;
import pl.edu.agh.toik.ec.properties.AgentProperty;
import pl.edu.agh.toik.ec.properties.ObservationType;
import pl.edu.agh.toik.ec.properties.observation.EventObservationType;
import pl.edu.agh.toik.ec.topology.Topology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AgentFactory {


    private Topology topology;
    private String workerName;
    private AgentConfiguration agentConfiguration;
    private NamingService namingService;

    public AgentFactory(Topology topology, String workerName, AgentConfiguration agentConfiguration, NamingService namingService) {
        this.topology = topology;
        this.workerName = workerName;
        this.agentConfiguration = agentConfiguration;
        this.namingService = namingService;
    }

    public AgentImpl getAgent(String name) {
        AgentImpl agentImpl = new AgentImpl();
        agentImpl.setNeighbours(topology.getNeightbours(name));
        ObservationType observationType = getObservationType(agentConfiguration.toString());

        String agentParameter = "";
        String starterId = "";

        AgentProperty agentProperty = new AgentProperty(agentParameter, agentImpl, observationType, starterId);
        return agentImpl;
    }

    public HashMap<String, AgentImpl> getAgents(int numOfAgents) {
        HashMap<String, AgentImpl> agents = new HashMap<>();
        for(int i = 0; i < numOfAgents; i++) {
            String name = "agent" + i;
            agents.put(name, getAgent(name));
        }
        return agents;
    }

    private ObservationType getObservationType(String type) {
        ObservationType observationType = new EventObservationType();
        return observationType;
    }

}
