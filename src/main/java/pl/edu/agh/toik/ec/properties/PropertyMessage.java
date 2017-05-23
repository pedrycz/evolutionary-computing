package pl.edu.agh.toik.ec.properties;

import pl.edu.agh.toik.ec.communication.Message;

public interface PropertyMessage<T> extends Message {

    String getPropertyName();

    T getValue();

    long getTimestamp();

}
