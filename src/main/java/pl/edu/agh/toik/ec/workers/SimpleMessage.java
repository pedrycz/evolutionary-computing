package pl.edu.agh.toik.ec.workers;

import pl.edu.agh.toik.ec.communication.Message;

import java.util.Date;

public class SimpleMessage extends Message {


    private String sender;
    private String receiver;
    private String content;
    private Date timeStamp;

    public SimpleMessage() {}

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

}
