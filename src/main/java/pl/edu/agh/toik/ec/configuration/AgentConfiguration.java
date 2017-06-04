package pl.edu.agh.toik.ec.configuration;

import pl.edu.agh.toik.ec.algorithm.AlgorithmStep;
import pl.edu.agh.toik.ec.algorithm.generation.PopulationGenerationStrategy;

import java.util.LinkedList;
import java.util.Map;

/**
 * Created by M on 2017-05-30.
 */
public class AgentConfiguration {

    private final int populationSize;
    private final int populationDimension;
    private final PopulationGenerationStrategy populationGenerationStrategy;
    private final Map<AgentParameter, PropertyConfiguration> parameterConfiguration;
    private final LinkedList<AlgorithmStep> algorithmSteps;

    public AgentConfiguration(int populationSize, int populationDimension, PopulationGenerationStrategy populationGenerationStrategy, Map<AgentParameter, PropertyConfiguration> parameterConfiguration, LinkedList<AlgorithmStep> algorithmSteps) {
        this.populationSize = populationSize;
        this.populationDimension = populationDimension;
        this.populationGenerationStrategy = populationGenerationStrategy;
        this.parameterConfiguration = parameterConfiguration;
        this.algorithmSteps = algorithmSteps;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public int getPopulationDimension() {
        return populationDimension;
    }

    public PopulationGenerationStrategy getPopulationGenerationStrategy() {
        return populationGenerationStrategy;
    }

    public Map<AgentParameter, PropertyConfiguration> getParameterConfiguration() {
        return parameterConfiguration;
    }

    public LinkedList<AlgorithmStep> getAlgorithmSteps() {
        return algorithmSteps;
    }

}