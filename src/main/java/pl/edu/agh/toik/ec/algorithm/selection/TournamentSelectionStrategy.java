package pl.edu.agh.toik.ec.algorithm.selection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.edu.agh.toik.ec.algorithm.Individual;

public class TournamentSelectionStrategy extends SelectionStrategy {

    @Override
    public List<Individual> select(int selectionSize, List<Individual> population) {
        double survivalRatio = ((double) selectionSize) / population.size();
        double groupSize = (1.0 / survivalRatio);
        List<Individual> newPopulation = new ArrayList<>();
        for (double i = 0; i < population.size() - groupSize + 1; i += groupSize) {
            List<Individual> group = population.subList((int) i, (int) (i + groupSize));
            group.sort(INDIVIDUAL_FITNESS_COMPARATOR);
            if(selectionType == SelectionType.MINIMUM)
                Collections.reverse(group);
            newPopulation.add(group.get(group.size() - 1));
        }
        return newPopulation;
    }
}
