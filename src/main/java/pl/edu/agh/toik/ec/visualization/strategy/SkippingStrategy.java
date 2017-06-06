package pl.edu.agh.toik.ec.visualization.strategy;

import pl.edu.agh.toik.ec.visualization.VisualizationMessage;

/**
 * Strategy that skips some messages.
 * 
 * @author Robert Virding
 */
public class SkippingStrategy extends AbstractStrategy {
	private long messagesSkip;
	private long messagesSkipCounter;

	public SkippingStrategy(long messagesSkip) {
		messagesSkipCounter = 0;
		this.messagesSkip = messagesSkip;
	}

	@Override
	public void interpretMessage(VisualizationMessage message) {
		if (messagesSkipCounter == 0) {
			messagesSkipCounter = messagesSkip;
			this.setChanged();
			notifyObservers(message);
		}
		messagesSkipCounter -= 1;
	}

}
