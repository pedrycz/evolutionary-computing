package pl.edu.agh.toik.ec.workers;

import pl.edu.agh.toik.ec.algorithm.AgentImpl;
import pl.edu.agh.toik.ec.topology.Topology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AgentFactory {


    private Topology topology;
    private String workerName;

    public AgentFactory(Topology topology, String workerName) {
        this.topology = topology;
        this.workerName = workerName;
    }

    //waiting for topology + properties
    public AgentImpl getAgent(String name) {
        AgentImpl agentImpl = new AgentImpl();
        return agentImpl;
    }

    public HashMap<String, AgentImpl> getAgents(int numOfAgents) {
        HashMap<String, AgentImpl> agents = new HashMap<>();
        for(int i = 0; i < numOfAgents; i++) {
            agents.put("agent" + i, new AgentImpl());
        }
        return agents;
    }

}
