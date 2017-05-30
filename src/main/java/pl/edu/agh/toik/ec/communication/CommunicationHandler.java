package pl.edu.agh.toik.ec.communication;

import java.util.HashMap;

/**
 * Created by Admin on 2017-05-15.
 */
public class CommunicationHandler {
    private HashMap<String, ReceiverInterface> receivers = new HashMap<>();

    public void registerEndpoint(String name, ReceiverInterface receiver){
        receivers.put(name, receiver);
    }

    public ReceiverInterface getReceiver(String name){
        return receivers.get(name);
    }

}

