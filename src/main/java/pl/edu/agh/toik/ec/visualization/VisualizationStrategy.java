package pl.edu.agh.toik.ec.visualization;

import java.util.Observer;

/**
 * VisualizationStrategy receives messages from Visualization core and notifies
 * observers when it decides that some messages are ready to be send. The
 * observer pattern was used here to allow asynchronous message evaluation.
 * Created by piotrek on 20.05.2017.
 */
public interface VisualizationStrategy {
	/**
	 * @param message
	 *            Message send for strategy evaluation.
	 */
	public void interpretMessage(VisualizationMessage message);

	/**
	 * @param o
	 *            Observer that wants to receive updates about approved
	 *            messages.
	 */
	public void addObserver(Observer o);
}
