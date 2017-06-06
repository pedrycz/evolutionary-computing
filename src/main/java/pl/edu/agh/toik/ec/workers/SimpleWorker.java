package pl.edu.agh.toik.ec.workers;

import pl.edu.agh.toik.ec.algorithm.Agent;
import pl.edu.agh.toik.ec.communication.CommunicationService;
import pl.edu.agh.toik.ec.communication.Message;
import pl.edu.agh.toik.ec.communication.ReceiverInterface;
import pl.edu.agh.toik.ec.configuration.Configuration;
import pl.edu.agh.toik.ec.namingservice.NamingService;

import java.util.HashMap;

public class SimpleWorker implements Worker, ReceiverInterface {

    private String name;
    private StopCondition stopCondition;
    private CommunicationService communicationService;
    private HashMap<String, Agent> agents;
    private AgentFactory agentFactory;
    private boolean active = false;
    private NamingService namingService;

    public SimpleWorker(String name, StopCondition stopCondition, Configuration configuration) {

        this.name = name;
        this.stopCondition = stopCondition;
        this.communicationService = configuration.getCommunicationService();
        this.namingService = configuration.getNamingService();


        this.agentFactory = new AgentFactory(configuration, this);
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
        if(checkStopCondition()) {
            for (HashMap.Entry<String, Agent> entry : agents.entrySet()) {
                entry.getValue().makeStep();
            }
            System.out.println("SimpleWorker " + name + " step");
        } else {
            active = false;
        }
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


    public HashMap<String, Agent> getAgents() {
        return agents;
    }

    public void setAgents(HashMap<String, Agent> agents) {
        this.agents = agents;
    }

    public String getWorkerName() {
        return this.name;
    }

    @Override
    public void notify(Message message) {
        sendMessage((SimpleMessage) message);
    }
}
