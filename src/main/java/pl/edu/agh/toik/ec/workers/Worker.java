package pl.edu.agh.toik.ec.workers;

import pl.edu.agh.toik.ec.communication.Message;

public interface Worker {

    void start();

    boolean isActive();

    void step();

    void sendMessage(Message msg);

    boolean checkStopCondition();

}
