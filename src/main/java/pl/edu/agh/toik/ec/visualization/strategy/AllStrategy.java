package pl.edu.agh.toik.ec.visualization.strategy;

import pl.edu.agh.toik.ec.visualization.VisualizationMessage;

public class AllStrategy extends AbstractStrategy {

	@Override
	public void interpretMessage(VisualizationMessage message) {
		this.setChanged();
		notifyObservers(message);
	}

}
