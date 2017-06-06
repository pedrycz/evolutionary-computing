package pl.edu.agh.toik.ec.visualization;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import pl.edu.agh.toik.ec.algorithm.selection.SelectionStrategy.SelectionType;
import pl.edu.agh.toik.ec.communication.Message;
import pl.edu.agh.toik.ec.visualization.conf.VisualizationConst;

/**
 * Created by baran on 09.05.17.
 */
public class VisualizationImpl implements Visualization {
	public static final Logger LOG = Logger.getLogger(VisualizationImpl.class);

	private VisualizationStrategy strategy;
	private VisualizationType type;
	private SelectionType selectionType;

	private List<VisualizationMessage> cache = new ArrayList<>();

	/**
	 * Responsible for sending WebSocket messages.
	 */
	@Autowired
	private SimpMessagingTemplate template;

	public VisualizationImpl() {
	}

	public VisualizationImpl(VisualizationStrategy strategy, VisualizationType type, SelectionType selectionType) {
		this.strategy = strategy;
		this.type = type;
		this.selectionType = selectionType;
		strategy.addObserver(this);
	}

	/**
	 * Receiving and parsing messages. Sending them to strategy for evaluation.
	 */
	@Override
	public void notify(Message message) {
		strategy.addObserver(this);
		try {
			strategy.interpretMessage(VisualizationMessage.parse(message));
		} catch (VisualizationMessageParseException e) {
			LOG.error("Visualization received unparsable message of type " + message.getClass().getSimpleName());
		}
	}

	/**
	 * Storing and sending messages approved by VisualizationStrategy to
	 * WebSocket clients.
	 */
	@Override
	public void update(Observable o, Object arg) {
		cache.add((VisualizationMessage) arg);
		if (template == null) // TODO confirm possibility
			LOG.error("[VisualizationImpl.update] template is null!");
		else
			template.convertAndSend(VisualizationConst.WS_ENDPOINT_FITNESS, arg);
	}

	@Override
	public VisualizationType getType() {
		return type;
	}

	@Override
	public SelectionType getSelectionType() {
		return selectionType;
	}

	@Override
	public List<VisualizationMessage> getMessages() {
		return cache;
	}

	public void setStrategy(VisualizationStrategy strategy) {
		this.strategy = strategy;
		strategy.addObserver(this);
	}

	public void setType(VisualizationType type) {
		this.type = type;
	}
}
