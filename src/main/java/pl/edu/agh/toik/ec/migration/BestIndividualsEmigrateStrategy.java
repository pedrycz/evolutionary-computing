package pl.edu.agh.toik.ec.migration;

import pl.edu.agh.toik.ec.algorithm.Individual;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BestIndividualsEmigrateStrategy implements EmigrateStrategy {
    private int nuberOfBestIndividualsToEmirate;

    public BestIndividualsEmigrateStrategy(int nuberOfBestIndividualsToEmirate){
        this.nuberOfBestIndividualsToEmirate = nuberOfBestIndividualsToEmirate;
        System.out.println("[INFO] Select BestIndividualsEmigrateStrategy ");
    }

    public List<Individual> selectIndividuals(List<Individual> individuals) {
        List<Individual> individualList = new ArrayList<Individual>(individuals);
        List<Individual> emiratedIndividualList = new ArrayList<Individual>();
        Collections.sort(individualList, new Comparator<Individual>() {
            public int compare(Individual o1, Individual o2) {
                return new Double(o2.getFitness()).compareTo(o1.getFitness());
            }
        });
        for(int i=0; i<nuberOfBestIndividualsToEmirate; i++){
            emiratedIndividualList.add(individualList.get(i));
        }
        return emiratedIndividualList;
    }


    public static void main( String[] args ) {
        List<Individual> individuals = new ArrayList<Individual>();
        individuals.add(new Individual(10));
        individuals.add(new Individual(20));
        individuals.add(new Individual(30));
        individuals.add(new Individual(40));

        BestIndividualsEmigrateStrategy bestIndividualsEmigrateStrategy = new BestIndividualsEmigrateStrategy(2);
        System.out.println("BestIndividualsEmigrateStrategy");
        for(Individual individual : bestIndividualsEmigrateStrategy.selectIndividuals(individuals)){
            System.out.println(individual.getFitness());
        }

    }
}

