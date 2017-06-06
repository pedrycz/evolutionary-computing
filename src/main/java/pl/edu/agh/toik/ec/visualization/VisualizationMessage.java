package pl.edu.agh.toik.ec.visualization;

import java.util.Random;

import org.apache.log4j.Logger;

import pl.edu.agh.toik.ec.communication.Message;
import pl.edu.agh.toik.ec.properties.PropertyMessage;
import pl.edu.agh.toik.ec.workers.SimpleMessage;

/**
 * Represents internal system message parsed for visualization purposes.
 * 
 * @author Andrzej Duda
 */
public class VisualizationMessage {
	public static final Logger LOG = Logger.getLogger(VisualizationMessage.class);

	public String workerId;
	public long timestamp;
	public double fitness;

	public static VisualizationMessage parse(Message rawData) throws VisualizationMessageParseException {
		VisualizationMessage m = new VisualizationMessage();
		m.workerId = rawData.getWorkerName();
		if (rawData instanceof PropertyMessage) {
			PropertyMessage propertyMessage = (PropertyMessage) rawData; // TODO
																			// parametrized
																			// casting
			m.timestamp = propertyMessage.getTimeStamp().getTime();
			m.workerId = propertyMessage.getSender();
			try {
				m.fitness = (Double) propertyMessage.getValue();
			} catch (ClassCastException e) {
				randomData(m);
			}
		} else if (rawData instanceof SimpleMessage) {
			SimpleMessage simpleMessage = (SimpleMessage) rawData;
			m.timestamp = simpleMessage.getTimeStamp().getTime();
			try {
				m.fitness = Double.parseDouble(simpleMessage.getContent());
			} catch (NumberFormatException e) {
				randomData(m);
			}
		} else {
			throw new VisualizationMessageParseException();
		}
		return m;
	}

	// TODO remove this method when it is no longer useful
	private static void randomData(VisualizationMessage m) {
		LOG.error("Failed to parse double from message content. Continuing anyway with fake data.");
		Random random = new Random();
		m.fitness = random.nextDouble();
	}

	public VisualizationMessage() {
	}
}
