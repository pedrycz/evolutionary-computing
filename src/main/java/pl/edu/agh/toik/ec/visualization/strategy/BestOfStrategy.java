package pl.edu.agh.toik.ec.visualization.strategy;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import pl.edu.agh.toik.ec.visualization.VisualizationMessage;

//TODO rewrite
/**
 * Strategy that approves only the best messages at given epoch.
 * 
 * @author Sleepy Student
 */
public class BestOfStrategy extends AbstractStrategy implements Runnable {
	private int backoff;
	private VisualizationMessage bestSoFar;
	private final ScheduledExecutorService scheduler;
	private Boolean received;
	private Long currentTimestamp;

	/**
	 * 
	 * @param backoffInSeconds
	 *            Seconds to wait for epoch ending.
	 */
	public BestOfStrategy(int backoffInSeconds) {
		this.backoff = backoffInSeconds;
		scheduler = Executors.newScheduledThreadPool(1);// TODO Springify
		received = false;
		scheduler.scheduleAtFixedRate(this, backoff, backoff, TimeUnit.SECONDS);
	}

	@Override
	public void interpretMessage(VisualizationMessage message) {
		VisualizationMessage notify = null;
		synchronized (this) { // parallel execution safety
			if (currentTimestamp == null) { // is it first execution
				bestSoFar = message;
				currentTimestamp = message.timestamp;
			} else if (message.timestamp > currentTimestamp) { // is it new
																// epoch
				notify = bestSoFar;
				bestSoFar = message;
				currentTimestamp = message.timestamp;
			} else if (message.fitness > bestSoFar.fitness) { // is the message
																// better than
																// current
																// picked one
				bestSoFar = message;
			}
			received = true;
		}
		if (notify != null) {
			setChanged();
			notifyObservers(notify);
		}
	}

	/**
	 * periodic task for asynchronous messages approving
	 */
	@Override
	public void run() {
		VisualizationMessage notify = null;
		synchronized (this) { // parallel execution safety
			if (received && bestSoFar != null) {
				notify = bestSoFar;
				bestSoFar = null;
			}
			received = false;
		}
		if (notify != null) {
			setChanged();
			notifyObservers(notify);
		}
	}
}
