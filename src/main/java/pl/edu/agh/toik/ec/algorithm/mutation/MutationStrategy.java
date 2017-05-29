package pl.edu.agh.toik.ec.algorithm.mutation;

import pl.edu.agh.toik.ec.algorithm.Agent;
import pl.edu.agh.toik.ec.algorithm.AlgorithmStep;
import pl.edu.agh.toik.ec.algorithm.Individual;

import java.util.List;
import java.util.Random;

public abstract class MutationStrategy implements AlgorithmStep {

    double mutationRatio = 0.1;
    Random random = new Random();

    @Override
    public void process(Agent agent, List<Individual> population) {
        if (population == null)
            return;
        for(Individual individual : population)
            mutate(individual);
    }

    public abstract void mutate(Individual individual);

    public double getMutationRatio() {
        return mutationRatio;
    }

    public void setMutationRatio(double mutationRatio) {
        this.mutationRatio = mutationRatio;
    }
}
