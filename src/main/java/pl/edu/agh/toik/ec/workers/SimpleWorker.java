package pl.edu.agh.toik.ec.workers;

import pl.edu.agh.toik.ec.aglorithm.Agent;
import pl.edu.agh.toik.ec.communication.CommunicationService;
import pl.edu.agh.toik.ec.communication.Message;
import pl.edu.agh.toik.ec.topology.Topology;

import java.util.List;

public class SimpleWorker implements Worker {

    private String name;
    private StopCondition stopCondition;
    private int stepCounter;
    private Topology topology;
    private CommunicationService communicationService;

    private List<Agent> agents;
    private AgentFactory agentFactory;

    public SimpleWorker(String name, StopCondition stopCondition, Topology topology) {
        this.name = name;
        this.stopCondition = stopCondition;
        this.topology = topology;
    }

    @Override
    public void start() {
        System.out.println("SimpleWorker " + name + " started");
    }

    @Override
    public boolean isActive() {
        return stopCondition.isActive();
    }

    @Override
    public void step() {
        System.out.println("SimpleWorker " + name + " step");
    }

    //stub
    public Agent checkOwnAgents(String name) {
    }

    public void sendMessage(Message msg) {
        Agent agent = checkOwnAgents(msg.getReceiver());
        if(agent!=null) {
            agent.receiveMessage(msg);
        } else {
            //stub
            communicationService.send(msg);
            System.out.println("msg sent from worker: " + name);
        }

    }

    @Override
    public boolean checkStopCondition() {
        return false;
    }

}
