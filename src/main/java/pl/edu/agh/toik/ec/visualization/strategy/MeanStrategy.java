package pl.edu.agh.toik.ec.visualization.strategy;

import java.util.Optional;

import pl.edu.agh.toik.ec.visualization.VisualizationMessage;

/**
 * Strategy that approves messages by combining them into their mean.
 * 
 * @author Francis Pritchard
 */
public class MeanStrategy extends AbstractStrategy {
	private VisualizationMessageWrapper average;

	public MeanStrategy() {
		average = new VisualizationMessageWrapper();
	}

	@Override
	public void interpretMessage(VisualizationMessage message) {
		average.add(message).ifPresent(x -> {
			setChanged();
			notifyObservers(x);
		});
	}

	/**
	 * Helper for computing the mean.
	 */
	class VisualizationMessageWrapper {
		private String workerId = null;
		private Double fitness;
		private Integer delim;
		private Long currentTimestamp;

		/**
		 * Adds a message to mean.
		 * 
		 * @param message
		 *            Message to be added to mean.
		 * @return Possibly a computed mean (or nothing).
		 */
		public Optional<VisualizationMessage> add(VisualizationMessage message) {
			if (currentTimestamp == null) {
				setFirstMessage(message);
			} else {
				if (currentTimestamp < message.timestamp) {
					VisualizationMessage out = parse();
					setFirstMessage(message);
					return Optional.of(out);
				} else if (currentTimestamp == message.timestamp) {
					fitness += message.fitness;
					delim += 1;
				}
			}
			return Optional.empty();
		}

		/**
		 * mean initialization
		 * 
		 * @param message
		 */
		private void setFirstMessage(VisualizationMessage message) {
			workerId = message.workerId;
			currentTimestamp = message.timestamp;
			fitness = message.fitness;
			delim = 1;
		}

		/**
		 * Computes mean from current state of the object.
		 * 
		 * @return computed mean.
		 */
		private VisualizationMessage parse() {
			VisualizationMessage ret = new VisualizationMessage();
			ret.workerId = workerId;
			ret.fitness = fitness / delim;
			ret.timestamp = currentTimestamp;
			return ret;
		}
	}
}