package pl.edu.agh.toik.ec.algorithm.crossover;

import pl.edu.agh.toik.ec.algorithm.Individual;

public class TwoPointCrossoverStrategy extends CrossoverStrategy {
    
    @Override
    public Individual crossover(Individual parent1, Individual parent2) {
        int crossoverPoint1 = random.nextInt(parent1.getParametersSize()+1);
        int crossoverPoint2 = random.nextInt(parent1.getParametersSize()+1);
        int firstCrossoverPoint = crossoverPoint1 < crossoverPoint2 ? crossoverPoint1 : crossoverPoint2;
        int secondCrossoverPoint = crossoverPoint1 < crossoverPoint2 ? crossoverPoint2 : crossoverPoint1;
        boolean swapParents = random.nextBoolean();
        Individual firstCrossoverParent = swapParents ? parent2 : parent1;
        Individual secondCrossoverParent = swapParents ? parent1 : parent2;
        Individual child = new Individual(parent1.getParametersSize());
        for(int i = 0 ; i < parent1.getParametersSize() ; i++){
            child.setParameter(i, (i < firstCrossoverPoint || i > secondCrossoverPoint ) ? firstCrossoverParent.getParameter(i) : secondCrossoverParent.getParameter(i));
        }
        return child;
    }

}
