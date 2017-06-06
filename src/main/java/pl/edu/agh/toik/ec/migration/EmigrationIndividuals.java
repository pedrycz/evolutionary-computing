package pl.edu.agh.toik.ec.migration;

import pl.edu.agh.toik.ec.algorithm.Agent;
import pl.edu.agh.toik.ec.algorithm.Individual;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmigrationIndividuals implements EmigrationService {
    private Agent agent;
    private EmigrateStrategy emigrateStrategy;

    public EmigrationIndividuals(EmigrateStrategy emigrateStrategy){
        this.emigrateStrategy = emigrateStrategy;
    }

    private void emigrate(Agent agent, List<Individual> population) {
        this.agent = agent;
        List<Individual> selectedIndividuals = emigrateStrategy.selectIndividuals(population);
        List<MigrationMessage> migrationMessages = createMessages(selectedIndividuals);
        for(MigrationMessage migrationMessage : migrationMessages){
            agent.sendMessage(migrationMessage);
        }
    }

    private List<MigrationMessage> createMessages(List<Individual> individuals){
        List<MigrationMessage> migrationMessages = new ArrayList<MigrationMessage>();
        for(String receiverAgent : agent.getNeighbours()){
            String[] splited = receiverAgent.split(":");
            String workerName = splited[0] + ":" + splited[1];
            MigrationMessage migrationMessage = new MigrationMessage(agent.getName(), receiverAgent, individuals, new Date());
            migrationMessage.setTargetWorkerName(workerName);
            migrationMessages.add(migrationMessage);
        }
        return  migrationMessages;
    }

    public void process(Agent agent, List<Individual> population) {
        emigrate(agent, population);
    }
}
