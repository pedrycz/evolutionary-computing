package pl.edu.agh.toik.ec.visualization.strategy;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import pl.edu.agh.toik.ec.visualization.VisualizationMessage;

//TODO rewrite
public class BestOfStrategy extends AbstractStrategy implements Runnable {
	private int backoff;
	private VisualizationMessage bestSoFar;
	private final ScheduledExecutorService scheduler;
	private Boolean received;
	private Long currentTimestamp;

	public BestOfStrategy(int backoffInSeconds) {
		this.backoff = backoffInSeconds;
		scheduler = Executors.newScheduledThreadPool(1);// TODO Springify
		received = false;
		scheduler.scheduleAtFixedRate(this, backoff, backoff, TimeUnit.SECONDS);
	}

	@Override
	public void interpretMessage(VisualizationMessage message) {
		VisualizationMessage notify = null;
		synchronized (this) {
			if (currentTimestamp == null) {
				bestSoFar = message;
				currentTimestamp = message.timestamp;
			} else if (message.timestamp > currentTimestamp) {
				notify = bestSoFar;
				bestSoFar = message;
				currentTimestamp = message.timestamp;
			} else if (message.fitness > bestSoFar.fitness) {
				bestSoFar = message;
			}
			received = true;
		}
		if (notify != null) {
			setChanged();
			notifyObservers(notify);
		}
	}

	@Override
	public void run() {
		VisualizationMessage notify = null;
		synchronized (this) {
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
