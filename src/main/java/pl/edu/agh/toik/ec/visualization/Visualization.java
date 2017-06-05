package pl.edu.agh.toik.ec.visualization;

import java.util.List;
import java.util.Observer;

import pl.edu.agh.toik.ec.algorithm.selection.SelectionStrategy.SelectionType;
import pl.edu.agh.toik.ec.communication.Message;

/**
 * Created by baran on 09.05.17.
 */
public interface Visualization extends Observer {
	void notify(Message message);

	VisualizationType getType();
	
	SelectionType getSelectionType();

	List<VisualizationMessage> getMessages();
}
