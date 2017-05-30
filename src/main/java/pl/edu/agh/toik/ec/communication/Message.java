package pl.edu.agh.toik.ec.communication;

import java.io.Serializable;

/**
 * Created by sjchmiela on 23.05.2017.
 */
public abstract class Message implements Serializable {
    private String workerName;

    public String getWorkerName() {
        return workerName;
    }

    public void setTargetWorkerName(String workerName) {
        this.workerName = workerName;
    }
}
