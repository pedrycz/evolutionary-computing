package pl.edu.agh.toik.ec.communication;

/**
 * Created by ppedrycz on 20.05.2017.
 */
public class SimpleCommunicationService implements CommunicationService {
    private CommunicationHandler communicationHandler = new CommunicationHandler();
    public static final String STARTER_NAME = "Starter";

    @Override
    synchronized public void setStarter(ReceiverInterface starter) {
        communicationHandler.registerEndpoint(STARTER_NAME, starter);
    }

    @Override
    public void send(Message message) {
        communicationHandler.getReceiver(message.getWorkerName()).notify(message);
    }

    @Override
    synchronized public void registerReceiver(String workerName, ReceiverInterface receiver) {
        communicationHandler.registerEndpoint(workerName, receiver);
    }
}
