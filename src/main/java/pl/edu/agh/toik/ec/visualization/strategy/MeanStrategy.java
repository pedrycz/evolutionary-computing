package pl.edu.agh.toik.ec.visualization.strategy;

import java.util.Optional;

import pl.edu.agh.toik.ec.visualization.VisualizationMessage;

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

	class VisualizationMessageWrapper {
		private String workerId = null;
		private Double fitness;
		private Integer delim;
		private Long currentTimestamp;

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

		private void setFirstMessage(VisualizationMessage message) {
			workerId = message.workerId;
			currentTimestamp = message.timestamp;
			fitness = message.fitness;
			delim = 1;
		}

		private VisualizationMessage parse() {
			VisualizationMessage ret = new VisualizationMessage();
			ret.workerId = workerId;
			ret.fitness = fitness / delim;
			ret.timestamp = currentTimestamp;
			return ret;
		}
	}
}