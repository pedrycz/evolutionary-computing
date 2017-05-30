package pl.edu.agh.toik.ec.properties;

import pl.edu.agh.toik.ec.communication.Message;

public abstract class PropertyMessage<T> extends Message {

    abstract String getPropertyName();

    abstract T getValue();

    abstract long getTimestamp();

}
