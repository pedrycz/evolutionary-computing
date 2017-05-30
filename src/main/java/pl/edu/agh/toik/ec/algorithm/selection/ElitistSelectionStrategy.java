package pl.edu.agh.toik.ec.algorithm.selection;

import java.util.Collections;
import java.util.List;

import pl.edu.agh.toik.ec.algorithm.Individual;

public class ElitistSelectionStrategy extends SelectionStrategy {
    @Override
    public List<Individual> select(int selectionSize, List<Individual> population) {
        population.sort(INDIVIDUAL_FITNESS_COMPARATOR);
        if (selectionType == SelectionType.MAXIMUM)
            Collections.reverse(population);
        return population.subList(0, selectionSize);

    }
}
