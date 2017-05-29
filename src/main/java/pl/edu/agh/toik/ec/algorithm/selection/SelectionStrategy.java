package pl.edu.agh.toik.ec.algorithm.selection;

import pl.edu.agh.toik.ec.algorithm.Agent;
import pl.edu.agh.toik.ec.algorithm.AlgorithmStep;
import pl.edu.agh.toik.ec.algorithm.Individual;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public abstract class SelectionStrategy implements AlgorithmStep {

    public enum SelectionType {
        MAXIMUM, MINIMUM;
    }
    
    protected SelectionType selectionType = SelectionType.MAXIMUM;
    Random random = new Random();

    protected static final Comparator<Individual> INDIVIDUAL_FITNESS_COMPARATOR = new Comparator<Individual>() {

        @Override
        public int compare(Individual o1, Individual o2) {
            return Double.valueOf(o1.getFitness()).compareTo(o2.getFitness());
        }
    };
    
    @Override
    public void process(Agent agent, List<Individual> population) {
        population.retainAll(select(agent.getPopulationSize(), population));
    }

    public abstract List<Individual> select(int selectionSize, List<Individual> population);

    public SelectionType getSelectionType() {
        return selectionType;
    }

    public void setSelectionType(SelectionType selectionType) {
        this.selectionType = selectionType;
    }

}
