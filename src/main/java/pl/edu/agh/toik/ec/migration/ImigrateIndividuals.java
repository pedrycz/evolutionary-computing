package pl.edu.agh.toik.ec.migration;

import pl.edu.agh.toik.ec.algorithm.Agent;
import pl.edu.agh.toik.ec.algorithm.Individual;
import pl.edu.agh.toik.ec.communication.Message;
import java.util.List;

public class ImigrateIndividuals implements ImigrationService {

    public void process(Agent agent, List<Individual> population) {
        for (Message message : agent.getIncomingMessages()) {
            if(isOurMessage(message)){
                imigrate(population, (MigrationMessage) message);
            }
        }
    }

    private boolean isOurMessage(Message message){
        return message instanceof MigrationMessage;
    }

    private void imigrate(List<Individual> population, MigrationMessage message) {
        List<Individual> imigratedIndividuals = message.getContent();
        population.addAll(imigratedIndividuals);

    }
}
