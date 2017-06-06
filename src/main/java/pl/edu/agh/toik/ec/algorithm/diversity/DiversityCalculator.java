package pl.edu.agh.toik.ec.algorithm.diversity;

import pl.edu.agh.toik.ec.algorithm.Individual;

import java.math.BigDecimal;
import java.util.List;

import static pl.edu.agh.toik.ec.algorithm.diversity.BigMath.*;

public class DiversityCalculator {

    private List<Individual> population;
    private int populationSize;
    private int populationDimension;

    private static final double MINIMAL_AVERAGE_SIMILARITY_DISTANCE = 0.00000001;

    public DiversityCalculator(List<Individual> population, int populationSize, int populationDimension) {
        this.population = population;
        this.populationSize = populationSize;
        this.populationDimension = populationDimension;
    }

    public double calculatePopulationDiversity() {
        BigDecimal sum = BigDecimal.ZERO;
        double avg = populationSize * Math.log(populationSize);
        for (int i = 0; i < populationSize; i++) {
            BigDecimal similarity = similaritySumFor(population.get(i)).divide(BigDecimal.valueOf(populationSize), BigDecimal.ROUND_HALF_DOWN);
            BigDecimal pow = power(similarity, zeta()).add(BigDecimal.valueOf(MINIMAL_AVERAGE_SIMILARITY_DISTANCE));
            sum = sum.add(log(pow));
        }
        return -sum.divide(BigDecimal.valueOf(avg), BigDecimal.ROUND_HALF_DOWN).doubleValue();
    }

    private BigDecimal similaritySumFor(Individual individual) {
        BigDecimal sum = BigDecimal.ZERO;
        for (int i = 0; i < populationSize; i++) {
            sum = sum.add(similarity(individual, population.get(i)));
        }
        return sum;
    }

    private BigDecimal zeta() {
        return BigDecimal.valueOf(Math.log(populationSize));
    }

    private BigDecimal similarity(Individual individual1, Individual individual2) {
        BigDecimal distance = distance(individual1, individual2);
        double alpha = Math.sqrt(populationSize) / 3;
        double omega = -Math.log(0.5) / alpha;

        return exp(distance.multiply(BigDecimal.valueOf(omega)).negate());
    }

    private BigDecimal distance(Individual individual1, Individual individual2) {
        BigDecimal distance = BigDecimal.ZERO;
        for (int i = 0; i < populationDimension; i++) {
            distance = distance.add(BigDecimal.valueOf(individual1.getParameter(i)).pow(2)
                    .subtract(BigDecimal.valueOf(individual2.getParameter(i)).pow(2)));
        }
        distance = sqrt(distance);
        return distance;
    }

}
