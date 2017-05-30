package pl.edu.agh.toik.ec.algorithm.crossover;

import pl.edu.agh.toik.ec.algorithm.Individual;

public class UniformCrossoverStrategy extends CrossoverStrategy {
    
    @Override
    public Individual crossover(Individual parent1, Individual parent2) {
        Individual child = new Individual(parent1.getParametersSize());
        for(int i = 0 ; i < parent1.getParametersSize() ; i++){
            child.setParameter(i, random.nextBoolean() ? parent1.getParameter(i) : parent2.getParameter(i));
        }
        return child;
    }

}
