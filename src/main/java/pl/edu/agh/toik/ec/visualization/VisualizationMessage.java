package pl.edu.agh.toik.ec.visualization;

import java.util.Random;

import pl.edu.agh.toik.ec.communication.Message;

public class VisualizationMessage {
    public String workerId;
    public int timestamp;
    public double fitness;

    public VisualizationMessage(Message rawData) {
	workerId = rawData.getWorkerName();
	// TODO implement
	Random random = new Random();
	timestamp = (int) (System.currentTimeMillis() / 1000);
	fitness = random.nextDouble();
    }
}
