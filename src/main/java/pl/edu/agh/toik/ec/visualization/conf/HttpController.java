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

	@RequestMapping("/type")
	public String getVisualizationType() {
		return visualization.getType().getClass().getSimpleName();
	}

	@RequestMapping("/messages")
	public List<VisualizationMessage> getMessages() {
		return visualization.getMessages();
	}
}
