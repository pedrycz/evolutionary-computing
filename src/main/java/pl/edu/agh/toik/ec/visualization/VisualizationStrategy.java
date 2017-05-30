package pl.edu.agh.toik.ec.visualization;

import java.util.Observer;

/**
 * Created by piotrek on 20.05.2017.
 */
public interface VisualizationStrategy {
    public void interpretMessage(VisualizationMessage message);
    public void addObserver(Observer o);
}
