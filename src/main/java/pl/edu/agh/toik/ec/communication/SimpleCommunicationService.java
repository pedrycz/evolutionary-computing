package pl.edu.agh.toik.ec.communication;

/**
 * Created by ppedrycz on 20.05.2017.
 */
public class SimpleCommunicationService implements CommunicationService {
    private CommunicationHandler communicationHandler = new CommunicationHandler();

    @Override
    public void setStarter(ReceiverInterface starter) {
        communicationHandler.registerStarter(starter);
    }

    @Override
    public void sendToStarter(Message message) {
        communicationHandler.getStarter().notify(message);
    }

    public void send(String address, Message message) {
        communicationHandler.getReceiver(address).notify(message);
    }

    @Override
    public void registerReceiver(String workerName, ReceiverInterface receiver) {
        communicationHandler.registerEndpoint(workerName, receiver);
    }
}
