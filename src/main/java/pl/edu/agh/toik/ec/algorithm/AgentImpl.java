package pl.edu.agh.toik.ec.algorithm;

import pl.edu.agh.toik.ec.algorithm.generation.PopulationGenerationStrategy;
import pl.edu.agh.toik.ec.communication.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.ToDoubleFunction;
import java.util.stream.DoubleStream;

public class AgentImpl implements Agent {

    private List<Individual> population;
    private LinkedList<AlgorithmStep> algorithmSteps = new LinkedList<>();

    private LinkedList<Message> incomingMessages = new LinkedList<>();
    private LinkedList<Message> outgoingMessages = new LinkedList<>();

    private Property<Double> bestFitnessProperty = new Property<>();
    private Property<Double> worstFitnessProperty = new Property<>();
    private Property<Double> populationDiversityProperty = new Property<>();

    private String name = null;

    private List<String> neighbours;

    private int populationSize;
    private int populationDimension;

    private PopulationGenerationStrategy populationGenerationStrategy;

    private boolean initialized = false;

    private final static ToDoubleFunction<Individual> INDIVIDUAL_TO_FITNESS_FUNCTION = new ToDoubleFunction<Individual>() {

        @Override
        public double applyAsDouble(Individual value) {
            return value.getFitness();
        }
    };
    
    @Override
    public void setup() {
        assert populationGenerationStrategy != null : "Population generation strategy cannot be null";
        population = populationGenerationStrategy.generatePopulation(this);
        assert population != null : "Setup population cannot be null";
        initialized = true;
    }

    @Override
    public void makeStep() {
        assert initialized : "Agent has to be initialized first";
        for (AlgorithmStep step : algorithmSteps)
            step.process(this, population);
        calculatePopulationDiversity();
        calculateFitnessProperties();
    }
    
    private void calculateFitnessProperties() {
        DoubleStream populationFitnessStream = population.stream().mapToDouble(INDIVIDUAL_TO_FITNESS_FUNCTION);
        bestFitnessProperty.setValue(populationFitnessStream.max().orElseGet(null));
        worstFitnessProperty.setValue(populationFitnessStream.min().orElseGet(null));
    }

    private void calculatePopulationDiversity() {
        // TODO Auto-generated method stub

    }
    
    @Override
    public void clearSteps() {
        algorithmSteps.clear();
    }

    @Override
    public boolean removeStep(AlgorithmStep step) {
        assert step != null : "Algorithm step cannot be null";
        return algorithmSteps.removeAll(Collections.singleton(step));
    }

    @Override
    public void addStep(AlgorithmStep step) {
        assert step != null : "Algorithm step cannot be null";
        algorithmSteps.add(step);
    }
    
    @Override
    public void addStep(int index, AlgorithmStep step) {
        assert step != null : "Algorithm step cannot be null";
        algorithmSteps.add(index, step);
    }
    
    @Override
    public void receiveMessage(Message message) {
        incomingMessages.add(message);
    }
    
    @Override
    public void sendMessage(String address, Message message) {
        outgoingMessages.add(message);
    }
    
    @Override
    public List<Message> getOutgoingMessages() {
        return Collections.unmodifiableList(outgoingMessages);
    }
    
    @Override
    public boolean consumeOutgoingMessage(Message message) {
        return outgoingMessages.remove(message);
    }
    
    @Override
    public List<Message> getIncomingMessages() {
        return Collections.unmodifiableList(incomingMessages);
    }
    
    @Override
    public boolean consumeIncomingMessage(Message message) {
        return incomingMessages.remove(message);
    }
    
    @Override
    public void setBestFitnessProperty(Property<Double> bestFitnessProperty) {
        this.bestFitnessProperty = bestFitnessProperty;
    }
    
    @Override   
    public void setWorstFitnessProperty(Property<Double> worstFitnessProperty) {
        this.worstFitnessProperty = worstFitnessProperty;
    }
    
    @Override
    public void setPopulationDiversityProperty(Property<Double> populationDiversityProperty) {
        this.populationDiversityProperty = populationDiversityProperty;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public List<String> getNeighbours() {
        return neighbours;
    }

    @Override
    public void setNeighbours(List<String> neighbours) {
        this.neighbours = neighbours;
    }

    @Override
    public int getPopulationSize() {
        return populationSize;
    }
    
    @Override
    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }
    
    @Override
    public int getPopulationDimension() {
        return populationDimension;
    }
    
    @Override
    public void setPopulationDimension(int populationDimension) {
        this.populationDimension = populationDimension;
    }
    
    @Override
    public PopulationGenerationStrategy getPopulationGenerationStrategy() {
        return populationGenerationStrategy;
    }
    
    @Override
    public void setPopulationGenerationStrategy(PopulationGenerationStrategy populationGenerationStrategy) {
        this.populationGenerationStrategy = populationGenerationStrategy;
    }

}
