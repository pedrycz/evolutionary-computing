package pl.edu.agh.toik.ec.algorithm.generation;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import pl.edu.agh.toik.ec.algorithm.Agent;
import pl.edu.agh.toik.ec.algorithm.Individual;

public class RandomPopulationGenerationStrategy extends PopulationGenerationStrategy {
    
    private Double parameterMax = null;
    private Double parameterMin = null;
    
    @Override
    public List<Individual> generatePopulation(Agent agent) {
        LinkedList<Individual> newPopulation = new LinkedList<>();
        for(int i = 0 ; i < agent.getPopulationSize() ; i++){
            Individual individual = new Individual(agent.getPopulationDimension());
            for(int j = 0 ; j < agent.getPopulationDimension() ; j++){
                individual.setParameter(j, ThreadLocalRandom.current().nextDouble(parameterMin != null ? parameterMin : Double.MIN_VALUE, parameterMax != null ? parameterMax : Double.MAX_VALUE));
            }
            newPopulation.add(individual);
        }
        return newPopulation;
    }

    public Double getParameterMax() {
        return parameterMax;
    }

    public void setParameterMax(Double parameterMax) {
        this.parameterMax = parameterMax;
    }

    public Double getParameterMin() {
        return parameterMin;
    }

    public void setParameterMin(Double parameterMin) {
        this.parameterMin = parameterMin;
    }

}
