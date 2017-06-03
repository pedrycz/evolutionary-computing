package pl.edu.agh.toik.ec.migration;

import java.util.Date;
import java.util.List;
import pl.edu.agh.toik.ec.algorithm.Individual;
import pl.edu.agh.toik.ec.communication.Message;
import pl.edu.agh.toik.ec.workers.SimpleMessage;

public class MigrationMessage extends SimpleMessage {
    private List<Individual> individuals;

    public MigrationMessage(String sender, String receiver, List<Individual> content, Date timeStamp) {
        super.setSender(sender);
        super.setReceiver(receiver);
        super.setContent("MigrationMessage");
        super.setTimeStamp(timeStamp);
        this.individuals = content;

    }

    public List<Individual> getIndividuals() {
        return individuals;
    }

    public void setIndividuals(List<Individual> content) {
        this.individuals = content;
    }

}