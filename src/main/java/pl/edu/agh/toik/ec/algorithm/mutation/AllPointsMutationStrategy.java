package pl.edu.agh.toik.ec.algorithm.mutation;
 
import pl.edu.agh.toik.ec.algorithm.Individual;

public class AllPointsMutationStrategy extends MutationStrategy {

    private Double parameterMax=null;
    private Double parameterMin=null;
    @Override
    public void mutate(Individual individual) {
        for (int i = 0; i < individual.getParametersSize(); i++) {
            double randomDouble = random.nextDouble() * ((parameterMax == null?  Double.MAX_VALUE : parameterMax) - (parameterMin == null ? Double.MIN_VALUE : parameterMin )) + (parameterMin == null ? Double.MIN_VALUE : parameterMin);
            individual.setParameter(i, randomDouble);
        }
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
