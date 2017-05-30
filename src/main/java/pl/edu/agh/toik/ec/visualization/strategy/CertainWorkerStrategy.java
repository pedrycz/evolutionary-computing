package pl.edu.agh.toik.ec.visualization.strategy;

import pl.edu.agh.toik.ec.visualization.VisualizationMessage;

public class CertainWorkerStrategy extends AbstractStrategy {
	private String workerId;
	public CertainWorkerStrategy(String workerId) { this.workerId = workerId; }

	@Override
	public void interpretMessage(VisualizationMessage message) {
		if(message.workerId.equals(workerId))
			notifyObservers(message);
	}

}
