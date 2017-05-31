package pl.edu.agh.toik.ec.migration;

import pl.edu.agh.toik.ec.algorithm.Individual;
import java.util.List;

public interface EmigrateStrategy {
    List<Individual> selectIndividuals(List<Individual> individuals);
}
