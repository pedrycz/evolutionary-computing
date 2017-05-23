package pl.edu.agh.toik.ec.communication;

import java.util.HashMap;

/**
 * Created by Admin on 2017-05-15.
 */
public class CommunicationHandler {
    private HashMap<String, ReceiverInterface> receivers = new HashMap<>();
    private final String STARTER_NAME = "Starter";

    public void registerEndpoint(String name, ReceiverInterface receiver){
        receivers.put(name, receiver);
    }

    public void registerStarter(ReceiverInterface receiver){
        receivers.put(STARTER_NAME, receiver);
    }

    public ReceiverInterface getStarter(){
        return receivers.get(STARTER_NAME);
    }

    public ReceiverInterface getReceiver(String name){
        return receivers.get(name);
    }

}

