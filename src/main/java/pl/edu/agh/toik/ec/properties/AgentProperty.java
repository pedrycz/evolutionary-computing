package pl.edu.agh.toik.ec.properties;

import pl.edu.agh.toik.ec.algorithm.Agent;
import pl.edu.agh.toik.ec.algorithm.Property;
import pl.edu.agh.toik.ec.communication.Message;
import pl.edu.agh.toik.ec.communication.SimpleCommunicationService;

import java.util.Date;

public class AgentProperty<T> extends Property<T> {

    private final String parameterName;
    private final Agent agent;
    private final ObservationType observationType;

    public AgentProperty(String agentParameter, Agent agent, ObservationType observationType) {
        this.parameterName = agentParameter;
        this.agent = agent;
        this.observationType = observationType;
    }

    @Override
    public void setValue(T value) {
        super.setValue(value);
        if (observationType.check(value)) {
            Message message = new AgentMessage(parameterName, value);
            agent.sendMessage(SimpleCommunicationService.STARTER_NAME, message);
        }
    }

    private class AgentMessage extends PropertyMessage<T> {

        private final String parameterName;
        private final T value;
        private final long timestamp;

        AgentMessage(String parameterName, T value) {
            this.parameterName = parameterName;
            this.value = value;
            this.timestamp = new Date().getTime();
        }

        @Override
        public String getPropertyName() {
            return parameterName;
        }

        @Override
        public T getValue() {
            return value;
        }

        @Override
        public long getTimestamp() {
            return timestamp;
        }
    }
}
