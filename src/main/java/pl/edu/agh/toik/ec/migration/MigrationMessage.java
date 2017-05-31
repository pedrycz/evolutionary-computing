package pl.edu.agh.toik.ec.migration;

import java.util.Date;
import java.util.List;
import pl.edu.agh.toik.ec.algorithm.Individual;
import pl.edu.agh.toik.ec.communication.Message;

public class MigrationMessage extends Message {
    String sender;
    String receiver;
    Date timeStamp;
    List<Individual> content;

    public MigrationMessage(String sender, String receiver, List<Individual> content, Date timeStamp) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timeStamp = timeStamp;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<Individual> getContent() {
        return content;
    }

    public void setContent(List<Individual> content) {
        this.content = content;
    }

}