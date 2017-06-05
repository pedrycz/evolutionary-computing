package pl.edu.agh.toik.ec.visualization;

import java.util.List;
import java.util.Observer;

import pl.edu.agh.toik.ec.algorithm.selection.SelectionStrategy.SelectionType;
import pl.edu.agh.toik.ec.communication.Message;

//TODO divide the interface into intermodular and internal parts.
/**
 * Main Visualization module interface. Created by baran on 09.05.17.
 */
public interface Visualization extends Observer {
	/**
	 * Intermodular interface
	 * 
	 * @param message
	 *            Message that needs to be visualized.
	 */
	void notify(Message message);

	/**
	 * Configuration query, used internally in visualization module.
	 * 
	 * @return Visualization type set in xml configuration.
	 */
	VisualizationType getType();

	/**
	 * Configuration query, used internally in visualization module.
	 * 
	 * @return SelectionType set in xml configuration. Tells the module which
	 *         (bigger or lesser) fitness is better.
	 */
	SelectionType getSelectionType();

	/**
	 * State query, used internally in visualization module.
	 * 
	 * @return List of messages approved for visualization.
	 */
	List<VisualizationMessage> getMessages();
}
