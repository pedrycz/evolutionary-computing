package pl.edu.agh.toik.ec.visualization;

import java.util.Random;

import pl.edu.agh.toik.ec.communication.Message;

public class VisualizationMessage {
	public String workerId;
	public int timestamp;
	public double fitness;

	public VisualizationMessage(Message rawData) {
		// TODO implement
		Random random = new Random();
		workerId = "" + random.nextInt(4);
		timestamp = (int) (System.currentTimeMillis() / 1000);
		fitness = random.nextDouble();
	}
}
