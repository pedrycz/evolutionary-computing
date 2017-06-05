package pl.edu.agh.toik.ec.visualization.conf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.edu.agh.toik.ec.visualization.Visualization;
import pl.edu.agh.toik.ec.visualization.VisualizationMessage;

//TODO remove cross origin annotation
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

	@RequestMapping("/messages")
	public List<VisualizationMessage> getMessages() {
		return visualization.getMessages();
	}

	@RequestMapping("/config")
	public ConfigEndpoint getConfig() {
		// TODO bind biggerConfigIsBetter with appropriate configuration
		return new ConfigEndpoint(visualization.getType().getClass().getSimpleName(), true);
	}

	public class ConfigEndpoint {
		public final String type;
		public final Boolean biggerFitnessIsBetter;

		public ConfigEndpoint(String type, Boolean biggerFitnessIsBetter) {
			this.type = type;
			this.biggerFitnessIsBetter = biggerFitnessIsBetter;
		}
	}
}
