package pl.edu.agh.toik.ec.algorithm.generation;

import java.util.List;

import pl.edu.agh.toik.ec.algorithm.Agent;
import pl.edu.agh.toik.ec.algorithm.Individual;

public abstract class PopulationGenerationStrategy {
    public abstract List<Individual> generatePopulation(Agent agent);
}
