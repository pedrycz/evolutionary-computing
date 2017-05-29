package pl.edu.agh.toik.ec.workers;

import pl.edu.agh.toik.ec.algorithm.AgentImpl;
import pl.edu.agh.toik.ec.communication.CommunicationService;
import pl.edu.agh.toik.ec.communication.Message;
import pl.edu.agh.toik.ec.topology.Topology;

import java.util.HashMap;

public class SimpleWorker implements Worker {

    private String name;
    private StopCondition stopCondition;
    private int stepCounter = 0;
    private Topology topology;
    private CommunicationService communicationService;
    private HashMap<String, AgentImpl> agents;
    private AgentFactory agentFactory;
    private boolean active = false;

    public SimpleWorker(String name, StopCondition stopCondition, Topology topology) {
        this.name = name;
        this.stopCondition = stopCondition;
        this.topology = topology;
        this.agentFactory = new AgentFactory(topology, name);
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
            for (HashMap.Entry<String, AgentImpl> entry : agents.entrySet()) {
                entry.getValue().makeStep();
            }
            stepCounter++;
            System.out.println("SimpleWorker " + name + " step");
        }
    }

    //waiting for message to clarify
    @Override
    public void sendMessage(Message msg) {

    }

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


    public int getStepCounter() {
        return stepCounter;
    }

    public void setStepCounter(int stepCounter) {
        this.stepCounter = stepCounter;
    }

    public HashMap<String, AgentImpl> getAgents() {
        return agents;
    }

    public void setAgents(HashMap<String, AgentImpl> agents) {
        this.agents = agents;
    }

}
