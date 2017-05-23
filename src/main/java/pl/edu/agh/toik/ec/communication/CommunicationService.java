package pl.edu.agh.toik.ec.communication;

/**
 * Created by ppedrycz on 20.05.2017.
 */
public interface CommunicationService {
    void sendToStarter(Message message);
    void send(String address, Message message);
    void registerReceiver(String workerName, ReceiverInterface receiver);
    void setStarter(ReceiverInterface starter);
}
