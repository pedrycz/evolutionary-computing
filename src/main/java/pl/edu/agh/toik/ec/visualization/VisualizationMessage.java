package pl.edu.agh.toik.ec.visualization;

import java.util.Random;

import org.apache.log4j.Logger;

import pl.edu.agh.toik.ec.communication.Message;
import pl.edu.agh.toik.ec.properties.PropertyMessage;
import pl.edu.agh.toik.ec.workers.SimpleMessage;

public class VisualizationMessage {
	public static final Logger LOG = Logger.getLogger(VisualizationMessage.class);

	public String workerId;
	public long timestamp;
	public double fitness;

	public static VisualizationMessage parse(Message rawData) throws VisualizationMessageParseException {
		VisualizationMessage m = new VisualizationMessage();
		m.workerId = rawData.getWorkerName();
		if (rawData instanceof PropertyMessage) {
			PropertyMessage propertyMessage = (PropertyMessage) rawData;
			m.timestamp = propertyMessage.getTimeStamp().getTime();
			// TODO if possible user sender field for m.workerId
			try {
				m.fitness = (Double)propertyMessage.getValue();
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
	
	private static void randomData(VisualizationMessage m) {
		LOG.error("Failed to parse double from message content. Continuing anyway with fake data.");
		Random random = new Random();
		m.fitness = random.nextDouble();
	}

	public VisualizationMessage() {
	}
}
