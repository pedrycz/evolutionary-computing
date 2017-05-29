package pl.edu.agh.toik.ec.algorithm;

import java.util.List;

public interface AlgorithmStep {

    public void process(Agent agent, List<Individual> population);
    
}
