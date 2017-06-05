package pl.edu.agh.toik.ec.properties;

import pl.edu.agh.toik.ec.workers.SimpleMessage;

import java.util.Date;

public class PropertyMessage<T> extends SimpleMessage {

    private String propertyName;
    private T value;

    PropertyMessage(String sender, String receiver, String propertyName, T value) {
        this.propertyName = propertyName;
        this.value = value;

        this.setContent("PropertyMessage");
        this.setSender(sender);
        this.setTimeStamp(new Date());

        this.setReceiver(receiver);
        this.setTargetWorkerName(receiver);
    }

    public String getPropertyName() {
        return propertyName;
    }

    public T getValue() {
        return value;
    }

}
