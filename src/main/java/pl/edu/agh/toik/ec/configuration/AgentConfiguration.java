package pl.edu.agh.toik.ec.configuration;

import pl.edu.agh.toik.ec.algorithm.generation.PopulationGenerationStrategy;

import java.util.Map;

/**
 * Created by M on 2017-05-30.
 */
public class AgentConfiguration {

    private final int populationSize;
    private final int populationDimension;
    private final PopulationGenerationStrategy populationGenerationStrategy;
    private final Map<AgentParameter, PropertyConfiguration> parameterConfiguration;

    public AgentConfiguration(int populationSize, int populationDimension, PopulationGenerationStrategy populationGenerationStrategy, Map<AgentParameter, PropertyConfiguration> parameterConfiguration) {
        this.populationSize = populationSize;
        this.populationDimension = populationDimension;
        this.populationGenerationStrategy = populationGenerationStrategy;
        this.parameterConfiguration = parameterConfiguration;
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
}