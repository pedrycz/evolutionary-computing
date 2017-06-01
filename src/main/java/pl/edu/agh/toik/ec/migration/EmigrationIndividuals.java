package pl.edu.agh.toik.ec.migration;

import pl.edu.agh.toik.ec.algorithm.Agent;
import pl.edu.agh.toik.ec.algorithm.Individual;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmigrationIndividuals implements EmigrationService {
    Agent agent;
    EmigrateStrategy emigrateStrategy;

    public EmigrationIndividuals(EmigrateStrategy emigrateStrategy){
        this.emigrateStrategy = emigrateStrategy;
    }

    private void emigrate(Agent agent, List<Individual> population) {
        this.agent = agent;
        List<Individual> selectedIndividuals = emigrateStrategy.selectIndividuals(population);
        List<MigrationMessage> migrationMessages = createMessages(selectedIndividuals);
        for(MigrationMessage migrationMessage : migrationMessages){
            agent.sendMessage(migrationMessage.getReceiver(),migrationMessage);
        }
    }

    private List<MigrationMessage> createMessages(List<Individual> individuals){
        List<MigrationMessage> migrationMessages = new ArrayList<MigrationMessage>();
        for(Agent receiverAgent : agent.getNeighbours()){
            migrationMessages.add(new MigrationMessage(agent.getName(), receiverAgent.getName(), individuals, new Date()));
        }
        return  migrationMessages;
    }

    public void process(Agent agent, List<Individual> population) {
        emigrate(agent, population);
    }
}
