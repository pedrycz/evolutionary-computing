package pl.edu.agh.toik.ec.algorithm.constraint;

import java.util.List;

import pl.edu.agh.toik.ec.algorithm.Agent;
import pl.edu.agh.toik.ec.algorithm.AlgorithmStep;
import pl.edu.agh.toik.ec.algorithm.Individual;

public abstract class ConstraintStrategy implements AlgorithmStep {
    
    protected abstract void fixIndividual(Individual individual);
    
    @Override
    public void process(Agent agent, List<Individual> population) {
        if(population == null)
            return;
        for(Individual individual : population){
            fixIndividual(individual);
        }
    }
    
    
}
