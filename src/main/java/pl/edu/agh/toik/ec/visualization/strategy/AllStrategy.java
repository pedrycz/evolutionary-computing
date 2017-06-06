package pl.edu.agh.toik.ec.visualization.strategy;

import pl.edu.agh.toik.ec.visualization.VisualizationMessage;

/**
 * The simplest strategy - approving all messages.
 * 
 * @author Robert Virding
 */
public class AllStrategy extends AbstractStrategy {

	@Override
	public void interpretMessage(VisualizationMessage message) {
		this.setChanged();
		notifyObservers(message);
	}

}
