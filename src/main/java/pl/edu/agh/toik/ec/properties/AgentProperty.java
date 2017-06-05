package pl.edu.agh.toik.ec.properties;

import pl.edu.agh.toik.ec.algorithm.Agent;
import pl.edu.agh.toik.ec.algorithm.Property;
import pl.edu.agh.toik.ec.communication.SimpleCommunicationService;

public class AgentProperty<T> extends Property<T> {

    private final String parameterName;
    private final Agent agent;
    private final ObservationType observationType;

    public AgentProperty(String parameterName, Agent agent, ObservationType observationType) {
        this.parameterName = parameterName;
        this.agent = agent;
        this.observationType = observationType;
    }

    @Override
    public void setValue(T value) {
        super.setValue(value);
        if (observationType.check(value)) {
            PropertyMessage<T> message = new PropertyMessage<>(agent.getName(), SimpleCommunicationService.STARTER_NAME,
                    parameterName, value);

            agent.sendMessage(message);
        }
    }

}
