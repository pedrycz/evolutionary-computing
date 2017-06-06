package pl.edu.agh.toik.ec.visualization.strategy;

import pl.edu.agh.toik.ec.visualization.VisualizationMessage;

/**
 * Strategy that approves only messages from given worker.
 * 
 * @author Mike Williams
 */
public class CertainWorkerStrategy extends AbstractStrategy {
	private String workerId;

	/**
	 * @param workerId
	 *            Id of Worker whose messages should be approved.
	 */
	public CertainWorkerStrategy(String workerId) {
		this.workerId = workerId;
	}

	@Override
	public void interpretMessage(VisualizationMessage message) {
		if (message.workerId.equals(workerId))
			notifyObservers(message);
	}

}
