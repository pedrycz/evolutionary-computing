package pl.edu.agh.toik.ec.workers;

import pl.edu.agh.toik.ec.aglorithm.Agent;

import java.util.ArrayList;
import java.util.List;

public class AgentFactory {


    public AgentFactory() {};
    //where should agent name be decided?
    public Agent getAgent(String name) {
        Agent agent = new Agent();
        return agent;
    }

    public List<Agent> getAgents(int numOfAgents) {
        List<Agent> agents = new ArrayList<>();
        for(int i = 0; i < numOfAgents; i++) {
            agents.add(getAgent("agent" + i));
        }
        return agents;
    }

}
