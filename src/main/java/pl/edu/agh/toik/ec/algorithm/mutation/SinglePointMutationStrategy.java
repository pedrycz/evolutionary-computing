package pl.edu.agh.toik.ec.algorithm.mutation;
 
import pl.edu.agh.toik.ec.algorithm.Individual;

public class SinglePointMutationStrategy extends MutationStrategy {

    private Double parameterMin = null;
    private Double parameterMax = null;
    @Override
    public void mutate(Individual individual) {
        int index = random.nextInt(individual.getParametersSize());

        double randomDouble = random.nextDouble() * ((parameterMax == null?  Double.MAX_VALUE : parameterMax) - (parameterMin == null ? Double.MIN_VALUE : parameterMin )) + (parameterMin == null ? Double.MIN_VALUE : parameterMin);
        individual.setParameter(index, randomDouble);

    }
    public double getParameterMax() {
        return parameterMax;
    }

    public void setParameterMax(double parameterMax) {
        this.parameterMax = parameterMax;
    }

    public double getParameterMin() {
        return parameterMin;
    }

    public void setParameterMin(double parameterMin) {
        this.parameterMin = parameterMin;
    }
}
