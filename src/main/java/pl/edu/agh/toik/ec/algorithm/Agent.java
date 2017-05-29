package pl.edu.agh.toik.ec.algorithm;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.ToDoubleFunction;
import java.util.stream.DoubleStream;

import pl.edu.agh.toik.ec.algorithm.generation.PopulationGenerationStrategy;
import pl.edu.agh.toik.ec.communication.Message;

public class Agent {

    private List<Individual> population;
    private LinkedList<AlgorithmStep> algorithmSteps = new LinkedList<>();

    private LinkedList<Message> incomingMessages = new LinkedList<>();
    private LinkedList<Message> outgoingMessages = new LinkedList<>();

    private Property<Double> bestFitnessProperty = new Property<>();
    private Property<Double> worstFitnessProperty = new Property<>();
    private Property<Double> populationDiversityProperty = new Property<>();

    private String name = null;

    private List<Agent> neighbours;

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
    
    public void setup() {
        assert populationGenerationStrategy != null : "Population generation strategy cannot be null";
        population = populationGenerationStrategy.generatePopulation(this);
        assert population != null : "Setup population cannot be null";
        initialized = true;
    }

    public void step() {
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

    public void clearSteps() {
        algorithmSteps.clear();
    }

    public boolean removeStep(AlgorithmStep step) {
        assert step != null : "Algorithm step cannot be null";
        return algorithmSteps.removeAll(Collections.singleton(step));
    }

    public void addStep(AlgorithmStep step) {
        assert step != null : "Algorithm step cannot be null";
        algorithmSteps.add(step);
    }

    public void addStep(int index, AlgorithmStep step) {
        assert step != null : "Algorithm step cannot be null";
        algorithmSteps.add(index, step);
    }

    public void receiveMessage(Message message) {
        incomingMessages.add(message);
    }

    public void sendMessage(String address, Message message) {
        outgoingMessages.add(message);
    }

    public List<Message> getOutgoingMessages() {
        return Collections.unmodifiableList(outgoingMessages);
    }

    public boolean consumeOutgoingMessage(Message message) {
        return outgoingMessages.remove(message);
    }

    public List<Message> getIncomingMessages() {
        return Collections.unmodifiableList(incomingMessages);
    }

    public boolean consumeIncomingMessage(Message message) {
        return incomingMessages.remove(message);
    }

    public void setBestFitnessProperty(Property<Double> bestFitnessProperty) {
        this.bestFitnessProperty = bestFitnessProperty;
    }
    
    public void setWorstFitnessProperty(Property<Double> worstFitnessProperty) {
        this.worstFitnessProperty = worstFitnessProperty;
    }

    public void setPopulationDiversityProperty(Property<Double> populationDiversityProperty) {
        this.populationDiversityProperty = populationDiversityProperty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Agent> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<Agent> neighbours) {
        this.neighbours = neighbours;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public int getPopulationDimension() {
        return populationDimension;
    }

    public void setPopulationDimension(int populationDimension) {
        this.populationDimension = populationDimension;
    }

    public PopulationGenerationStrategy getPopulationGenerationStrategy() {
        return populationGenerationStrategy;
    }

    public void setPopulationGenerationStrategy(PopulationGenerationStrategy populationGenerationStrategy) {
        this.populationGenerationStrategy = populationGenerationStrategy;
    }

}
