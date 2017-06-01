package pl.edu.agh.toik.ec.workers;

import pl.edu.agh.toik.ec.algorithm.AgentImpl;
import pl.edu.agh.toik.ec.communication.CommunicationService;
import pl.edu.agh.toik.ec.communication.Message;
import pl.edu.agh.toik.ec.configuration.Configuration;
import pl.edu.agh.toik.ec.namingservice.NamingService;
import pl.edu.agh.toik.ec.topology.Topology;

import java.util.HashMap;

public class SimpleWorker implements Worker {

    private String name;
    private StopCondition stopCondition;
    private Topology topology;
    private CommunicationService communicationService;
    private HashMap<String, AgentImpl> agents;
    private AgentFactory agentFactory;
    private boolean active = false;
    private NamingService namingService;

    public SimpleWorker(String name, StopCondition stopCondition, Configuration configuration) {

        this.name = name;
        this.stopCondition = stopCondition;
        this.topology = configuration.getTopology();
        this.communicationService = configuration.getCommunicationService();
        this.namingService = configuration.getNamingService();

        this.agentFactory = new AgentFactory(topology, name, configuration.getAgentConfiguration());
    }

    public void createAgents(int numOfAgents) {
        this.agents = this.agentFactory.getAgents(numOfAgents);
/*        add this?
        for (AgentImpl agent : agents.values()) {
            agent.setup();
        }*/
    }

    @Override
    public void start() {
        this.active = true;
        System.out.println("SimpleWorker " + name + " started");
    }


    @Override
    public void step() {
        if (checkStopCondition()) {
            for (HashMap.Entry<String, AgentImpl> entry : agents.entrySet()) {
                entry.getValue().makeStep();
            }
            System.out.println("SimpleWorker " + name + " step");
        } else {
            active = false;
        }
    }

    //waiting for message to clarify
    @Override
    public void sendMessage(Message msg) {
        if (msg instanceof SimpleMessage) {
            SimpleMessage simpleMsg = (SimpleMessage) msg;
            if (agents.containsKey(simpleMsg.getReceiver())) {
                agents.get(simpleMsg.getReceiver()).receiveMessage(simpleMsg);
                return;
            }
        }

        communicationService.send(msg);
    }

    public void sendMessage(SimpleMessage msg) {

        if (agents.containsKey(msg.getReceiver())) {
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


    public HashMap<String, AgentImpl> getAgents() {
        return agents;
    }

    public void setAgents(HashMap<String, AgentImpl> agents) {
        this.agents = agents;
    }

}
