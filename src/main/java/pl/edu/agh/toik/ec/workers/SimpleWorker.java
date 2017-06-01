package pl.edu.agh.toik.ec.workers;

import pl.edu.agh.toik.ec.algorithm.Agent;
import pl.edu.agh.toik.ec.algorithm.AgentImpl;
import pl.edu.agh.toik.ec.communication.CommunicationService;
import pl.edu.agh.toik.ec.communication.Message;
import pl.edu.agh.toik.ec.configuration.AgentConfiguration;
import pl.edu.agh.toik.ec.namingservice.NamingService;
import pl.edu.agh.toik.ec.topology.Topology;

import java.util.HashMap;

public class SimpleWorker implements Worker {

    private String name;
    private StopCondition stopCondition;
    private Topology topology;
    private CommunicationService communicationService;
    private HashMap<String, Agent> agents;
    private AgentFactory agentFactory;
    private boolean active = false;
    private NamingService namingService;

    public SimpleWorker(String name, StopCondition stopCondition, Topology topology,
                        CommunicationService communicationService, NamingService namingService,
                        AgentConfiguration agentConfiguration) {

        this.name = name;
        this.stopCondition = stopCondition;
        this.topology = topology;
        this.communicationService = communicationService;
        this.namingService = namingService;
        this.agentFactory = new AgentFactory(topology, name, agentConfiguration, namingService);
    }

    public void createAgents(int numOfAgents) {
        this.agents = this.agentFactory.getAgents(numOfAgents);
    }

    @Override
    public void start() {
        this.active = true;
        System.out.println("SimpleWorker " + name + " started");
    }


    @Override
    public void step() {
        if(checkStopCondition()) {
            for (HashMap.Entry<String, Agent> entry : agents.entrySet()) {
                entry.getValue().makeStep();
            }
            System.out.println("SimpleWorker " + name + " step");
        } else {
            active = false;
        }
    }

    @Override
    public void sendMessage(SimpleMessage msg) {
        if(agents.containsKey(msg.getReceiver())) {
            agents.get(msg.getReceiver()).receiveMessage(msg);
        } else {
            communicationService.send(msg);
        }
    }

    @Override
    public boolean checkStopCondition() {
        return this.stopCondition.isActive();
    }

    @Override
    public boolean isActive() {
        return this.active;
    }

    public void stop() {
        this.active = false;
    }

    public HashMap<String, Agent> getAgents() {
        return agents;
    }

    public String getName() {
        return this.name;
    }
}
