package pl.edu.agh.toik.ec.algorithm.evaluation;

import pl.edu.agh.toik.ec.algorithm.Individual;

public class SphereEvaluationStrategy extends EvaluationStrategy {

    @Override
    public double evaluate(Individual individual) {
        double sum = 0;
        for(int i = 0 ; i < individual.getParametersSize() ; i++){
            double x = individual.getParameter(i);
            sum += x*x;
        }
        return sum;
    }

}
