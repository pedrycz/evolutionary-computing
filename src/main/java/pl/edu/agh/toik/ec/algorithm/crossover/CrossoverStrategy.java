package pl.edu.agh.toik.ec.algorithm.crossover;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import pl.edu.agh.toik.ec.algorithm.Agent;
import pl.edu.agh.toik.ec.algorithm.AlgorithmStep;
import pl.edu.agh.toik.ec.algorithm.Individual;

public abstract class CrossoverStrategy implements AlgorithmStep {

    private double birthRatio = 0.2;
    protected Random random = new Random();
    
    @Override
    public void process(Agent agent, List<Individual> population) {
        if(population == null)
            return;
        List<Individual> tmpPopulation = new LinkedList<Individual>(population);
        for(int i = 0 ; i < (population.size() * birthRatio) ; i++){
            Individual parent1 = tmpPopulation.remove(random.nextInt(tmpPopulation.size()));
            Individual parent2 = tmpPopulation.remove(random.nextInt(tmpPopulation.size()));
            assert parent1 != null && parent2 != null : "Crossover parent cannot be null";
            assert parent1.getParametersSize() == parent2.getParametersSize() : "Parent individuals cannot have different sizes";
            population.add(crossover(parent1, parent2));
        }
    }
    
    protected abstract Individual crossover(Individual parent1, Individual parent2);

    public double getBirthRatio() {
        return birthRatio;
    }

    public void setBirthRatio(double birthRatio) {
        this.birthRatio = birthRatio;
    }
    
}
