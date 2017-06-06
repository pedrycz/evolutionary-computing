package pl.edu.agh.toik.ec.visualization.conf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.edu.agh.toik.ec.algorithm.selection.SelectionStrategy.SelectionType;
import pl.edu.agh.toik.ec.visualization.Visualization;
import pl.edu.agh.toik.ec.visualization.VisualizationMessage;

//TODO remove cross origin annotation
/**
 * HTTP endpoints definition.
 * @author Dominik Jachas
 */
@CrossOrigin(origins = "*")
@RestController
public class HttpController {
	@Autowired
	private Visualization visualization;

	// TODO remove method
	@RequestMapping("/type")
	public String getVisualizationType() {
		return visualization.getType().getClass().getSimpleName();
	}

	/**
	 * @return List of all strategy-approved messages.
	 */
	@RequestMapping("/messages")
	public List<VisualizationMessage> getMessages() {
		return visualization.getMessages();
	}

	/**
	 * @return Current web-side configuration.
	 */
	@RequestMapping("/config")
	public ConfigEndpoint getConfig() {
		return new ConfigEndpoint(visualization.getType().getClass().getSimpleName(),
				visualization.getSelectionType().equals(SelectionType.MAXIMUM));
	}

	/**
	 * helper class for {@link getConfig()} method
	 */
	public class ConfigEndpoint {
		public final String type;
		public final Boolean biggerFitnessIsBetter;

		public ConfigEndpoint(String type, Boolean biggerFitnessIsBetter) {
			this.type = type;
			this.biggerFitnessIsBetter = biggerFitnessIsBetter;
		}
	}
}
