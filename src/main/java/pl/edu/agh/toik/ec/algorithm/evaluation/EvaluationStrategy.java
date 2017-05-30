package pl.edu.agh.toik.ec.algorithm.evaluation;

import java.util.List;

import pl.edu.agh.toik.ec.algorithm.Agent;
import pl.edu.agh.toik.ec.algorithm.AlgorithmStep;
import pl.edu.agh.toik.ec.algorithm.Individual;

public abstract class EvaluationStrategy implements AlgorithmStep {
    
    @Override
    public void process(Agent agent, List<Individual> population) {
        if(population == null)
            return;
        for(Individual individual : population){
            individual.setFitness(evaluate(individual));
        }
    }
    
    public abstract double evaluate(Individual individual);
    
}
