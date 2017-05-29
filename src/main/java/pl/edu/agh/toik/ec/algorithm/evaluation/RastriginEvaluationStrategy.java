package pl.edu.agh.toik.ec.algorithm.evaluation;

import pl.edu.agh.toik.ec.algorithm.Individual;

public class RastriginEvaluationStrategy extends EvaluationStrategy {

    private final static double A = 10;
    
    @Override
    public double evaluate(Individual individual) {
        double sum = 0;
        sum += A * individual.getParametersSize();
        for(int i = 0 ; i < individual.getParametersSize() ; i++){
            double x = individual.getParameter(i);
            sum += (x*x - A*Math.cos(2*Math.PI *x));
        }
        return sum;
    }

}
